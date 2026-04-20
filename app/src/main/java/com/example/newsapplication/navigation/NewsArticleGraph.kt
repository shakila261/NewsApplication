package com.example.newsapplication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.newsapplication.modal.Routes
import com.example.newsapplication.view.NewsArticleScreen


fun NavGraphBuilder.NewsAtricleNavGraph(navController: NavHostController){

  navigation<Routes.NewsArticleGraph>(
      startDestination = Routes.NewsArticleScreen
  )  {
      composable<Routes.NewsArticleScreen>{
          NewsArticleScreen(navController)
      }
  }

}