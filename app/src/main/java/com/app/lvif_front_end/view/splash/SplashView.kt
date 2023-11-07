package com.app.lvif_front_end.view.splash

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.app.lvif_front_end.viewmodel.splash.MainViewModel

@Composable
fun SplashScreen(navController: NavHostController, viewModel: MainViewModel) {
    val user by viewModel.users.observeAsState()

    LaunchedEffect(user) {
        Log.i("test-user", (user?.size ?: 0).toString())
        if (user != null) {
            val isLogin = if (user?.size != 0) "home" else "login"
            navController.navigate(isLogin)
        }
    }
}