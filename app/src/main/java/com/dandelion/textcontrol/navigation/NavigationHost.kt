package com.dandelion.textcontrol.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dandelion.textcontrol.ui.screens.options.FieldOptionsScreen
import com.dandelion.textcontrol.ui.screens.enter.EnterScreen
import com.dandelion.textcontrol.ui.screens.options.FieldOptionsVM
import com.dandelion.textcontrol.ui.screens.result.ResultScreen

const val ENTER_SCREEN = "enter_screen"
const val FIELD_OPTIONS = "field_options/"
const val FIELD_OPTIONS_PARAMETER = "fieldCount"
const val RESULT_SCREEN = "result_screen"

@Composable
fun NavigationHost(navController: NavHostController) {

    val fieldOptionsVM = viewModel<FieldOptionsVM>()

    NavHost(navController = navController, startDestination = ENTER_SCREEN) {
        composable(route = ENTER_SCREEN) {
            EnterScreen(navController)
        }
        composable(
            route = "$FIELD_OPTIONS{$FIELD_OPTIONS_PARAMETER}",
            arguments = listOf(navArgument(FIELD_OPTIONS_PARAMETER) { type = NavType.IntType })
        ) {
            FieldOptionsScreen(
                navController = navController,
                fieldCount = it.arguments?.getInt(FIELD_OPTIONS_PARAMETER) ?: 0,
                fieldOptionsVM
            )
        }
        composable(
            route = RESULT_SCREEN
        ) {
            ResultScreen(
                navController = navController,
                fieldOptionsVM
            )
        }
    }
}
