package com.app.lvif_front_end.router

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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

        composable("book?id={id}&name={name}&description={description}&action={action}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType; nullable = true; defaultValue = "" },
                navArgument("name") {
                    type = NavType.StringType; nullable = true; defaultValue = ""
                },
                navArgument("description") {
                    type = NavType.StringType; nullable = true; defaultValue = ""
                },
                navArgument("action") { type = NavType.IntType; defaultValue = 1 }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""
            val action = backStackEntry.arguments?.getInt("action") ?: 1
            BookScreen(
                name = name,
                bookId = id,
                description = description,
                action = action,
                navController = navController,
                mainViewModel = viewModel
            )
        }
    }
}