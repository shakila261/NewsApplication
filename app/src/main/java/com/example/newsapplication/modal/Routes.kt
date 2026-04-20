package com.example.newsapplication.modal

import kotlinx.serialization.Serializable

sealed class Routes {

@Serializable
    data object NewsNavGraph: Routes()
    @Serializable
    data object newsScreen:Routes()

    @Serializable
   data object NewsArticleGraph: Routes()

    @Serializable
data object NewsArticleScreen:Routes()


}