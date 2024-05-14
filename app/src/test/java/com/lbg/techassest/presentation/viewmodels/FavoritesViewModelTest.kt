package com.lbg.techassest.presentation.viewmodels

import com.lbg.techassest.usecases.GetFavoriteMoviesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoritesViewModelTest {

    @Mock
    lateinit var getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
    private lateinit var sut: FavoritesViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = FavoritesViewModel(getFavoriteMoviesUseCase)
    }

    @After
    fun tearDown() {
    }
}