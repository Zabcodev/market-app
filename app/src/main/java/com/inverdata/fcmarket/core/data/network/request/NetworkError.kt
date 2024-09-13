package com.inverdata.fcmarket.core.data.network.request

import com.inverdata.fcmarket.login.data.network.model.response.ErrorRemote

enum class NetworkError(val message: ErrorRemote): Error {
    REQUEST_TIMEOUT(message = ErrorRemote()),
    BAD_REQUEST(message = ErrorRemote(error = "Contraseña Invalida")),
    UNAUTHORIZED(message = ErrorRemote()),
    CONFLICT(message = ErrorRemote()),
    NO_INTERNET(message = ErrorRemote()),
    PAYLOAD_TO_LARGE(message = ErrorRemote()),
    SERVER_ERROR(message = ErrorRemote()),
    SERIALIZATION(message = ErrorRemote()),
    UNKNOWN(message = ErrorRemote())
}