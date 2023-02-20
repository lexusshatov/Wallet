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
import com.mouse.wallet.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen() {
    var login: String by rememberSaveable { mutableStateOf("") }
    var loginError: String by remember {
        mutableStateOf("")
    }
    var password: String by rememberSaveable { mutableStateOf("") }

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
                onClick = { /*TODO*/ }
            ) {
                Text(text = stringResource(R.string.log_in))
            }
        }
    }
}