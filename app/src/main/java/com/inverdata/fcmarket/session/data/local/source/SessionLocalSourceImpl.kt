package com.inverdata.fcmarket.session.data.local.source

import android.se.omapi.Session
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.inverdata.fcmarket.core.data.database.helper.DbHelper
import com.inverdata.fcmarket.session.data.local.model.SessionLocal
import com.inverdata.fcmarket.session.data.mappers.toLocal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SessionLocalSourceImpl(
    private val dbHelper: DbHelper,
    private val scope: CoroutineScope
) : SessionLocalSource {
    override suspend fun getSessions(): Flow<List<SessionLocal>> {
        return scope.async {
            dbHelper.withDatabase { database ->
                database.sessionQueries.getSession()
                    .asFlow()
                    .mapToList(scope.coroutineContext)
                    .map { sessions ->
                        sessions.map { session ->
                            session.toLocal()
                        }
                    }
            }
        }.await()
    }

    override suspend fun insertSession(session: SessionLocal) {
        scope.launch {
            dbHelper.withDatabase { database ->
                database.sessionQueries.insertSession(
                    id = session.id,
                    access = session.access,
                    refresh = session.refresh,
                    userId = session.userId,
                    userEmail = session.userEmail
                )
            }
        }
    }

    override suspend fun getUserEmail(): String? {
        return scope.async {
            dbHelper.withDatabase { database ->
                database.sessionQueries.getUserEmail()
                    .executeAsOneOrNull()
            }
        }.await()
    }

    override suspend fun deleteSession() {
        scope.launch {
            dbHelper.withDatabase { database ->
                database.sessionQueries.deleteSession()
            }
        }
    }
}