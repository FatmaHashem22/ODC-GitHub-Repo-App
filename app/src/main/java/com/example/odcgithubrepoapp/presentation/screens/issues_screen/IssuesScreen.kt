package com.example.odcgithubrepoapp.presentation.screens.issues_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.odcgithubrepoapp.presentation.common_component.ThemeSwitcherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IssuesScreen(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    ownerName: String,
    name: String
) {

    val issuesViewModel: IssuesListViewModel = hiltViewModel()
    val themeViewModel : ThemeSwitcherViewModel = hiltViewModel()

    var theme by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        issuesViewModel.requestGithubIssuesList(ownerName,name)
    }

    val issuesUiState by issuesViewModel.issuesStateFlow.collectAsStateWithLifecycle()

    theme = themeViewModel.isDark.collectAsStateWithLifecycle().value

    val toggleTheme = {
        themeViewModel.toggleTheme()
    }

    ODCGithubRepoAppTheme(darkTheme = theme) {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            topBar = {
                AppBar(
                    title = R.string.issues_app_bar_title,
                    onBackButtonClicked = onClickBack,
                    onDarkThemeChange = { toggleTheme() } ,
                    isDarkTheme = theme
                )
            }
        ) { innerPadding ->

            when {
                issuesUiState.isLoading -> {
                    AnimateShimmerIssuesList(
                        innerPadding = innerPadding,
                        theme = theme
                    )
                }

                issuesUiState.isError -> {
                    ErrorSection(
                        innerPadding = innerPadding,
                        customErrorExceptionUiModel = issuesUiState.customRemoteExceptionUiModel,
                        onRefreshButtonClicked = {
                            issuesViewModel.requestGithubIssuesList(ownerName,name)
                        },
                        theme = theme
                    )
                }

                issuesUiState.issuesList.isNotEmpty() -> {
                    IssuesContent(
                        issuesUiState = issuesUiState,
                        innerPadding = innerPadding,
                        theme = theme
                    )
                }
            }


        }
    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IssuesContent(
    issuesUiState: IssuesUiState,
    innerPadding: PaddingValues,
    theme: Boolean
) {

    ODCGithubRepoAppTheme (darkTheme = theme){
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            LazyColumn(
                Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            ) {
                items(issuesUiState.issuesList) { githubIssuesUiModel ->
                    IssueItem(
                        githubIssuesUiModel = githubIssuesUiModel,
                        theme = theme
                    )
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
            innerPadding = PaddingValues(12.dp),
            theme = true
        )
    }
}

@Preview
@Composable
private fun PreviewIssuesScreenLoading() {
    ODCGithubRepoAppTheme {
        IssuesContent(
            issuesUiState = fakeIssuesLoadingUiState,
            innerPadding = PaddingValues(12.dp),
            theme = true
        )
    }
}

@Preview
@Composable
private fun PreviewIssuesScreenError() {
    ODCGithubRepoAppTheme {
        IssuesContent(
            issuesUiState = fakeIssuesErrorUiState,
            innerPadding = PaddingValues(12.dp),
            theme = true
        )
    }
}