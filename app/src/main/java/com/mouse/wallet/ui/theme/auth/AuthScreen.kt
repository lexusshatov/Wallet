package com.mouse.wallet.ui.theme.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mouse.core.State
import com.mouse.core.data.User
import com.mouse.core.interaction.LoginInteraction
import com.mouse.wallet.R
import com.mouse.wallet.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    authViewModel: AuthViewModel = viewModel(),
) {
    val loginResult: Flow<State<User>> by authViewModel.login

    var login: String by rememberSaveable { mutableStateOf("") }
    var loginError: String by remember {
        mutableStateOf("")
    }
    var password: String by rememberSaveable { mutableStateOf("") }
    var passwordError: String by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = "collect") {
        loginResult.collect { state ->
            println(state)
        }
    }


    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = login,
            onValueChange = { login = it },
            label = { Text(text = stringResource(R.string.username)) },
            singleLine = true
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(R.string.password)) },
            singleLine = true
        )
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(text = stringResource(R.string.register))
            }
            Button(
                onClick = {
                    val loginParams = LoginInteraction.Params(
                        login = login,
                        password = password
                    )
                    authViewModel.login(loginParams)
                }
            ) {
                Text(text = stringResource(R.string.log_in))
            }
        }
    }
}