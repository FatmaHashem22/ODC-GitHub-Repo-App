package com.example.odcgithubrepoapp.presentation.mapper

import com.example.odcgithubrepoapp.domain.model.GithubIssuesDomainModel
import com.example.odcgithubrepoapp.presentation.screens.issues_screen.model.GithubIssuesUiModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun GithubIssuesDomainModel.toGithubIssuesUiModel() : GithubIssuesUiModel {
    return GithubIssuesUiModel(
        id = this.id,
        title = this.title,
        state = this.state,
        author = this.author,
        createdAt = formatDateTime(this.createdAt),
//        createdAt = this.createdAt,
//        closedAt = this.closedAt
        closedAt = if (this.closedAt != null) formatDateTime(this.closedAt) else null
    )
}

private fun formatDateTime(input: String): String {
    return try {

        val inputFormatter = DateTimeFormatter.ISO_DATE_TIME
        val dateTime = LocalDateTime.parse(input, inputFormatter)

        val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a") // Example output format


        dateTime.format(outputFormatter)
    } catch (e: DateTimeParseException) {
        "Invalid date format"
    }
}
