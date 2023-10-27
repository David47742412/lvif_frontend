package com.app.lvif_front_end.view.home

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.lvif_front_end.viewmodel.home.HomeViewModel

@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val books by homeViewModel.books.observeAsState(emptyList())
    val user by homeViewModel.currentUser.observeAsState()

    LazyColumn {
        items(books) { book ->
            Text(text = book.name)
        }
    }
}