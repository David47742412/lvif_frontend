package com.app.lvif_front_end.view.splash

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.lvif_front_end.viewmodel.splash.SplashViewModel

@Composable
fun SplashScreen(navController: NavHostController, viewModel: SplashViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val login by viewModel.users.observeAsState()

    viewModel.getAllUser {
        Toast.makeText(context.applicationContext, "Error Get Users", Toast.LENGTH_SHORT).show()
    }

    LaunchedEffect(login) {
        if (login != null) {
            val isLogin = if (login?.size != 0) "home" else "login"
            navController.navigate(isLogin)
        }
    }
}