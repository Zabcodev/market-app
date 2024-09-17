package com.inverdata.fcmarket.customer.presentation

sealed interface CustomerEvent {
    data class OnIdentificationChanged(val identification: String): CustomerEvent
    data object OnSearchClick: CustomerEvent
    data class OnNameChanged(val name: String): CustomerEvent
    data class OnLastNameChanged(val lastname: String): CustomerEvent
    data class OnAddressChanged(val address: String): CustomerEvent
    data class OnPhoneNumberChanged(val phoneNumber: String): CustomerEvent
    data class OnEmailChanged(val email: String): CustomerEvent
    data class OnIdentificationTypeChanged(val identificationType: String): CustomerEvent
    data object SaveCustomer: CustomerEvent
    data object OnAddNewCustomerClick: CustomerEvent
    data object OnDismissAddCustomerClick: CustomerEvent
}