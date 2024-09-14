package com.inverdata.fcmarket.session.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.registry.screenModule
import com.inverdata.fcmarket.session.data.local.model.SessionLocal
import com.inverdata.fcmarket.session.domain.repository.SessionRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val repository: SessionRepository
): ScreenModel {

    private val _sharedFlow = MutableSharedFlow<SplashDestination>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        checkDestination()
    }

    private fun checkDestination() {
        screenModelScope.launch {
            repository.getSessions().collect { sessions ->
                if (sessions.isEmpty()) {
                    _sharedFlow.emit(SplashDestination.Login)
                } else {
                    _sharedFlow.emit(SplashDestination.Home)
                }
            }
        }
    }


}