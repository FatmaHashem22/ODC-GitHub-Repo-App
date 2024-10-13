package com.example.odcgithubrepoapp.domain.usecase

import com.example.odcgithubrepoapp.domain.model.GithubIssuesDomainModel
import com.example.odcgithubrepoapp.domain.repository.GithubIssuesRepository
import javax.inject.Inject

class FetchIssuesListUseCase @Inject constructor(
    private val githubIssuesRepository: GithubIssuesRepository
) {
    suspend operator fun invoke(
        ownerName:String, name:String
    ) : List<GithubIssuesDomainModel> {
        return githubIssuesRepository.fetchIssuesList(ownerName,name)
    }
}