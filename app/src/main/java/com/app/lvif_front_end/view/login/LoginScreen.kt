package com.app.lvif_front_end.view.login

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    var emailOrUsername by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        ConstraintLayout(constraintSet()) {
            Text(
                text = "Nombre de usuario o email",
                modifier = Modifier.layoutId("lblEmailOrUsername")
            )
            OutlinedTextField(
                value = emailOrUsername,
                onValueChange = { emailOrUsername = it },
                modifier = Modifier.layoutId("emailOrUsername"),
                label = {
                    Text(text = "Nombre de usuario o email")
                }
            )
            Text(text = "Contraseña", modifier = Modifier.layoutId("lblPassword"))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                Modifier.layoutId("password"),
                label = {
                    Text(text = "Contraseña")
                }
            )
            Button(onClick = { }, modifier = Modifier.layoutId("btnAuth")) {
                Text(text = "Iniciar sesión")
            }
        }
    }

}

@Composable
@Preview(showSystemUi = true)
fun LoginPreview() {
    val navController = rememberNavController();
    LoginScreen(navController)
}

private fun constraintSet(): ConstraintSet {
    return ConstraintSet {
        val lblEmailOrUsername = createRefFor("lblEmailOrUsername")
        val inpEmailOrUsername = createRefFor("emailOrUsername")
        val lblPassword = createRefFor("lblPassword")
        val inpPassword = createRefFor("password")
        val btnLogin = createRefFor("btnAuth")

        constrain(lblEmailOrUsername) {
            top.linkTo(parent.top, 10.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(inpEmailOrUsername) {
            top.linkTo(lblEmailOrUsername.bottom, 20.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(lblPassword) {
            top.linkTo(inpEmailOrUsername.bottom, 20.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(inpPassword) {
            top.linkTo(lblPassword.bottom, 20.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(btnLogin) {
            top.linkTo(inpPassword.bottom, 20.dp)
            start.linkTo(parent.start, 30.dp)
            end.linkTo(parent.end, 30.dp)
            width = Dimension.fillToConstraints

        }
    }
}