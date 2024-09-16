@file:OptIn(ExperimentalMaterial3Api::class)

package com.inverdata.fcmarket.customer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inverdata.fcmarket.R
import com.inverdata.fcmarket.customer.domain.model.Customer
import com.inverdata.fcmarket.customer.presentation.CustomerEvent
import com.inverdata.fcmarket.customer.presentation.CustomerState

@Composable
fun CustomerComponent(
    state: CustomerState,
    newCustomer: Customer?,
    onEvent: (CustomerEvent) -> Unit,
    onUpdateDialogStatus: () -> Unit,
    onSelectProductClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val focus = LocalFocusManager.current

    if (state.showDialog) {
        BasicAlertDialog(
            modifier = Modifier.background(Color.White),
            onDismissRequest = {
                onUpdateDialogStatus()
            }
        ) {
            Column {
                Text(
                    text = state.messageError ?: "",
                    color = Color.Black
                )
                TextButton(
                    onClick = {
                        onUpdateDialogStatus()
                    }
                ) {
                    Text(
                        text = "Ok"
                    )
                }
            }
        }
    }


    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.customer_data),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomerTextField(
            value = newCustomer?.identificationNumber ?: "",
            onValueChange = { identificationValue ->
                onEvent(CustomerEvent.OnIdentificationChanged(identificationValue))
            },
            label = stringResource(R.string.customer_identification),
            placeholder = stringResource(R.string.customer_identification_placeholder),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onEvent(CustomerEvent.OnSearchClick)
                    focus.clearFocus()
                }
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomerTextField(
            value = state.selectedCustomer?.firstName ?: "",
            onValueChange = { },
            label = stringResource(R.string.customer_name),
            placeholder = stringResource(R.string.customer_name_placeholder),
            enabled = false,
            readOnly = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomerTextField(
            value = state.selectedCustomer?.lastName ?: "",
            onValueChange = {},
            label = stringResource(R.string.customer_lastname),
            placeholder = stringResource(R.string.customer_lastname_placeholder),
            enabled = false,
            readOnly = true
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                onSelectProductClick()
            },
            shape = RoundedCornerShape(12.dp),
            enabled = state.selectedCustomer?.firstName != null
        ) {
            Text(
                text = stringResource(R.string.select_products)
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun CustomerComponentPreview() {
    CustomerComponent()
}*/
