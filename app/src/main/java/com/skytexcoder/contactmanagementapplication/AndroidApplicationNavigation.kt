package com.skytexcoder.contactmanagementapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AndroidApplicationNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = "list_contact_screen_composable_route") {
        composable("list_contact_screen_composable_route") {
            ListContactScreen(navigationController = navigationController)
        }

        composable("add_contact_screen_composable_route") {
            AddOREditContactScreen(navigationController = navigationController)
        }
    }
}