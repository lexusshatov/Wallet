package com.mouse.core.interaction

import com.mouse.core.State
import com.mouse.core.Void
import com.mouse.core.logger.Logger
import com.mouse.core.logger.SystemLogger
import com.mouse.core.validate.EmptyValidate
import com.mouse.core.validate.Validate
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.reflect.KProperty

interface Interaction<in Params, out Result : Any> {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Flow<State<Result>>
    operator fun invoke(params: Params)
}

abstract class BaseInteraction<in Params, out Result : Any>(
    private val scope: CoroutineScope,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val validation: Validate<Params> = EmptyValidate(),
    private val logger: Logger = SystemLogger,
) : Interaction<Params, Result> {

    private val mutableState: MutableSharedFlow<State<Result>> =
        MutableSharedFlow(replay = 1, extraBufferCapacity = 10)
    private var job: Job? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): Flow<State<Result>> {
        return mutableState
    }

    override fun invoke(params: Params) {
        job?.cancel()
        job = scope.launch(coroutineDispatcher) {
            val validationResult = validation.validate(params)
            if (validationResult.isSuccess) {
                try {
                    mutableState.emit(State.Loading)
                    val result = process(params)
                    mutableState.emit(State.Success(result))
                } catch (error: CancellationException) {
                    logger.log("Network", "Coroutine cancelled: ${this@BaseInteraction}")
                } catch (error: Exception) {
                    val message = error.localizedMessage.ifEmpty { "Something went wrong!" }
                    mutableState.emit(State.Error(message))
                }
            } else {
                val errors = validationResult.errors
                    .mapNotNull { it.message }
                    .joinToString("\n")
                mutableState.emit(State.Error(errors))
            }
            job = null
        }
    }

    abstract suspend fun process(params: Params): Result
}

operator fun Interaction<Void, *>.invoke(): Unit = invoke(Void)