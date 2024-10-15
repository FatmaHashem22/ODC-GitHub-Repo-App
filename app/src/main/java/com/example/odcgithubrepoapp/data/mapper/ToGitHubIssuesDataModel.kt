package com.example.odcgithubrepoapp.data.mapper

import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api.IssuesListApi
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.issues_list.GithubIssuesDataModel

suspend fun IssuesListApi.toGithubIssuesDataModel(ownerName : String, name : String) : GithubIssuesDataModel {
    val issues = this.fetchIssuesList(ownerName,name).body()!!
    return GithubIssuesDataModel(issues)
}