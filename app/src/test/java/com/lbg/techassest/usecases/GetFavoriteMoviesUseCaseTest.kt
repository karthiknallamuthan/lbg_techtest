package com.lbg.techassest.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lbg.techassest.domain.local.FavoriteMoviesEntity
import com.lbg.techassest.usecases.data.FakeRepositoryErrorApi
import com.lbg.techassest.usecases.data.FakeRepositorySuccessApi
import com.lbg.techassest.usecases.utils.MainCoroutineScopeRule
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetFavoriteMoviesUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineScopeRule = MainCoroutineScopeRule()

    private lateinit var sut: GetFavoriteMoviesUseCase
    private lateinit var sutSuccess: GetFavoriteMoviesUseCase

    private lateinit var fakeMovieRepository: FakeRepositoryErrorApi
    private lateinit var fakeMovieRepositorySuccess: FakeRepositorySuccessApi

    private val listResult = mutableListOf<List<FavoriteMoviesEntity>>()

    @Before
    fun setUp() {
        fakeMovieRepository = FakeRepositoryErrorApi()
        sut = GetFavoriteMoviesUseCase(fakeMovieRepository)

        fakeMovieRepositorySuccess = FakeRepositorySuccessApi()
        sutSuccess = GetFavoriteMoviesUseCase(fakeMovieRepositorySuccess)
    }


    @Test
    fun `should return empty if not exist favorite movies`() {

        val result = sut.invoke()

        runBlocking {
            result.collect {
                listResult.add(it)
            }
        }

        assertEquals(listResult[0].size, 0)
    }

    @Test
    fun `should return list of favorite movies`() {

        val result = sutSuccess.invoke()

        runBlocking {
            result.collect {
                listResult.add(it)
            }
        }

        assertEquals(listResult[0].size, 1)
    }




    @After
    fun tearDown() {
        listResult.clear()
    }

}