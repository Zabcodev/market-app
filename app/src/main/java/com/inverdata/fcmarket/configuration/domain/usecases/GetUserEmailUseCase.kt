package com.inverdata.fcmarket.configuration.domain.usecases

import com.inverdata.fcmarket.session.domain.repository.SessionRepository

class GetUserEmailUseCase(
    private val repository: SessionRepository
) {

    suspend operator fun invoke(): String = repository.getUserEmail().orEmpty()

}