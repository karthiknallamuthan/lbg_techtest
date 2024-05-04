package com.lbg.techassest.data.di

import com.lbg.techassest.data.local.FavoriteMoviesLocalDataSourceImpl
import com.lbg.techassest.data.local.IFavoriteMoviesLocalDataSource
import com.lbg.techassest.data.remote.IMoviesRemoteDataSource
import com.lbg.techassest.data.remote.IMoviesService
import com.lbg.techassest.data.remote.MoviesRemoteDataSource
import com.lbg.techassest.data.remote.MoviesService
import com.lbg.techassest.data.remote.MoviesServiceImpl
import com.lbg.techassest.data.repository.IMoviesRepository
import com.lbg.techassest.data.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesModule {

    //Remote
    @Singleton
    @Binds
    abstract fun provideMovieServices(
        moviesServiceImpl: MoviesServiceImpl
    ): IMoviesService

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(
        remoteDataSource: MoviesRemoteDataSource
    ): IMoviesRemoteDataSource

    @Singleton
    @Binds
    abstract fun provideMoviesRepository(
        moviesRepositoryImpl: MoviesRepository
    ): IMoviesRepository


    //Local

    @Singleton
    @Binds
    abstract fun provideMoviesLocalDataSource(
        moviesLocalDataSourceImpl: FavoriteMoviesLocalDataSourceImpl
    ): IFavoriteMoviesLocalDataSource

}


@Module
@InstallIn(SingletonComponent::class)
object MoviesModuleObj {

    @Singleton
    @Provides
    fun provideMoviesService(
        retrofit: Retrofit
    ): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }
}

