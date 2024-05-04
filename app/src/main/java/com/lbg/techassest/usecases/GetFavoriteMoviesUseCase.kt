package com.lbg.techassest.usecases

import com.lbg.techassest.data.repository.IMoviesRepository
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val repository: IMoviesRepository
) {
    operator fun invoke() = repository.getFavoriteMovies()
}