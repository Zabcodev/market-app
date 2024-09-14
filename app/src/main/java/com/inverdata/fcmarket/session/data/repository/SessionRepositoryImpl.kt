package com.inverdata.fcmarket.session.data.repository

import com.inverdata.fcmarket.session.data.local.model.SessionLocal
import com.inverdata.fcmarket.session.data.local.source.SessionLocalSource
import com.inverdata.fcmarket.session.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow

class SessionRepositoryImpl(
    private val localSource: SessionLocalSource
): SessionRepository {
    override suspend fun getSessions(): Flow<List<SessionLocal>> {
        return localSource.getSessions()
    }
}