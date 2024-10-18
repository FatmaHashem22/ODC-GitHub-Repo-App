package com.example.odcgithubrepoapp.data.repository

import android.content.Context
import android.util.Log
import com.example.odcgithubrepoapp.data.data_sources.local.GithubLocalDataSource
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.IssuesListEntity
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
import com.example.odcgithubrepoapp.data.mapper.toGithubReposDomainModel
import com.example.odcgithubrepoapp.data.mapper.toRepoDetailsDomainModel
import com.example.odcgithubrepoapp.data.mapper.toRepoEntity
import com.example.odcgithubrepoapp.data.mapper.toRepoListDomainModel
import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel
import com.example.odcgithubrepoapp.domain.model.RepoDetailsDomainModel
import com.example.odcgithubrepoapp.domain.repository.GithubReposRepository
import com.example.odcgithubrepoapp.presentation.utils.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GithubReposRepositoryImpl @Inject constructor(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val githubLocalDataSource: GithubLocalDataSource,
    private val context: Context,
): GithubReposRepository {
    override suspend fun fetchReposList(isForcedRefresh : Boolean): List<GithubReposDomainModel> {

        val isFirstTime = githubLocalDataSource.readIfFirstTimeEnterApp().first()


        if(isFirstTime){
            Log.d("Cached","DataStore isFirstTime true")
            val remoteRepos = githubRemoteDataSource.fetchRepositoriesList().items
            githubLocalDataSource.insertRepos(remoteRepos.map { it.toRepoEntity() })
            githubLocalDataSource.saveIsFirstTimeEnterApp()
            return remoteRepos.map { it.toGithubReposDomainModel() }

        } else {
            Log.d("Cached","DataStore isFirstTime false")

            if (NetworkUtils.isNetworkAvailable(context) && isForcedRefresh) {
                Log.d("Cached","Internet Connected call api")
                val remoteRepos = githubRemoteDataSource.fetchRepositoriesList().items
                githubLocalDataSource.updateReposList(remoteRepos.map { it.toRepoEntity() })
                return remoteRepos.map { it.toGithubReposDomainModel() }
            } else {
                Log.d("Cached","Internet NOT Connected fetch cache")
                val cachedRepos = githubLocalDataSource.getCachedReposList()
                return cachedRepos.map { it.toRepoListDomainModel() }
            }
//            val cachedRepos = githubLocalDataSource.getCachedReposList()
//            if (cachedRepos.isNotEmpty()) {
//                Log.d("Cached","$cachedRepos")
//                return cachedRepos.map {
//                    it.toRepoListDomainModel()
//                }
//            } else {
//                Log.d("Cached","Didn't cache")
//                val remoteRepos = githubRemoteDataSource.fetchRepositoriesList().items
//                githubLocalDataSource.insertRepos(remoteRepos.map { it.toRepoEntity() })
//                return remoteRepos.map { it.toGithubReposDomainModel() }
//            }
        }

    }

    override suspend fun fetchRepoDetails(ownerName: String, name: String): RepoDetailsDomainModel {
        return githubRemoteDataSource.fetchRepoDetails(ownerName, name).toRepoDetailsDomainModel()
    }

    override suspend fun getThemePreference(): Flow<Boolean> {

        return githubLocalDataSource.readIsDarkTheme()

    }


    override suspend fun saveThemePreference(isDark: Boolean) {
        githubLocalDataSource.saveThemePreference(isDark)
    }

    override suspend fun getCachedReposList(): List<ReposListEntity> {
        return githubLocalDataSource.getCachedReposList()
    }

    override suspend fun insertRepos(repoList: List<ReposListEntity>) {
        githubLocalDataSource.insertRepos(repoList)
    }

    override suspend fun insertIssuesList(issuesList: List<IssuesListEntity>) {
        githubLocalDataSource.insertIssuesList(issuesList)
    }

    override suspend fun getIssuesList(): List<IssuesListEntity> {
        return githubLocalDataSource.getIssuesList()
    }


}