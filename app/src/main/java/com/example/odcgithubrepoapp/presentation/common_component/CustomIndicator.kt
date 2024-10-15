package com.example.odcgithubrepoapp.presentation.common_component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.odcgithubrepoapp.presentation.theme.LightGray
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme
import com.example.odcgithubrepoapp.presentation.theme.light_secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomIndicator(scope: BoxScope) {

    CircularProgressIndicator(
        modifier = Modifier
            .width(30.dp), color = light_secondary,
        trackColor = LightGray
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun CustomIndicatorPreview() {

    ODCGithubRepoAppTheme {
//        CustomIndicator()
    }

}