package com.lbg.techassest.usecases

import com.lbg.techassest.data.repository.IMoviesRepository
import com.lbg.techassest.domain.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository
) {
    suspend operator fun invoke(
        query: String,
        api_key: String,
        language: String
    ) = moviesRepository.searchMovie(query, api_key, language).map {
        it.results.toDomainModel()
    }
}
