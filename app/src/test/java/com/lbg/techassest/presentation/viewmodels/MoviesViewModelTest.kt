package com.lbg.techassest.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lbg.techassest.domain.toDomainModel
import com.lbg.techassest.usecases.GetPopularMoviesUseCase
import com.lbg.techassest.usecases.PopularMoviesResult
import com.lbg.techassest.usecases.SearchMovieUseCase
import com.lbg.techassest.usecases.data.FakeRepositorySuccessApi
import com.lbg.techassest.usecases.fakes.FakeValueApi
import com.lbg.techassest.usecases.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    lateinit var fakeRepositorySuccess: FakeRepositorySuccessApi

    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    lateinit var searchMoviesUseCase: SearchMovieUseCase

    private lateinit var sut: MoviesViewModel

    private val listResult = mutableListOf<PopularMoviesResult>()

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineScopeRule = MainCoroutineScopeRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        fakeRepositorySuccess = FakeRepositorySuccessApi()
        getPopularMoviesUseCase = GetPopularMoviesUseCase(fakeRepositorySuccess)
        searchMoviesUseCase = SearchMovieUseCase(fakeRepositorySuccess)

        sut = MoviesViewModel(getPopularMoviesUseCase, searchMoviesUseCase)


    }

    @Test
    fun `get popular movies should return different results emitted`() = mainCoroutineScopeRule.runBlockingTest {
        val scope = launch {
            sut.movies.collect(){
                listResult.add(it)
            }
        }

        sut.getPopularMovies()

        val expected = listOf(
            PopularMoviesResult.Loading(true),
            PopularMoviesResult.Loading(false),
        )

        scope.cancel()
        assertNotEquals(expected, listResult)
    }

    @Test
    fun `get popular test first emit should be loading`() = runTest {
        sut.movies.first(){
            listResult.add(it)
        }
        sut.getPopularMovies()
        val expected = PopularMoviesResult.Loading(true)
        assertEquals(expected, listResult.first())
    }

    @Test
    fun `get popular normal test should return success`() = runTest {
        val list = sut.movies.take(2).toList()

        sut.getPopularMovies()

        val expected = listOf(
            PopularMoviesResult.Loading(true),
            PopularMoviesResult.Success(FakeValueApi.listMovieEntityFake().toDomainModel()),
        )
        assertEquals(expected, list)
    }


    @After
    fun tearDown() {
        listResult.clear()
        mainCoroutineScopeRule.coroutineContext.cancelChildren()
    }
}