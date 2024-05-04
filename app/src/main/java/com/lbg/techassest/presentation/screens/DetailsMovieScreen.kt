package com.lbg.techassest.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.lbg.techassest.domain.MovieDetailDomain
import com.lbg.techassest.presentation.composables.CustomNoInternetConnectionScreen
import com.lbg.techassest.presentation.composables.CustomErrorScreenSomethingHappens
import com.lbg.techassest.presentation.composables.LoadingScreen
import com.lbg.techassest.usecases.GetDetailsMovieResult

@Composable
fun DetailsMovieScreen(
    navController: NavController,
    stateMovieDetail: GetDetailsMovieResult,
    onClickFavorite: (MovieDetailDomain) -> Unit,
    isFavoriteMovie:Boolean,
) {
    var isLoading by remember { mutableStateOf(false)}
    var isError by remember { mutableStateOf(false)}
    var isSuccess by remember { mutableStateOf(false)}
    var isInternetError by remember { mutableStateOf(false)}

    var item by remember { mutableStateOf(MovieDetailDomain())}


    LaunchedEffect(key1 = stateMovieDetail){
        when(stateMovieDetail) {
            is GetDetailsMovieResult.Success -> {
                isLoading = false
                isError = false
                isInternetError = false
                isSuccess = true
                item = stateMovieDetail.data
            }
            is GetDetailsMovieResult.Error -> {
                isLoading = false
                isSuccess = false
                isError = true
                isInternetError = false
            }
            is GetDetailsMovieResult.Loading -> {
                isError = false
                isSuccess = false
                isInternetError = true
                isLoading = stateMovieDetail.isLoading
            }

            is GetDetailsMovieResult.InternetError -> {
                isLoading = false
                isError = false
                isInternetError = true
                isSuccess = false
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        when{
            isLoading -> {
                LoadingScreen()
            }
            isError -> {
                CustomErrorScreenSomethingHappens()
            }
            isInternetError -> {
                CustomNoInternetConnectionScreen()
            }
            isSuccess -> {
                DetailsMovieContent(
                    onClickBack = {
                        navController.popBackStack()
                    },
                    onClickFavorite = { onClickFavorite(item) },
                    title = item.title?: "",
                    description = item.overview?: "",
                    imagePoster = item.poster_path?: "",
                    isFavoriteMovie = isFavoriteMovie
                )
            }
        }
    }
}

