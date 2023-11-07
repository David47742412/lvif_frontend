package com.app.lvif_front_end.view.book

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.lvif_front_end.viewmodel.book.BookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookScreen(action: Char, bookViewModel: BookViewModel = hiltViewModel()) {
    val name by bookViewModel.name.observeAsState("");
    val description by bookViewModel.description.observeAsState("");
    val image by bookViewModel.image.observeAsState();

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { bookViewModel.setValueProps(name = it) },
                label = { Text(text = "Nombre") },
            )
            OutlinedTextField(
                value = description,
                onValueChange = { bookViewModel.setValueProps(description = it) },
                label = { Text(text = "Nombre") },
            )
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {},
                ) {
                    Text(text = "Cargar Archivo")
                }
                Button(
                    onClick = {},
                ) {
                    Text(text = "Tomar Foto")
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun PrevBook() {
    BookScreen('c')
}
