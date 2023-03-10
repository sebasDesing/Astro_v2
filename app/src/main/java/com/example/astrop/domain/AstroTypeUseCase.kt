package com.example.astrop.domain

import com.example.astrop.data.AstroRepository
import com.example.astrop.data.model.AstroTypeModel

class AstroTypeUseCase {
    private val repository = AstroRepository()
    suspend operator fun invoke(): AstroTypeModel? {
        return repository.getTypeAstro()
    }
}