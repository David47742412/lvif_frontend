package com.app.lvif_front_end.router

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.lvif_front_end.view.book.BookScreen
import com.app.lvif_front_end.view.home.HomeScreen
import com.app.lvif_front_end.view.login.LoginScreen
import com.app.lvif_front_end.view.splash.SplashScreen
import com.app.lvif_front_end.viewmodel.splash.MainViewModel

@Composable
fun Router(viewModel: MainViewModel = hiltViewModel()) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController, viewModel)
        }

        composable("login") {
            LoginScreen(navController, mainViewModel = viewModel)
        }

        composable("home") {
            HomeScreen(navController, viewModel)
        }

        composable("book") {
            BookScreen('c')
        }
    }
}