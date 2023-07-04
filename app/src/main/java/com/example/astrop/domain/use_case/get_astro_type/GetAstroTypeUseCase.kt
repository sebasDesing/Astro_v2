package com.example.astrop.domain.use_case.get_astro_type

import com.example.astrop.domain.model.AstroType

interface GetAstroTypeUseCase {

    suspend operator fun  invoke(): List<AstroType>
}