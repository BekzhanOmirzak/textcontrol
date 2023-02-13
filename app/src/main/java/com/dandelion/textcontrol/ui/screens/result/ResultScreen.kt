package com.dandelion.textcontrol.ui.screens.result

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResultField(options: FieldOptions) {

    var fieldValue by remember {
        mutableStateOf(options.content)
    }

    with(options) {
        Card(
            modifier = Modifier.offset(positionX, positionY),
            border = BorderStroke(borderWidth, borderColor)
        ) {
            TextField(
                value = fieldValue,
                onValueChange = {
                    if (it.length <= maxCharacters) {
                        Thread.sleep(executionDelay)
                        fieldValue = it
                        Log.d("ResultScreen input", it)
                    }
                },
                enabled = isInputEnabled,
                textStyle = TextStyle.Default.copy(
                    fontSize = fontSize,
                    color = textColor,
                    lineHeight = lineSpacing,
                    textAlign = alignment
                ),
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
                maxLines = lineCount,
                placeholder = { Text(text = hintText, color = hintTextColor) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                modifier = Modifier
                    .background(background, RoundedCornerShape(shapeRadius))
                    .padding(paddingStart, paddingTop, paddingEnd, paddingBottom)
                    .then(if (height == 0.dp) Modifier else Modifier.height(height))
                    .then(if (width == 0.dp) Modifier else Modifier.width(width))
                    .verticalScroll(rememberScrollState(), isInputEnabled)
                    .shadow(elevationOffset, spotColor = elevationColor)
                    .indicatorLine(
                        enabled = true,
                        focusedIndicatorLineThickness = underlineWidth,
                        unfocusedIndicatorLineThickness = underlineWidth,
                        isError = false,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = underlineColor,
                            unfocusedIndicatorColor = underlineColor,
                            disabledIndicatorColor = underlineColor
                        ),
                        interactionSource = MutableInteractionSource()
                    )
            )
        }
    }
}

@Preview
@Composable
fun ResultScreen_Preview() {
    ResultScreen(rememberNavController())
}
