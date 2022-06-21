package com.soringpenguin.ui_foodentry.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.soringpenguin.food_domain.Food

@Composable
fun FoodListItem(
    food: Food,
    onSelectFood: (Int) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .background(MaterialTheme.colors.surface)
            .clickable {
                onSelectFood(food.id)
            },
        elevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                text = food.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}