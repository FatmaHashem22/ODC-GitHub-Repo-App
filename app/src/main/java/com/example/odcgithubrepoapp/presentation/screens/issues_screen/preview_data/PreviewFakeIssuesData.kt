package com.example.odcgithubrepoapp.presentation.screens.issues_screen.preview_data

import com.example.odcgithubrepoapp.presentation.model.CustomRemoteExceptionUiModel
import com.example.odcgithubrepoapp.presentation.screens.issues_screen.model.GithubIssuesUiModel
import com.example.odcgithubrepoapp.presentation.screens.issues_screen.model.IssuesUiState

val fakeIssuesList = listOf(
    GithubIssuesUiModel(
        id = 1,
        title = "Steps for pre-trained REALM models",
        state = "open",
        author = "NONE",
        createdAt = "2024-09-02T13:13:53Z"
    ),
    GithubIssuesUiModel(
        id = 2,
        title = "Bump nltk from 3.7 to 3.9 in /language/fruit",
        state = "open",
        author = "NONE",
        createdAt = "2024-08-20T16:29:04Z"
    ),
    GithubIssuesUiModel(
        id = 3,
        title = "Bump keras from 2.10.0 t…13.1 in /language/fruit",
        state = "open",
        author = "NONE",
        createdAt = 	"2024-08-02T16:13:08Z"
    ),
    GithubIssuesUiModel(
        id = 4,
        title = "Bump tensorflow from 2.4…/language/search_agents",
        state = "open",
        author = "NONE",
        createdAt = "2024-07-30T21:29:36Z"
    ),
    GithubIssuesUiModel(
        id = 5,
        title = 	"Bump tensorflow from 2.1…12.1 in /language/fruit",
        state = "open",
        author = "NONE",
        createdAt = 	"2024-07-30T21:11:06Z"
    ),
)

val fakeIssuesUiState = IssuesUiState(
    isLoading = false,
    isError = false,
    issuesList = fakeIssuesList
)

val fakeIssuesLoadingUiState = IssuesUiState(
    isLoading = true
)

val fakeIssuesErrorUiState = IssuesUiState(
    isLoading = false,
    isError = true,
    customRemoteExceptionUiModel = CustomRemoteExceptionUiModel.NoInternetConnection
)