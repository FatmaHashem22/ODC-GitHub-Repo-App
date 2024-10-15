package com.example.odcgithubrepoapp.di

import android.content.Context
import com.example.odcgithubrepoapp.data.data_sources.local.GithubLocalDataSource
import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
import com.example.odcgithubrepoapp.data.repository.GithubIssuesRepositoryImpl
import com.example.odcgithubrepoapp.data.repository.GithubReposRepositoryImpl
import com.example.odcgithubrepoapp.domain.repository.GithubIssuesRepository
import com.example.odcgithubrepoapp.domain.repository.GithubReposRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubReposRepository(
        githubRemoteDataSource: GithubRemoteDataSource,
        localDataSource: GithubLocalDataSource,
        @ApplicationContext context : Context
    ): GithubReposRepository {
        return GithubReposRepositoryImpl(githubRemoteDataSource, localDataSource,context)
    }

    @Provides
    @Singleton
    fun provideGithubIssuesRepository(
        githubRemoteDataSource: GithubRemoteDataSource,
        localDataSource: GithubLocalDataSource
    ) : GithubIssuesRepository {
        return GithubIssuesRepositoryImpl(githubRemoteDataSource,localDataSource)
    }
}