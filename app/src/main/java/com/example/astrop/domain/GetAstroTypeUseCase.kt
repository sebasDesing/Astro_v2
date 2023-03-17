package com.example.astrop.domain

import com.example.astrop.data.AstroRepository
import com.example.astrop.data.model.AstroTypeModel

class GetAstroTypeUseCase {
    private val repository = AstroRepository()
    suspend operator fun invoke(): List<AstroTypeModel>? {
        return repository.getTypeAstro()
    }
}