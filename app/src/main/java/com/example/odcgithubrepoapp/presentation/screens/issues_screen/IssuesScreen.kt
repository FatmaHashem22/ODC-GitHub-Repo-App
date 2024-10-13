package com.example.odcgithubrepoapp.presentation.screens.issues_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.githubreposapp.presentation.common_components.shimmer.issues.AnimateShimmerIssuesList
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.common_component.AppBar
import com.example.odcgithubrepoapp.presentation.common_component.ErrorSection
import com.example.odcgithubrepoapp.presentation.screens.issues_screen.component.IssueItem
import com.example.odcgithubrepoapp.presentation.screens.issues_screen.model.IssuesUiState
import com.example.odcgithubrepoapp.presentation.screens.issues_screen.preview_data.fakeIssuesErrorUiState
import com.example.odcgithubrepoapp.presentation.screens.issues_screen.preview_data.fakeIssuesLoadingUiState
import com.example.odcgithubrepoapp.presentation.screens.issues_screen.preview_data.fakeIssuesUiState
import com.example.odcgithubrepoapp.presentation.screens.issues_screen.viewmodel.IssuesListViewModel
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme

@Composable
fun IssuesScreen(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    ownerName: String,
    name: String
) {

    val issuesViewModel: IssuesListViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        issuesViewModel.requestGithubIssuesList(ownerName,name)
    }

    val issuesUiState by issuesViewModel.issuesStateFlow.collectAsStateWithLifecycle()

    IssuesContent(
        issuesUiState = issuesUiState,
        onRefreshButtonClicked = {
            issuesViewModel.requestGithubIssuesList(ownerName,name)
        },
        onClickBack = onClickBack
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IssuesContent(
    modifier: Modifier = Modifier,
    issuesUiState: IssuesUiState,
    onRefreshButtonClicked: () -> Unit,
    onClickBack: () -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            AppBar(
                title = R.string.issues_app_bar_title,
                onBackButtonClicked = onClickBack
            )
        }
    ) { innerPadding ->

        when {
            issuesUiState.isLoading -> {
                AnimateShimmerIssuesList(
                    innerPadding = innerPadding
                )
            }

            issuesUiState.isError -> {
                ErrorSection(
                    innerPadding = innerPadding,
                    customErrorExceptionUiModel = issuesUiState.customRemoteExceptionUiModel,
                    onRefreshButtonClicked = {
                        onRefreshButtonClicked()
                    }
                )
            }

            issuesUiState.issuesList.isNotEmpty() -> {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    LazyColumn(
                        Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp)
                    ) {
                        items(issuesUiState.issuesList) { githubIssuesUiModel ->
                            IssueItem(
                                githubIssuesUiModel = githubIssuesUiModel
                            )
                        }
                    }
                }
            }
        }


    }
}

@Preview
@Composable
private fun PreviewIssuesScreen() {
    ODCGithubRepoAppTheme {
        IssuesContent(
            issuesUiState = fakeIssuesUiState,
            onRefreshButtonClicked = {},
            onClickBack = {}
        )
    }
}

@Preview
@Composable
private fun PreviewIssuesScreenLoading() {
    ODCGithubRepoAppTheme {
        IssuesContent(
            issuesUiState = fakeIssuesLoadingUiState,
            onRefreshButtonClicked = {},
            onClickBack = {}
        )
    }
}

@Preview
@Composable
private fun PreviewIssuesScreenError() {
    ODCGithubRepoAppTheme {
        IssuesContent(
            issuesUiState = fakeIssuesErrorUiState,
            onRefreshButtonClicked = {},
            onClickBack = {}
        )
    }
}