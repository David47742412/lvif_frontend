package com.app.lvif_front_end.router

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.app.lvif_front_end.di.module.RoomModule
import com.app.lvif_front_end.room.database.LvifDatabase
import com.app.lvif_front_end.view.home.HomeScreen
import com.app.lvif_front_end.view.login.LoginScreen
import com.app.lvif_front_end.view.splash.SplashScreen

@Composable
fun Router() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }

        composable("login") {
            LoginScreen(navController)
        }

        composable("home") {
            HomeScreen(navController)
        }
    }

}