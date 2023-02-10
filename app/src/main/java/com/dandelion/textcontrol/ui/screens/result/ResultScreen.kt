package com.dandelion.textcontrol.ui.screens.result

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dandelion.textcontrol.ui.screens.options.FieldOptions
import com.dandelion.textcontrol.ui.screens.options.FieldOptionsVM

@Composable
fun ResultScreen(navController: NavController, vm: FieldOptionsVM = viewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        repeat(vm.fieldOptions.size) {
            ResultField(vm.fieldOptions[it])
        }
    }
}

@Composable
fun ResultField(options: FieldOptions) {

    var fieldValue by remember {
        mutableStateOf(options.content)
    }

    with(options) {
        Card(
            modifier = Modifier
                .offset(positionX, positionY),
            border = BorderStroke(borderWidth, borderColor)
        ) {
            TextField(
                value = fieldValue,
                onValueChange = { fieldValue = it },
                enabled = isInputEnabled,
                textStyle = TextStyle.Default.copy(
                    fontSize = fontSize,
                    color = textColor
                ),
                placeholder = { Text(text = hintText, color = hintTextColor) },
                modifier = Modifier
                    .background(background, RoundedCornerShape(shapeRadius)),
            )
        }
    }
}

@Preview
@Composable
fun ResultScreen_Preview() {
    ResultScreen(rememberNavController())
}
