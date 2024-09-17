package com.inverdata.fcmarket.login.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.inverdata.fcmarket.R
import com.inverdata.fcmarket.core.presentation.components.DefaultLayout
import com.inverdata.fcmarket.login.domain.model.Login
import com.inverdata.fcmarket.login.presentation.LoginEvent
import com.inverdata.fcmarket.login.presentation.LoginState

@Composable
fun LoginComponent(
    state: LoginState,
    newLogin: Login?,
    onEvent: (LoginEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    DefaultLayout {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.logo_fc),
                    contentDescription = stringResource(R.string.app_name)
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LoginTextField(
                    value = newLogin?.email ?: "",
                    onValueChanged = { onEvent(LoginEvent.OnEmailChanged(it)) },
                    label = stringResource(R.string.label_email),
                    placeholder = stringResource(R.string.placeholder_email),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Email,
                            contentDescription = stringResource(R.string.ic_email)
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    error = state.emailError
                )
                Spacer(modifier = Modifier.height(16.dp))
                LoginTextField(
                    value = newLogin?.password ?: "",
                    onValueChanged = { onEvent(LoginEvent.OnPasswordChanged(it)) },
                    label = stringResource(R.string.label_password),
                    placeholder = stringResource(R.string.placeholder_password),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Password,
                            contentDescription = stringResource(R.string.ic_password)
                        )
                    },
                    trailingIcon = {
                        val icon = if (passwordVisibility) {
                            Icons.Filled.VisibilityOff
                        } else {
                            Icons.Filled.Visibility
                        }

                        IconButton(
                            onClick = { passwordVisibility = !passwordVisibility }
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = "show/hide password"
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true,
                    error = state.passwordError
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                onClick = {
                    onEvent(LoginEvent.OnButtonClicked)
                },
                shape = RoundedCornerShape(12.dp)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(15.dp),
                        strokeWidth = 1.dp,
                        color = Color.White
                    )
                } else {
                    Text(
                        text = "Iniciar"
                    )
                }
            }

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

/*
@Preview
@Composable
fun LoginComponentPreview() {
    LoginComponent()
}*/
