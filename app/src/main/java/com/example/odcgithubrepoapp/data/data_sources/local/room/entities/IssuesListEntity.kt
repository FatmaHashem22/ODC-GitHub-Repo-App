package com.example.odcgithubrepoapp.data.data_sources.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IssuesListEntity(
    @PrimaryKey(autoGenerate = false)
    val id : Long,
    val title : String,
    val status : String,
    val author : String,
    val createdAt : String,
    val closedAt : String?
)