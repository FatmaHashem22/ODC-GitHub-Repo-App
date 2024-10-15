package com.example.odcgithubrepoapp.data.data_sources.local

import com.example.odcgithubrepoapp.data.data_sources.local.data_store.DataStorePreference
import com.example.odcgithubrepoapp.data.data_sources.local.room.dao.IssuesListDao
import com.example.odcgithubrepoapp.data.data_sources.local.room.dao.RepoListDao
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.IssuesListEntity
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubLocalDataSource @Inject constructor(
    private val repoListDao: RepoListDao,
    private val issuesListDao: IssuesListDao,
    private val dataStorePreference: DataStorePreference,
) {
    suspend fun getCachedReposList(): List<ReposListEntity> {
        return repoListDao.getReposList()
    }

    suspend fun insertRepos(repoList: List<ReposListEntity>) {
        repoListDao.insertReposList(repoList)
    }

    suspend fun insertIssuesList(issuesList : List<IssuesListEntity>){
        issuesListDao.insertIssuesList(issuesList)
    }

    suspend fun getIssuesList() : List<IssuesListEntity> {
        return issuesListDao.getIssuesList()
    }

    fun readIfFirstTimeEnterApp() : Flow<Boolean> {
        return dataStorePreference.readIsFirstTimeEnterApp()
    }
    suspend fun saveIsFirstTimeEnterApp() {
        dataStorePreference.saveIsFirstTimeEnterApp()
    }

}