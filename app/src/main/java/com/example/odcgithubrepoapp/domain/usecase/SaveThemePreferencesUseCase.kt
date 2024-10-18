package com.example.odcgithubrepoapp.domain.usecase

import com.example.odcgithubrepoapp.domain.repository.GithubReposRepository
import javax.inject.Inject

class SaveThemePreferencesUseCase @Inject constructor(
    private val githubReposRepository: GithubReposRepository
) {
    suspend operator fun invoke(themePreference: Boolean) {
        githubReposRepository.saveThemePreference(themePreference)
    }
}
