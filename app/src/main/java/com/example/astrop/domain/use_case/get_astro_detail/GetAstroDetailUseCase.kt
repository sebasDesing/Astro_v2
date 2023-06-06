package com.example.astrop.domain.use_case.get_astro_detail

import com.example.astrop.domain.model.AstroDetail

interface GetAstroDetailUseCase {
    suspend operator fun  invoke(): List<AstroDetail>
}