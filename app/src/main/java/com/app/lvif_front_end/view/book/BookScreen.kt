package com.app.lvif_front_end.view.book

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.lvif_front_end.viewmodel.book.BookViewModel
import com.app.lvif_front_end.viewmodel.splash.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookScreen(
    action: Int,
    bookId: String,
    name: String,
    description: String,
    navController: NavController,
    mainViewModel: MainViewModel,
    bookViewModel: BookViewModel = hiltViewModel()
) {
    val nameViewModel by bookViewModel.name.observeAsState(name);
    val descriptionViewModel by bookViewModel.description.observeAsState(description);

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(top = 20.dp))
            OutlinedTextField(
                value = nameViewModel,
                onValueChange = { bookViewModel.setName(name = it) },
                label = { Text(text = "Nombre") },
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            OutlinedTextField(
                value = descriptionViewModel,
                onValueChange = { bookViewModel.setDescription(description = it) },
                label = { Text(text = "Descripción") },
            )
            Spacer(modifier = Modifier.padding(bottom = 20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier.padding(end = 10.dp)
                ) {
                    Text(text = "Volver Atras")
                }
                Button(
                    onClick = {
                        bookViewModel.save(
                            bookId = bookId,
                            action = action,
                            mainViewModel = mainViewModel,
                            navController = navController
                        )
                    },
                ) {
                    Text(text = "Guardar")
                }
            }
        }
    }
}
