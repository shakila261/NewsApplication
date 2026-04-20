package com.example.newsapplication.view

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.newsapplication.modal.NewsUIState
import com.example.newsapplication.modal.Routes
import com.example.newsapplication.viewModel.NewsViewModel


@Composable
fun NewsScreen(
    navController: NavHostController
) {
    val vm: NewsViewModel = viewModel()
    val state = vm.news.collectAsState()



    LaunchedEffect(Unit) {

        vm.getNews("us")
    }


    when (val res = state.value) {
        is NewsUIState.Loading -> {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator()
            }
        }

        is NewsUIState.Success -> {

            Column(modifier = Modifier.padding(top = 50.dp)) {

                Text(
                    "TODAYS NEWS",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()

                )


                categoriesBar(vm)

                LazyColumn() {

                    items(res.data.articles) { article ->


                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            onClick = {
                                navController.navigate(Routes.NewsArticleGraph)
                            }
                        ) {


                            Row(modifier = Modifier.fillMaxWidth()) {

                                AsyncImage(
                                    model = article.urlToImage,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(start = 3.dp, top = 1.dp)
                                        .size(100.dp),
                                    contentScale = ContentScale.Crop


                                )
                                Spacer(modifier = Modifier.width(10.dp))

                                Column(
                                    modifier = Modifier,

                                    ) {

                                    Text(
                                        "Title:",
                                        modifier = Modifier.padding(top = 5.dp),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        "${article.title}",
                                        maxLines = 3,
                                        overflow = TextOverflow.Ellipsis,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        "${article.source.name}",
                                        maxLines = 3,
                                        overflow = TextOverflow.Ellipsis,
                                        fontWeight = FontWeight.Bold
                                    )

                                }
                            }

                        }
                    }

                }
            }
        }

        is NewsUIState.Error -> {
            Text("Error: ${res.message}")
        }
    }


}

@Composable
fun categoriesBar(newsViewModel: NewsViewModel) {

    val categories = listOf(
        "GENERAL",
        "BUSINESS",
        "ENTERTAINMENT",
        "HEALTH",
        "SCIENCE",
        "SPORTS",
        "TECHNOLOGY"
    )

    var isSearch by remember { mutableStateOf("") }
    var search by remember { mutableStateOf(false) }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {


        item {

            if (search) {
                OutlinedTextField(
                    value = isSearch,
                    onValueChange = { isSearch = it },
                    modifier = Modifier
                        .padding(5.dp)
                        .border(1.dp, Color.Gray, CircleShape)
                        .clip(CircleShape),
                    trailingIcon = {
                        IconButton(onClick = {
                            search = false
                            if (isSearch.isNotEmpty()) {
                                newsViewModel.getNewsBySearch(isSearch)
                            }
                        }) {
                            Icon(Icons.Default.Search, contentDescription = null)
                        }
                    }
                )
            } else {
                IconButton(onClick = { search = true }) {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
            }
        }


        item {
            Spacer(modifier = Modifier.width(8.dp))
        }


        items(categories) { category ->

            Button(
                onClick = {
                    newsViewModel.getNews("us", category)
                },
                modifier = Modifier.padding(5.dp)
            ) {
                Text(text = category)
            }
        }
    }
}
