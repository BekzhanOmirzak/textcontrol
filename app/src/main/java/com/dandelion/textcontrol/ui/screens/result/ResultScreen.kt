package com.dandelion.textcontrol.ui.screens.result

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dandelion.textcontrol.ui.screens.options.FieldOptionsVM

@Composable
fun ResultScreen(navController: NavController, vm: FieldOptionsVM = viewModel()) {
    Text(text = vm.fieldOptions.toString())
}

@Preview
@Composable
fun ResultScreen_Preview() {
    ResultScreen(rememberNavController())
}
