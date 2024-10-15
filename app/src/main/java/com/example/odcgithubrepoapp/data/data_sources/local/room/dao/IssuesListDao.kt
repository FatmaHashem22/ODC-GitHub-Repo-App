package com.example.odcgithubrepoapp.data.data_sources.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.IssuesListEntity


@Dao
interface IssuesListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIssuesList(issuesList : List<IssuesListEntity>)

    @Query("SELECT * FROM IssuesListEntity")
    suspend fun getIssuesList() : List<IssuesListEntity>
}