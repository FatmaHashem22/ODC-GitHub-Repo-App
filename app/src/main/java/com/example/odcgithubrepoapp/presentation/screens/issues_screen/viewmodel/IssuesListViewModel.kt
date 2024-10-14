package com.example.odcgithubrepoapp.presentation.screens.issues_screen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odcgithubrepoapp.domain.model.CustomRemoteExceptionDomainModel
import com.example.odcgithubrepoapp.domain.usecase.FetchIssuesListUseCase
import com.example.odcgithubrepoapp.presentation.mapper.toCustomExceptionRemoteUiModel
import com.example.odcgithubrepoapp.presentation.mapper.toGithubIssuesUiModel
import com.example.odcgithubrepoapp.presentation.model.CustomRemoteExceptionUiModel
import com.example.odcgithubrepoapp.presentation.screens.issues_screen.model.IssuesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssuesListViewModel @Inject constructor(
    private val githubIssuesListUseCase: FetchIssuesListUseCase
) : ViewModel() {

    private val _issuesStateFlow = MutableStateFlow(IssuesUiState(isLoading = true))
    val issuesStateFlow : StateFlow<IssuesUiState> = _issuesStateFlow.asStateFlow()


    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _issuesStateFlow.value = IssuesUiState(
            isLoading = false,
            isError = true,
            customRemoteExceptionUiModel = (throwable as CustomRemoteExceptionDomainModel).toCustomExceptionRemoteUiModel()
        )
    }

    fun requestGithubIssuesList(ownerName: String, name: String) {
        viewModelScope.launch (Dispatchers.IO + coroutineExceptionHandler) {
            try {
                val issuesList = githubIssuesListUseCase(ownerName,name)
                _issuesStateFlow.value = IssuesUiState(
                    isLoading = false,
                    issuesList = issuesList.map { it.toGithubIssuesUiModel() }
                )
            } catch (e : Exception) {
                Log.d("Error", "CustomRemoteExceptionDomainModel: ${e.message}")
                _issuesStateFlow.value = IssuesUiState(
                    isLoading = false,
                    isError = true,
                    customRemoteExceptionUiModel = (e as CustomRemoteExceptionDomainModel).toCustomExceptionRemoteUiModel()
                )
            }
        }
    }
}