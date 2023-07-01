package com.example.astrop.data

import com.example.astrop.data.database.dao.AstroDetailDao
import com.example.astrop.data.database.dao.AstroTypeDao
import com.example.astrop.data.database.dao.DailyImageDao
import com.example.astrop.data.database.entities.DailyImageEntity
import com.example.astrop.data.network.AstroService
import com.example.astrop.domain.model.DailyImage
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AstroRepositoryImplTest {
    private val service: AstroService = mockk(relaxed = true)
    private val astroDetailDao: AstroDetailDao = mockk(relaxed = true)
    private val dailyImageDao: DailyImageDao = mockk(relaxed = true)

    @Mock
    private lateinit var astroTypeDao: AstroTypeDao

    private lateinit var astroRepositoryImpl: AstroRepositoryImpl

    @Before
    fun setUp() {

        astroRepositoryImpl =
            AstroRepositoryImpl(service, astroTypeDao, dailyImageDao, astroDetailDao)
    }

    @Test

    fun `My first testing function `() = runBlocking {
        val expected = listOf(DailyImage("20/06/2023", "test_body", "urlTest", "test"))
        /*coEvery {
            dailyImageDao.geDailyImage()
        } returns listOf(DailyImageEntity(1, "20/06/2023", "test_body", "urlTest", "test"))
        */
        val result = astroRepositoryImpl.getDailyImageFromDB()
       // assertThat(result).isEqualTo(expected)

        coVerify (exactly = 1){
            dailyImageDao.geDailyImage()
        }

    }
}