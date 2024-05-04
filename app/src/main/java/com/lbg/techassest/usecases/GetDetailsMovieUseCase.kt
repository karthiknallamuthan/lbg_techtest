package com.lbg.techassest.usecases

import com.lbg.techassest.data.repository.IMoviesRepository
import com.lbg.techassest.domain.MovieDetailDomain
import com.lbg.techassest.domain.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDetailsMovieUseCase @Inject constructor(
    private val iMoviesRepository: IMoviesRepository
) {
    suspend operator fun invoke(
        api_key: String,
        language: String,
        id: String
    ) = iMoviesRepository.getMovieDetail(api_key, language, id).map {
        it.toDomainModel()
    }
}

sealed class GetDetailsMovieResult {
    data class Loading(val isLoading: Boolean) : GetDetailsMovieResult()
    data class Success(val data: MovieDetailDomain) : GetDetailsMovieResult()
    data class Error(val message:String) : GetDetailsMovieResult()
    data class InternetError(val message:String) : GetDetailsMovieResult()
}

