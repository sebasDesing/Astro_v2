package com.example.astrop.domain.use_case

import com.example.astrop.data.AstroRepositoryImpl
import com.example.astrop.domain.model.AstroDetail
import javax.inject.Inject


class GetDetailByTypeUseCase @Inject constructor(
    private val repository
    : AstroRepositoryImpl
) {

    suspend operator fun invoke(id: Int): List<AstroDetail> {

        val astroDetail = repository.getAllAstrosDetailBytypeFromBD(id)
        return if (astroDetail.isNotEmpty()) {
            astroDetail
        } else {
            emptyList()
        }
    }

}
