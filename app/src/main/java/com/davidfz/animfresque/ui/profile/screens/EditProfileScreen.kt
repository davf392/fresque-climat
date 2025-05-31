package com.davidfz.animfresque.ui.profile.screens

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage

@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    BackHandler { navController.popBackStack() }
    var text by rememberSaveable { mutableStateOf("Text") }

    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            EditableProfilePicture(
                modifier = Modifier
                    .size(125.dp)
                    .padding(8.dp)
            )
        }
        Column(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            OutlinedTextField(
                value = "John",
                singleLine = true,
                onValueChange = { text = it },
                label = { Text("Prénom") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "Doe",
                singleLine = true,
                onValueChange = { text = it },
                label = { Text("Nom de famille") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "johndoe@gmail.com",
                singleLine = true,
                onValueChange = { text = it },
                label = { Text("E-Mail") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "Paris",
                singleLine = true,
                onValueChange = { text = it },
                label = { Text("Ville") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}

@Composable
fun EditableProfilePicture(modifier: Modifier = Modifier) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri = uri }
    )

    Card(
        modifier = modifier
            .clip(RoundedCornerShape(60.dp))
            .clickable {
                imagePickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
    ) {
        AsyncImage(
            model = selectedImageUri,
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp) // Adjust height as needed
        )
    }
}

@Preview
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(
        modifier = Modifier.background(Color.White),
        navController = rememberNavController()
    )
}