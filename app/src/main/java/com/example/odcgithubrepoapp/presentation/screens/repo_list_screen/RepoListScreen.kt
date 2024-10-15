package com.example.odcgithubrepoapp.presentation.screens.repo_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.common_component.AppBar
import com.example.odcgithubrepoapp.presentation.common_component.ErrorSection
import com.example.odcgithubrepoapp.presentation.common_component.shimmer.repo_list.AnimateShimmerRepoList
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.component.RepoItem
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.RepoListUiState
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.preview_data.fakeRepoListErrorUiState
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.preview_data.fakeRepoListLoadingUiState
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.preview_data.fakeRepoListUiState
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.viewmodel.RepoListViewModel
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme
import com.example.odcgithubrepoapp.presentation.theme.light_background
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoListScreen(
    onRepoItemClicked: (String, String) -> Unit
) {
//    val swipeToRefresh = rem
    val repoListViewModel: RepoListViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        repoListViewModel.requestGithubRepoList()
    }
    val repoListUiSate by repoListViewModel.repoListStateFlow.collectAsStateWithLifecycle()


    RepoListContent(
        repoListUiSate = repoListUiSate,
        onRefreshButtonClicked = {
            repoListViewModel.requestGithubRepoList()
        },
        onRepoItemClicked = onRepoItemClicked,
        onPullToRefresh = { repoListViewModel.requestGithubRepoList(true) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoListContent(
    modifier: Modifier = Modifier,
    repoListUiSate: RepoListUiState,
    onRefreshButtonClicked: () -> Unit,
    onRepoItemClicked: (String, String) -> Unit,
    onPullToRefresh: () -> Unit
) {

    val pullToRefreshState = rememberPullToRefreshState()
    val isRefreshing = rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            AppBar(
                title = R.string.app_name,
                showBackButton = false
            )
        }
    ) { innerPadding ->
        when {
            repoListUiSate.isLoading -> {
                AnimateShimmerRepoList(
                    innerPadding = innerPadding
                )
            }

            repoListUiSate.isError -> {
                ErrorSection(
                    innerPadding = innerPadding,
                    customErrorExceptionUiModel = repoListUiSate.customRemoteExceptionUiModel,
                    onRefreshButtonClicked = {
                        onRefreshButtonClicked()
                    }
                )
            }

            repoListUiSate.repoList.isNotEmpty() -> {

                PullToRefreshBox(
                    state = pullToRefreshState,
                    isRefreshing = isRefreshing.value,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .background(
                            light_background
                        ),
                    onRefresh = {
                        coroutineScope.launch {
                            isRefreshing.value = true
                            delay(5.seconds)
                            onPullToRefresh()
                            isRefreshing.value = false
                        }
                    }
                ) {
                    LazyColumn(
                        Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp)
                    ) {
                        items(repoListUiSate.repoList) { githubRepoUiModel ->
                            RepoItem(
                                githubRepoUiModel = githubRepoUiModel,
                                onRepoItemClicked = onRepoItemClicked
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
private fun PreviewRepoListScreen() {
    ODCGithubRepoAppTheme {
        RepoListContent(
            repoListUiSate = fakeRepoListUiState,
            onRefreshButtonClicked = {},
            onRepoItemClicked = { _, _ -> },
            onPullToRefresh = {}
        )
    }
}

@Preview
@Composable
private fun PreviewRepoListScreenLoading() {
    ODCGithubRepoAppTheme {
        RepoListContent(
            repoListUiSate = fakeRepoListLoadingUiState,
            onRefreshButtonClicked = {},
            onRepoItemClicked = { _, _ -> },
            onPullToRefresh = {}
        )
    }
}

@Preview
@Composable
private fun PreviewRepoListScreenError() {
    ODCGithubRepoAppTheme {
        RepoListContent(
            repoListUiSate = fakeRepoListErrorUiState,
            onRefreshButtonClicked = {},
            onRepoItemClicked = { _, _ -> },
            onPullToRefresh = {}
        )
    }
}




