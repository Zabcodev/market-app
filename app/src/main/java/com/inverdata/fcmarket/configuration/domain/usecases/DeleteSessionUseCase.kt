package com.inverdata.fcmarket.configuration.domain.usecases

import com.inverdata.fcmarket.session.domain.repository.SessionRepository

class DeleteSessionUseCase(
    private val repository: SessionRepository
) {
    suspend operator fun invoke() = repository.deleteSession()
}