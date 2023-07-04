package com.example.astrop.domain.use_case.get_detail_by_type

import com.example.astrop.data.AstroRepositoryImpl
import com.example.astrop.domain.model.AstroDetail
import com.example.astrop.domain.use_case.get_astro_detail.GetAstroDetailUseCase
import javax.inject.Inject


class GetDetailByTypeUseCaseImpl @Inject constructor(
    private val repository
    : AstroRepositoryImpl
):GetDetailByTypeUseCase {

    override suspend operator fun invoke(id: Int): List<AstroDetail> {

        val astroDetail = repository.getAllAstrosDetailBytypeFromBD(id)
        return if (astroDetail.isNotEmpty()) {
            astroDetail
        } else {
            emptyList()
        }
    }

}
