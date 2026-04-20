package com.example.newsapplication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.newsapplication.modal.Routes
import com.example.newsapplication.view.NewsArticleScreen
import com.example.newsapplication.view.NewsScreen


fun NavGraphBuilder.NewsNavGraph(navController: NavHostController) {


    navigation<Routes.NewsNavGraph>(
        startDestination = Routes.newsScreen
    ) {

        composable<Routes.newsScreen> {
            NewsScreen(navController)
        }



        NewsAtricleNavGraph(navController)


    }


}







