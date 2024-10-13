package com.example.odcgithubrepoapp.presentation.mapper

import com.example.odcgithubrepoapp.domain.model.GithubIssuesDomainModel
import com.example.odcgithubrepoapp.presentation.screens.issues_screen.model.GithubIssuesUiModel
import java.time.Instant
import java.time.format.DateTimeFormatter

fun GithubIssuesDomainModel.toGithubIssuesUiModel() : GithubIssuesUiModel {
    return GithubIssuesUiModel(
        id = this.id,
        title = this.title,
        state = this.state,
        author = this.author,
        createdAt = formatDate(this.createdAt),
        closedAt = if (this.closedAt != null) formatDate(this.closedAt) else null
    )
}

private fun formatDate(isoDate: String): String {
    val instant = Instant.parse(isoDate)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return formatter.format(instant)

}