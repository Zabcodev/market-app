package com.inverdata.fcmarket.customer.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.inverdata.fcmarket.R
import com.inverdata.fcmarket.core.presentation.components.BottomSheetComponent
import com.inverdata.fcmarket.core.presentation.components.DropdownMenuComponent
import com.inverdata.fcmarket.customer.domain.model.Customer
import com.inverdata.fcmarket.customer.presentation.CustomerEvent
import com.inverdata.fcmarket.customer.presentation.CustomerState
import com.inverdata.fcmarket.customer.utils.CustomerConstants.identificationOptions

@Composable
fun AddCustomerSheet(
    state: CustomerState,
    newCustomer: Customer?,
    isOpen: Boolean,
    onEvent: (CustomerEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    BottomSheetComponent(
        visible = isOpen,
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = stringResource(R.string.register_customer_text)
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomerTextField(
                    value = newCustomer?.firstName ?: "",
                    onValueChange = {
                        onEvent(CustomerEvent.OnNameChanged(it))
                    },
                    label = stringResource(R.string.customer_name),
                    placeholder = stringResource(R.string.customer_name_placeholder)
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomerTextField(
                    value = newCustomer?.lastName ?: "",
                    onValueChange = {
                        onEvent(CustomerEvent.OnLastNameChanged(it))
                    },
                    label = stringResource(R.string.customer_lastname),
                    placeholder = stringResource(R.string.customer_lastname_placeholder)
                )
                Spacer(modifier = Modifier.height(16.dp))
                DropdownMenuComponent(
                    optionList = identificationOptions,
                    onOptionSelected = { type ->
                        onEvent(CustomerEvent.OnIdentificationTypeChanged(type))
                        println(type)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomerTextField(
                    value = newCustomer?.identificationNumber ?: "",
                    onValueChange = {
                        onEvent(CustomerEvent.OnIdentificationChanged(it))
                    },
                    label = stringResource(R.string.customer_identification),
                    placeholder = stringResource(R.string.customer_identification_placeholder)
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomerTextField(
                    value = newCustomer?.address ?: "",
                    onValueChange = {
                        onEvent(CustomerEvent.OnAddressChanged(it))
                    },
                    label = stringResource(R.string.customer_address),
                    placeholder = stringResource(R.string.customer_address_placeholder)
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomerTextField(
                    value = newCustomer?.phoneNumber ?: "",
                    onValueChange = {
                        onEvent(CustomerEvent.OnPhoneNumberChanged(it))
                    },
                    label = stringResource(R.string.customer_phone_number),
                    placeholder = stringResource(R.string.customer_phone_number_placeholder)
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomerTextField(
                    value = newCustomer?.email ?: "",
                    onValueChange = {
                        onEvent(CustomerEvent.OnEmailChanged(it))
                    },
                    label = stringResource(R.string.customer_email),
                    placeholder = stringResource(R.string.customer_email_placeholder)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onEvent(CustomerEvent.SaveCustomer)
                    },
                    shape = RoundedCornerShape(12.dp),
                    enabled = state.allValidationCheck
                ) {
                    Text(
                        text = stringResource(R.string.register_text)
                    )
                }
            }

            IconButton(
                onClick = {
                    onEvent(CustomerEvent.OnDismissAddCustomerClick)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close icon"
                )
            }

        }
    }
}