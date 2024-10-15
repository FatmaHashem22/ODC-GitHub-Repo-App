package com.example.odcgithubrepoapp.data.mapper

import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel

fun ReposListEntity.toRepoListDomainModel() : GithubReposDomainModel {
    return GithubReposDomainModel(
        id = this.id,
        name = this.name,
        avatar = this.avatar,
        ownerName = this.ownerName,
        description = this.description,
        stars = this.starsCount
    )
}