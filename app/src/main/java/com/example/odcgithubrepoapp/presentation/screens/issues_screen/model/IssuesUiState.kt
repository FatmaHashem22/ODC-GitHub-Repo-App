package com.example.odcgithubrepoapp.presentation.screens.issues_screen.model

import com.example.odcgithubrepoapp.presentation.model.CustomRemoteExceptionUiModel

data class IssuesUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val customRemoteExceptionUiModel: CustomRemoteExceptionUiModel = CustomRemoteExceptionUiModel.Unknown,
    val issuesList : List<GithubIssuesUiModel> = emptyList()
)
