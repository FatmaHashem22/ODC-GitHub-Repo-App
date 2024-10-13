package com.example.odcgithubrepoapp.presentation.screens.issues_screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun IssueItemTimeDetails(
    timeStatus : String,
    time : String
) {
    Text(
        text = timeStatus,
        style = MaterialTheme.typography.titleSmall
    )
    Text(
        text = time,
        color = MaterialTheme.colorScheme.onSurface,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(start = 4.dp)
    )
}