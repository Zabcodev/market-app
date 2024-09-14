package com.inverdata.fcmarket.session.data.mappers

import com.inverdata.fcmarket.Session
import com.inverdata.fcmarket.login.domain.model.UserSession
import com.inverdata.fcmarket.session.data.local.model.SessionLocal

fun Session.toLocal(): SessionLocal = SessionLocal(
    id = id,
    access = access,
    refresh = refresh,
    userId = userId
)

fun UserSession.toDatabase(): SessionLocal = SessionLocal(
    id = id,
    access = access,
    refresh = refresh,
    userId = userId
)