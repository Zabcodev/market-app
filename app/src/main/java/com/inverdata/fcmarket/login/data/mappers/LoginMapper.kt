package com.inverdata.fcmarket.login.data.mappers

import com.inverdata.fcmarket.Session
import com.inverdata.fcmarket.session.data.local.model.SessionLocal
import com.inverdata.fcmarket.login.data.network.model.request.LoginRemote
import com.inverdata.fcmarket.login.data.network.model.response.UserRemote
import com.inverdata.fcmarket.login.domain.model.Login
import com.inverdata.fcmarket.login.domain.model.User
import com.inverdata.fcmarket.login.domain.model.UserSession

fun Login.toRemote(): LoginRemote = LoginRemote(
    email = email,
    password = password
)

fun UserRemote.toDomain(): User = User(
    access = access ?: "",
    refresh = refresh ?: "",
    user = user ?: 0
)

fun User.toSession(): UserSession = UserSession(
    id = null,
    access = access,
    refresh = refresh,
    userId = user.toLong()
)