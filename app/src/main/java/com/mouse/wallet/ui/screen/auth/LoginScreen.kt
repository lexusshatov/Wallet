package com.mouse.wallet.ui.screen.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.data.User
import com.mouse.core.State
import com.mouse.core.collectState
import com.mouse.core.interaction.BaseInteraction.Companion.BASE_ERROR_KEY
import com.mouse.core.interaction.LoginInteraction
import com.mouse.core.validate.login.LoginValidate
import com.mouse.wallet.R
import com.mouse.wallet.ui.ScreenState
import com.mouse.wallet.ui.component.WalletButton
import com.mouse.wallet.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    screenState: ScreenState,
    authViewModel: AuthViewModel = koinViewModel(),
    navigateToHome: () -> Unit,
) {
    val loginResult: Flow<State<User>> by authViewModel.login

    var isLoadingLogin: Boolean by remember { mutableStateOf(false) }
    var login: String by rememberSaveable { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }
    var loginError: String by remember { mutableStateOf("") }
    var passwordError: String by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = login,
            onValueChange = {
                login = it
                loginError = ""
            },
            label = { Text(text = stringResource(R.string.username)) },
            singleLine = true,
            isError = loginError.isNotEmpty(),
            supportingText = { Text(loginError) }
        )
        TextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = ""
            },
            label = { Text(text = stringResource(R.string.password)) },
            singleLine = true,
            isError = passwordError.isNotEmpty(),
            supportingText = { Text(passwordError) }
        )
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            WalletButton(
                onClick = { /*TODO*/ },
            ) {
                Text(text = stringResource(R.string.register))
            }
            WalletButton(
                onClick = {
                    val loginParams = LoginInteraction.Params(
                        login = login,
                        password = password
                    )
                    authViewModel.login(loginParams)
                },
                loading = isLoadingLogin
            ) {
                Text(text = stringResource(R.string.log_in))
            }
        }
    }

    LaunchedEffect(key1 = "collect") {
        loginResult.collectState(
            onLoadingChange = { isLoadingLogin = it },
            onError = { key, error ->
                when (key) {
                    LoginValidate.LOGIN_KEY -> loginError = error
                    LoginValidate.PASSWORD_KEY -> passwordError = error
                    BASE_ERROR_KEY -> screenState.scope.launch {
                        screenState.snackbarHostState.showSnackbar(error)
                    }
                }
            }
        ) { navigateToHome() }
    }
}