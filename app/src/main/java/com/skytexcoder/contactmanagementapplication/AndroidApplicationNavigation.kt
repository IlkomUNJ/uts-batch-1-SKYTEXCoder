package com.skytexcoder.contactmanagementapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AndroidApplicationNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = "list_contact_screen_composable_route") {
        composable(route = "list_contact_screen_composable_route") {
            ListContactScreen(navigationController = navigationController)
        }

        composable(route = "add_contact_screen_composable_route") {
            AddOREditContactScreen(navigationController = navigationController)
        }

        composable(
            route = "edit_contact_screen_composable_route/{ContactID}",
            arguments = listOf(navArgument("ContactID") { type = NavType.LongType })
        ){ backStackEntry ->
            val ContactID = backStackEntry.arguments?.getLong("ContactID") ?: -1L
            AddOREditContactScreen(navigationController = navigationController, ContactID = ContactID)
        }
    }
}