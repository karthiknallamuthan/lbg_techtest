package com.lbg.techassest.usecases

import com.lbg.techassest.data.repository.IMoviesRepository
import com.lbg.techassest.domain.local.FavoriteMoviesEntity
import javax.inject.Inject

class InsertFavoriteMovieUseCase @Inject constructor(
    private val repository: IMoviesRepository
) {
    suspend operator fun invoke(favoriteMoviesEntity: FavoriteMoviesEntity) = repository.insertFavoriteMovie(favoriteMoviesEntity)
}