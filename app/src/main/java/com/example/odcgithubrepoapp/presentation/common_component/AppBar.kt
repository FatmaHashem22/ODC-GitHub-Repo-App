package com.example.odcgithubrepoapp.presentation.common_component

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme
import com.example.odcgithubrepoapp.presentation.theme.dark_purple

@ExperimentalMaterial3Api
@Composable
fun AppBar(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = true,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge,
    onBackButtonClicked: () -> Unit = {},
    onDarkThemeChange : () -> Unit,
    isDarkTheme : Boolean
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(title),
                style = titleStyle,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = dark_purple
        ),
        navigationIcon = {
            if (showBackButton) {
                IconButton(
                    onClick = { onBackButtonClicked() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = {
                    onDarkThemeChange()
                    Log.d("Theme","Theme = $isDarkTheme")
                }
            ) {
                Icon(
                    imageVector = if(isDarkTheme) {
                        Icons.Filled.LightMode
                    } else {
                        Icons.Filled.DarkMode
                    },
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun PreviewAppBar() {
      ODCGithubRepoAppTheme {
          AppBar(
              title = R.string.app_name,
              showBackButton = true,
              onDarkThemeChange = { },
              isDarkTheme = true
          )
      }
}