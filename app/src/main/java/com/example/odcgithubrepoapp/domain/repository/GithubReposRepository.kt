package com.example.odcgithubrepoapp.domain.repository

import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.IssuesListEntity
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import com.example.odcgithubrepoapp.domain.model.GithubIssuesDomainModel
import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel
import com.example.odcgithubrepoapp.domain.model.RepoDetailsDomainModel

interface GithubReposRepository {
    suspend fun fetchReposList(isForcedRefresh : Boolean): List<GithubReposDomainModel>
    suspend fun fetchRepoDetails(ownerName: String, name: String): RepoDetailsDomainModel
    suspend fun getCachedReposList(): List<ReposListEntity>
    suspend fun insertRepos(repoList: List<ReposListEntity>)
    suspend fun insertIssuesList(issuesList : List<IssuesListEntity>)
    suspend fun getIssuesList() : List<IssuesListEntity>

}