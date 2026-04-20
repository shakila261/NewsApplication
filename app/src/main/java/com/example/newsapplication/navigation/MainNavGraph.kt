package com.example.newsapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.newsapplication.modal.Routes


@Composable
fun MainNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.NewsNavGraph
    ) {
        NewsNavGraph(navController)

    }

}