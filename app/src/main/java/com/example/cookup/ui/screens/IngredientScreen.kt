package com.example.cookup.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cookup.data.model.IngredientEntity
import com.example.cookup.data.viewmodel.IngredientViewModel
import com.example.cookup.ui.theme.NeutralBorder
import com.example.cookup.ui.theme.NeutralSurface
import com.example.cookup.ui.theme.NeutralText
import androidx.compose.foundation.border
import com.example.cookup.navigation.NavRoutes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientScreen(
    navController: NavController,
    viewModel: IngredientViewModel,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.loadIngredients()
    }

    val ingredients by viewModel.allIngredients.collectAsState()
    val selected by viewModel.selectedIngredients.collectAsState()

    var searchText by remember { mutableStateOf("") }

    // Option C gradient
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF8F9FB), // cool light gray
            Color(0xFFEFF2F6)  // soft blue-gray tint
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundBrush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Select ingredients so we can find recipes for you.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = NeutralText.copy(alpha = 0.7f)
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Search field
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                label = { Text("Search ingredients") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NeutralBorder,
                    unfocusedBorderColor = NeutralBorder.copy(alpha = 0.6f),
                    cursorColor = NeutralText,
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Selected chips
            if (selected.isNotEmpty()) {
                Text(
                    text = "Selected",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = NeutralText.copy(alpha = 0.85f)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(selected) { ing ->
                        SelectedIngredientChip(
                            ingredient = ing,
                            onRemove = { viewModel.removeIngredient(ing) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))
            }

            // Filtered list
            val filtered = remember(searchText, ingredients) {
                val q = searchText.trim()
                if (q.isEmpty()) ingredients
                else ingredients.filter { it.name.contains(q, ignoreCase = true) }
            }

            Text(
                text = "All ingredients",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = NeutralText.copy(alpha = 0.8f)
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filtered) { ing ->
                    IngredientCardItem(
                        ingredient = ing,
                        selected = selected.contains(ing),
                        onClick = { viewModel.toggleIngredient(ing) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val ids = selected.joinToString(",") { it.id.toString() }
                    navController.navigate("${NavRoutes.RECIPES}/$ids")
                },
                enabled = selected.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = if (selected.isEmpty())
                        "Select ingredients to continue"
                    else
                        "Find Recipes (${selected.size})"
                )
            }
        }
    }
}

@Composable
fun IngredientCardItem(
    ingredient: IngredientEntity,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (selected) Color.White else NeutralSurface
    val borderColor = if (selected) Color(0xFF8AA08A) else NeutralBorder

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = if (selected) 5.dp else 2.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .border(1.dp, borderColor, RoundedCornerShape(12.dp)), // Added border with Modifier
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(bgColor)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            Text(
                text = ingredient.name,
                style = MaterialTheme.typography.bodyLarge,
                color = NeutralText
            )
        }
    }
}

@Composable
fun SelectedIngredientChip(
    ingredient: IngredientEntity,
    onRemove: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(50),
        color = NeutralSurface,
        shadowElevation = 2.dp,
        modifier = Modifier
            .border(1.dp, NeutralBorder, RoundedCornerShape(50)) // Use Modifier.border() for the border
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = ingredient.name,
                style = MaterialTheme.typography.bodyMedium
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove ingredient",
                modifier = Modifier
                    .size(16.dp)
                    .clickable { onRemove() },
                tint = NeutralText.copy(alpha = 0.7f)
            )
        }
    }
}
