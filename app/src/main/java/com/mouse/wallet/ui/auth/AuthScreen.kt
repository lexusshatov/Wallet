package com.mouse.wallet.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.data.User
import com.mouse.core.State
import com.mouse.core.collectState
import com.mouse.core.interaction.LoginInteraction
import com.mouse.core.validate.login.LoginValidate
import com.mouse.wallet.R
import com.mouse.wallet.ui.component.WalletButton
import com.mouse.wallet.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    authViewModel: AuthViewModel = koinViewModel(),
) {
    val loginResult: Flow<State<User>> by authViewModel.login

    var isLoadingLogin: Boolean by remember { mutableStateOf(false) }
    var login: String by rememberSaveable { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }
    var loginError: String by remember { mutableStateOf("") }
    var passwordError: String by remember { mutableStateOf("") }

    var user by remember { mutableStateOf(User()) }

    LaunchedEffect(key1 = "collect") {
        loginResult.collectState(
            onLoadingChange = { isLoadingLogin = it },
            onError = { key, error ->
                when (key) {
                    LoginValidate.LOGIN_KEY -> loginError = error
                    LoginValidate.PASSWORD_KEY -> passwordError = error
                }
            }
        ) { user = it }
    }


    Column(
        modifier = Modifier.fillMaxHeight(),
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

        Text(text = user.toString())
    }
}