package com.example.astrop.domain.use_case.get_detail_by_type

import com.example.astrop.domain.model.AstroDetail

interface GetDetailByTypeUseCase {
    suspend operator fun  invoke(id: Int): List<AstroDetail>
}