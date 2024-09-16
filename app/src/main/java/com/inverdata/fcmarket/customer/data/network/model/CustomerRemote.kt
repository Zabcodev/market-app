package com.inverdata.fcmarket.customer.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomerRemote(
    @SerialName("id_persona")
    val id: Int? = null,
    @SerialName("nombre_persona")
    val firstName: String? = null,
    @SerialName("apellido_persona")
    val lastName: String? = null,
    @SerialName("tipo_persona")
    val typeIdentification: String? = null,
    @SerialName("numero_identificacion_persona")
    val identificationNumber: String? = null,
    @SerialName("direccion_persona")
    val address: String? = null,
    @SerialName("telefono_persona")
    val phoneNumber: String? = null,
    @SerialName("email_persona")
    val email: String? = null,
    @SerialName("activo_persona")
    val isActive: Int? = null,
    @SerialName("contri")
    val isTaxpayer: Boolean? = null
)
