package com.example.odcgithubrepoapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.githubreposapp.presentation.screens.repo_details_screen.RepoDetailsScreen
import com.example.odcgithubrepoapp.presentation.screens.issues_screen.IssuesScreen
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.RepoListScreen
import com.example.odcgithubrepoapp.presentation.common_component.ThemeSwitcherViewModel
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.NAME_ARGUMENT_KEY
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.OWNER_ARGUMENT_KEY

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.RepoListScreen.route
    ) {
        composable(route = Screens.RepoListScreen.route) {
            RepoListScreen(
                onRepoItemClicked = { ownerName, name ->
                    navController.navigate(Screens.RepoDetailsScreen.passOwnerAndName(ownerName, name))
                }
            )
        }

        composable(
            route = Screens.RepoDetailsScreen.route,
            arguments = listOf(
                navArgument(OWNER_ARGUMENT_KEY){
                    type = NavType.StringType
                },
                navArgument(NAME_ARGUMENT_KEY){
                    type = NavType.StringType
                }
            )
        ){ navBackStackEntry ->
            val owner = navBackStackEntry.arguments?.getString(OWNER_ARGUMENT_KEY)
            val name = navBackStackEntry.arguments?.getString(NAME_ARGUMENT_KEY)
            if (owner!= null && name != null){
                RepoDetailsScreen(
                    owner = owner,
                    name = name,
                    onShowIssuesClicked = {
                        navController.navigate(Screens.IssuesScreen.passOwnerAndName(owner,name))
                    },
                    onClickBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            route = Screens.IssuesScreen.route,
            arguments = listOf(
                navArgument(OWNER_ARGUMENT_KEY) {
                    type = NavType.StringType
                },
                navArgument(NAME_ARGUMENT_KEY) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val owner = navBackStackEntry.arguments?.getString(OWNER_ARGUMENT_KEY)
            val name = navBackStackEntry.arguments?.getString(NAME_ARGUMENT_KEY)
            if (owner!= null && name != null) {
                IssuesScreen(
                    ownerName = owner,
                    name = name,
                    onClickBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}