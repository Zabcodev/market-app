package com.inverdata.fcmarket.session.domain.repository

import com.inverdata.fcmarket.session.data.local.model.SessionLocal
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun getSessions(): Flow<List<SessionLocal>>
    suspend fun getUserEmail(): String?
    suspend fun deleteSession()
}