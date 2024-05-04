package com.lbg.techassest.usecases

import com.lbg.techassest.data.repository.IMoviesRepository
import javax.inject.Inject

class DeleteFavoriteMovieUseCase @Inject constructor(
    private val repository: IMoviesRepository
) {
    suspend operator fun invoke(id: Int) = repository.deleteFavoriteMovie(id)
}