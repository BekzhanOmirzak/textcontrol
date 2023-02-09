package com.dandelion.textcontrol.fieldOptions

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun FieldOptionsScreen(navController: NavController) {
    Text(text = "Enter first field info")

}

@Preview
@Composable
private fun FieldOptionsScreen_Preview() {
    FieldOptionsScreen(rememberNavController())
}
