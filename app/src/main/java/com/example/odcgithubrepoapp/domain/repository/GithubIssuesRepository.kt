package com.example.odcgithubrepoapp.domain.repository

import com.example.odcgithubrepoapp.domain.model.GithubIssuesDomainModel

interface GithubIssuesRepository {
    suspend fun fetchIssuesList(ownerName: String, name: String) : List<GithubIssuesDomainModel>
}