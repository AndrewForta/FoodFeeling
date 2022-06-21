package com.soringpenguin.foodfeeling.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.RestaurantMenu
import androidx.compose.material.icons.rounded.Storage
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument

sealed class Screen(
    val route: String,
    val icon: ImageVector,
    val label: String
) {

    object FeelingEntry: Screen(
        route = "feelingEntry",
        icon = Icons.Rounded.Favorite,
        label = "Feelings"
    )

    object FoodEntry: Screen(
        route = "foodEntry",
        icon = Icons.Rounded.RestaurantMenu,
        label = "Food"
    )

    object History: Screen(
        route = "history",
        icon = Icons.Rounded.Storage,
        label = "Logs"
    )
}
