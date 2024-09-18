package com.inverdata.fcmarket.configuration.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.inverdata.fcmarket.configuration.domain.usecases.DeleteSessionUseCase
import com.inverdata.fcmarket.configuration.domain.usecases.GetUserEmailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConfigurationViewModel(
    private val deleteSessionUseCase: DeleteSessionUseCase,
    private val getUserEmailUseCase: GetUserEmailUseCase
) : ScreenModel {

    private val _state = MutableStateFlow(ConfigurationState())
    val state = _state.asStateFlow()

    init {
        getUserEmail()
    }

    private fun getUserEmail() {
        screenModelScope.launch {
            _state.update { state ->
                state.copy(userEmail = getUserEmailUseCase.invoke())
            }
        }
    }

    fun deleteSession() {
        screenModelScope.launch {
            deleteSessionUseCase.invoke()
        }
    }

}