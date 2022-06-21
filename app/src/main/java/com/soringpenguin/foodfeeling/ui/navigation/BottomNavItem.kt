package com.soringpenguin.foodfeeling.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    var title:String,
    var icon:ImageVector,
    var screen_route:String
) {

    object Feelings : BottomNavItem("Feelings", Icons.Rounded.Home,"feelingEntry")

    object Food : BottomNavItem("Food", Icons.Rounded.Edit,"feelingEntry")

}
