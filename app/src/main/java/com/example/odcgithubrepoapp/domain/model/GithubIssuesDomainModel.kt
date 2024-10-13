package com.example.odcgithubrepoapp.domain.model

data class GithubIssuesDomainModel (
    val id: Long,
    val title : String,
    val state : String,
    val author : String,
    val createdAt : String,
    val closedAt : String? = null
)