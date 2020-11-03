package dev.carrion.repository

import dev.carrion.kmmtest.common.FactBo
import dev.carrion.remote.models.FactDto

fun FactDto.toBo(): FactBo =
    FactBo(
        id = this.id,
        text = this.text,
        updatedAt = this.updatedAt,
        createdAt = this.createdAt
    )