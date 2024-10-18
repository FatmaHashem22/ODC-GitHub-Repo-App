package com.example.odcgithubrepoapp.domain.usecase

import com.example.odcgithubrepoapp.domain.repository.GithubReposRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchThemePreferencesUseCase @Inject constructor(
    private val githubReposRepository: GithubReposRepository
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return githubReposRepository.getThemePreference()
    }
}