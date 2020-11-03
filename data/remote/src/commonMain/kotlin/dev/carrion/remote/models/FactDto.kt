package dev.carrion.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FactDto(
    val used: Boolean,
    val source: String,
    val type: String,
    val deleted: Boolean,
    @SerialName("_id")
    val id: String,
    @SerialName("__v")
    val v: Int,
    val text: String,
    val updatedAt: String,
    val createdAt: String,
    val status: StatusDto? =  null,
    val user: String
)

@Serializable
data class StatusDto(
    val verified: Boolean,
    val sentCount: Int
)