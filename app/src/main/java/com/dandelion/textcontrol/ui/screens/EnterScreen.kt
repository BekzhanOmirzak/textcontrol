package com.dandelion.textcontrol.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dandelion.textcontrol.navigation.ENTER_SCREEN
import com.dandelion.textcontrol.navigation.FIELD_OPTIONS
import java.lang.NumberFormatException

@Composable
fun EnterScreen(navController: NavController, vm: EnterVM = viewModel()) {

    var fieldCount by remember { vm.fieldCount }
    var isCountValid by remember { mutableStateOf(true) }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Welcome to the Text Control application!")
            Text(text = "Please, enter the count of fields")
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 100.dp)
                    .fillMaxWidth(.75f),
                value = fieldCount,
                onValueChange = { fieldCount = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                isError = !isCountValid
            )
            if (!isCountValid) {
                Text(
                    text = "Please, enter valid number",
                    color = Color.Red
                )
            }
        }
        Button(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .weight(1f, false)
                .fillMaxWidth(.75f),
            onClick = {
                try {
                    isCountValid = true
                    fieldCount.toInt()
                    navController.navigate(FIELD_OPTIONS) {
                        popUpTo(ENTER_SCREEN) { inclusive = true }
                    }
                } catch (exception: NumberFormatException) {
                    isCountValid = false
                }
            }) {
            Text(text = "Edit Fields!")
        }
    }
}

@Preview
@Composable
private fun EnterScreen_Preview() {
    EnterScreen(rememberNavController())
}
