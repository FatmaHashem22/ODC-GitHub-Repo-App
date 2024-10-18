package com.example.odcgithubrepoapp.presentation.screens.issues_screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme

@Composable
fun IssueItemTimeDetails(
    timeStatus: String,
    time: String,
    theme: Boolean,
) {
    ODCGithubRepoAppTheme(darkTheme = theme) {
        Text(
            text = timeStatus,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = time,
            color = MaterialTheme.colorScheme.onSurface,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 4.dp)
        )
    }

}