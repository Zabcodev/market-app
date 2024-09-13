package com.inverdata.fcmarket.login.data.mappers

import com.inverdata.fcmarket.login.data.network.model.request.LoginRemote
import com.inverdata.fcmarket.login.data.network.model.response.UserRemote
import com.inverdata.fcmarket.login.domain.model.Login
import com.inverdata.fcmarket.login.domain.model.User

fun Login.toRemote(): LoginRemote = LoginRemote(
    email = email,
    password = password
)

fun UserRemote.toDomain(): User = User(
    access = access ?: "",
    refresh = refresh ?: "",
    user = user ?: 0
)