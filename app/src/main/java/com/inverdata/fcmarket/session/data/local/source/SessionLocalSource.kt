package com.inverdata.fcmarket.session.data.local.source

import android.se.omapi.Session
import com.inverdata.fcmarket.session.data.local.model.SessionLocal
import kotlinx.coroutines.flow.Flow

interface SessionLocalSource {

    suspend fun getSessions(): Flow<List<SessionLocal>>
    suspend fun insertSession(session: SessionLocal)
    suspend fun getUserEmail(): String?
    suspend fun deleteSession()

}