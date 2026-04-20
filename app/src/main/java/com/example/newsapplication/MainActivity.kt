package com.example.newsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapplication.navigation.MainNavGraph
import com.example.newsapplication.ui.theme.NewsApplicationTheme
import com.example.newsapplication.view.NewsScreen
import com.example.newsapplication.viewModel.NewsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate()")
        enableEdgeToEdge()
        setContent {

            MainNavGraph()


        }
    }

    override fun onStart() {
        super.onStart()
        println("onStart()")
    }

    override fun onResume() {
        super.onResume()

        println("onResume()")
    }

    override fun onPause() {
        super.onPause()
        println("onPause()")
    }

    override fun onStop() {
        super.onStop()
        println("onStop()")
    }


    override fun onRestart() {
        super.onRestart()

        println("onRestart()")
    }


    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy()")
    }
}
