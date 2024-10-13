package com.example.odcgithubrepoapp.data.repository

import com.example.odcgithubrepoapp.data.data_sources.local.GithubLocalDataSource
import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
import com.example.odcgithubrepoapp.data.mapper.toGithubIssuesListDomainModel
import com.example.odcgithubrepoapp.domain.model.GithubIssuesDomainModel
import com.example.odcgithubrepoapp.domain.repository.GithubIssuesRepository
import javax.inject.Inject

class GithubIssuesRepositoryImpl @Inject constructor(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val githubLocalDataSource: GithubLocalDataSource
) : GithubIssuesRepository{
    override suspend fun fetchIssuesList(ownerName: String, name: String) : List<GithubIssuesDomainModel> {
        return githubRemoteDataSource.fetchIssuesList(ownerName, name).items.map {
            it.toGithubIssuesListDomainModel()
        }
    }
}