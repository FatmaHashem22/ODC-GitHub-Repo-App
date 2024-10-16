package com.example.odcgithubrepoapp.data.data_sources.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity

@Dao
interface RepoListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReposList(repoList: List<ReposListEntity>)

    @Query("SELECT * FROM ReposListEntity")
    suspend fun getReposList(): List<ReposListEntity>

    @Update
    suspend fun updateReposList(repoList: List<ReposListEntity>)
}