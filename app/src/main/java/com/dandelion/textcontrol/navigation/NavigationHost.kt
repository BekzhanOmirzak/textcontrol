package com.dandelion.textcontrol.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dandelion.textcontrol.fieldOptions.FieldOptionsScreen
import com.dandelion.textcontrol.ui.screens.EnterScreen

const val ENTER_SCREEN = "enter_screen"
const val FIELD_OPTIONS = "field_options"

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ENTER_SCREEN) {
        composable(route = ENTER_SCREEN) {
            EnterScreen(navController)
        }
        composable(route = FIELD_OPTIONS) {
            FieldOptionsScreen(navController)
        }
    }
}
