package com.example.githubreposapp.presentation.common_components.shimmer.issues

import android.content.res.Configuration
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme

@Composable
fun AnimateShimmerIssuesList(innerPadding: PaddingValues, theme: Boolean) {
    ODCGithubRepoAppTheme(darkTheme = theme) {
        Column(
            Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.surface)) {
            LazyColumn {
                items(10) {
                    val shimmerColors = listOf(
                        MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
                        MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),
                        MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
                    )

                    val transition =
                        rememberInfiniteTransition(label = "") // to animate our shimmer as long as we want
                    val translateAnim = transition.animateFloat(
                        initialValue = 0f,
                        targetValue = 1000f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(
                                durationMillis = 700,
                                easing = LinearEasing
                            ),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = ""
                    )

                    val brush = Brush.linearGradient(
                        colors = shimmerColors,
                        start = Offset.Zero,
                        end = Offset(x = translateAnim.value, y = translateAnim.value)
                    )

                    IssuesShimmerItem(
                        brush = brush,
                        background = MaterialTheme.colorScheme.secondary,
                        theme = theme
                    )
                }
            }
        }

    }

}

@Composable
fun IssuesShimmerItem(brush: Brush, background: Color, theme: Boolean) {

    ODCGithubRepoAppTheme (darkTheme = theme){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(background.copy(0.2F))
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(brush)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(verticalArrangement = Arrangement.Center) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Spacer(
                        modifier = Modifier
                            .height(14.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth(fraction = 0.7f)
                            .background(brush)
                    )
                    Spacer(
                        modifier = Modifier
                            .height(14.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth(fraction = 0.4f)
                            .background(brush)
                    )
                }
                Spacer(modifier = Modifier.padding(6.dp))
                Spacer(
                    modifier = Modifier
                        .height(14.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth(fraction = 0.3f)
                        .background(brush)
                )
                Spacer(modifier = Modifier.padding(6.dp))

                Spacer(
                    modifier = Modifier
                        .height(14.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth(fraction = 0.6f)
                        .background(brush)
                )
            }
        }
        Divider()
    }


}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ShimmerItemPreview() {
    ODCGithubRepoAppTheme(darkTheme = true) {
        IssuesShimmerItem(
            brush = Brush.linearGradient(
                listOf(
                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),
                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
                )
            ),
            background = MaterialTheme.colorScheme.secondary,
            theme = true
        )
    }

}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ShimmerItemListPreview() {
    ODCGithubRepoAppTheme(darkTheme = true) {
        AnimateShimmerIssuesList(
            innerPadding = PaddingValues(12.dp),
            theme = true
        )
    }

}