package com.app.lvif_front_end.view.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.lvif_front_end.ui.components.book.CardScreen
import com.app.lvif_front_end.viewmodel.splash.MainViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: MainViewModel) {
    val books by viewModel.books.observeAsState(emptyList())
    val user by viewModel.currentUser.observeAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            navController.navigate("book")
                        }
                    ) {
                        Text(text = "Crear un nuevo libro")
                    }
                }
            }
        }
        items(books) { book ->
            CardScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(100.dp, 200.dp)
                    .padding(16.dp),
                book = book,
                navController = navController,
                mainViewModel = viewModel,
            )
        }
    }
}