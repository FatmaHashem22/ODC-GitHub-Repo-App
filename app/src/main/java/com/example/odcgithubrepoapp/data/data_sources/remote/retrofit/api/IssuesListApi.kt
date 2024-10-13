package com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api

import com.example.odcgithubrepoapp.data.Constants.Companion.OWNER_KEY
import com.example.odcgithubrepoapp.data.Constants.Companion.REPO_NAME_KEY
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.issues_list.GithubIssuesDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IssuesListApi {
    @GET("repos/{$OWNER_KEY}/{$REPO_NAME_KEY}/issues")
    suspend fun fetchIssuesList(
        @Path("owner") ownerName: String,
        @Path("repo") name: String
    ) : Response<GithubIssuesDataModel>
}