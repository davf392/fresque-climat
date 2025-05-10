package com.davidfz.animfresque.ui.profile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.davidfz.animfresque.ui.navigation.Routes
import com.davidfz.animfresque.ui.theme.FresqueClimatColors

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = { /* TODO: Handle activate account */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = FresqueClimatColors.Primary)
        ) {
            Text("ACTIVER MON COMPTE", color = Color.White)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Déjà activé ?", color = Color.Gray)
            Text("Vous pouvez vous connecter", color = Color.Gray)
        }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Votre e-mail") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Votre mot de passe") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { navController.navigate(Routes.PROFILE) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = FresqueClimatColors.Primary)
        ) {
            Text("ME CONNECTER", color = Color.White)
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(modifier = Modifier.background(Color.White))
}