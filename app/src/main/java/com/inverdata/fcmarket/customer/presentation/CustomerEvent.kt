package com.inverdata.fcmarket.customer.presentation

sealed interface CustomerEvent {
    data class OnIdentificationChanged(val identification: String): CustomerEvent
    data object OnSearchClick: CustomerEvent
}