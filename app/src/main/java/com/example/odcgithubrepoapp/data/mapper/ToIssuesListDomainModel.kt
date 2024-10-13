package com.example.odcgithubrepoapp.data.mapper

import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.issues_list.GithubIssuesDataModel
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.issues_list.IssuesItem
import com.example.odcgithubrepoapp.domain.model.GithubIssuesDomainModel

fun IssuesItem.toGithubIssuesListDomainModel() : GithubIssuesDomainModel {
    return GithubIssuesDomainModel(
        id = this.id,
        title = this.title,
        state = this.state,
        author = this.author_association,
        createdAt = this.created_at,
        closedAt = this.closed_at
    )
}