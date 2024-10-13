package com.example.odcgithubrepoapp.presentation.screens.issues_screen.model

data class GithubIssuesUiModel(
    val id: Long,
    val title : String,
    val state : String,
    val author : String,
    val createdAt : String,
    val closedAt : String? = null
)
