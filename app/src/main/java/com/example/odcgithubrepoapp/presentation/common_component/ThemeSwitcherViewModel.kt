package com.example.odcgithubrepoapp.presentation.common_component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odcgithubrepoapp.domain.usecase.FetchThemePreferencesUseCase
import com.example.odcgithubrepoapp.domain.usecase.SaveThemePreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeSwitcherViewModel @Inject constructor(
    private val fetchThemePreferencesUseCase: FetchThemePreferencesUseCase,
    private val saveThemePreferencesUseCase: SaveThemePreferencesUseCase
) : ViewModel(){

    private val _isDark = MutableStateFlow<Boolean>(false)
    val isDark = _isDark.asStateFlow()

    init{
        viewModelScope.launch {
           _isDark.value = fetchThemePreferencesUseCase().first()
        }
    }


    fun toggleTheme() {
        _isDark.value = !_isDark.value
        viewModelScope.launch {
            saveThemePreferencesUseCase(_isDark.value)
        }
    }
}

