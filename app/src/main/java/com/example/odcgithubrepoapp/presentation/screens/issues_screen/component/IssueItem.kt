package com.example.odcgithubrepoapp.presentation.screens.issues_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.screens.issues_screen.model.GithubIssuesUiModel
import com.example.odcgithubrepoapp.presentation.screens.issues_screen.preview_data.fakeIssuesList
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme

@Composable
fun IssueItem(
    githubIssuesUiModel: GithubIssuesUiModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            .padding(12.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_issues),
            contentDescription = null,
            modifier = Modifier.size(50.dp),

            )

        Column(
            modifier = Modifier.padding(start = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(
                    text = githubIssuesUiModel.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = githubIssuesUiModel.state,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
            Text(
                text = githubIssuesUiModel.author,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleSmall
            )
            Row {
                if(githubIssuesUiModel.closedAt != null) {
                    IssueItemTimeDetails(
                        timeStatus = stringResource(R.string.issue_closed_at),
                        time = githubIssuesUiModel.closedAt
                    )
                } else {
                    IssueItemTimeDetails(
                        timeStatus = stringResource(R.string.issue_created_at),
                        time = githubIssuesUiModel.createdAt
                    )
                }

            }
        }

    }
}

@Preview
@Composable
private fun PreviewIssueItem() {
    ODCGithubRepoAppTheme {
        IssueItem(
            githubIssuesUiModel = fakeIssuesList.first()
        )
    }

}