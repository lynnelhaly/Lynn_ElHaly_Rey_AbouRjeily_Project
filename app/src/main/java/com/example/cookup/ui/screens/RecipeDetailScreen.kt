package com.example.cookup.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cookup.data.viewmodel.RecipeDetailViewModel

@Composable
fun RecipeDetailScreen(
    navController: NavController,
    viewModel: RecipeDetailViewModel,
    modifier: Modifier = Modifier
) {
    val recipe by viewModel.recipe.collectAsState()

    if (recipe == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val current = recipe!!
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text("Image Placeholder", color = Color.DarkGray)
        }

        Column(modifier = Modifier.padding(20.dp)) {

            Text(
                text = current.title,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEEF7FE), RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Column {
                    Text("Category: ${current.category}")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Cooking Time: ${current.cookingTimeMinutes} min")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.toggleFavorite(!current.isFavorite) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    if (current.isFavorite) "★ Remove from Favorites"
                    else "☆ Add to Favorites"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (!current.dietaryTags.isNullOrEmpty()) {
                Text("Dietary Tags", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                val tags = current.dietaryTags.split(",").map { it.trim() }

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(tags) { tag ->
                        Text(
                            text = tag,
                            modifier = Modifier
                                .background(Color(0xFFEBF4FF), RoundedCornerShape(20.dp))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            Text(
                "Instructions",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
            )

            Spacer(modifier = Modifier.height(12.dp))

            val steps = current.instructions
                .split("\n")
                .filter { it.isNotBlank() }

            steps.forEachIndexed { index, step ->
                StepItem(number = index + 1, text = step)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun StepItem(number: Int, text: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "$number.",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}
