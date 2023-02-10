package com.dandelion.textcontrol.ui.screens.options

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chargemap.compose.numberpicker.NumberPicker
import com.dandelion.textcontrol.navigation.RESULT_SCREEN
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor
import java.lang.NumberFormatException
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun FieldOptionsScreen(
    navController: NavController,
    fieldCount: Int,
    vm: FieldOptionsVM = viewModel()
) {
    var addedFieldCount by remember { mutableStateOf(0) }
    val scrollState = rememberScrollState()
    vm.fieldCount.value = fieldCount
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = Modifier.padding(top = 16.dp), text = "Enter first field info")
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "isEnabledInput")
            Checkbox(checked = vm.isInputEnabled.value, onCheckedChange = {
                vm.currentFieldOption.isInputEnabled = it
                vm.isInputEnabled.value = it
            })
        }
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "content")
            TextField(value = vm.content.value, onValueChange = {
                vm.currentFieldOption.content = it
                vm.content.value = it
            })
        }
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "fontSize")
            NumberPicker(value = vm.fontSize.value, onValueChange = {
                vm.currentFieldOption.fontSize = it.sp
                vm.fontSize.value = it
            }, range = (10..90))
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "textColor")
            ClassicColorPicker(
                Modifier.size(200.dp),
                color = vm.textColor.value,
                onColorChanged = { color: HsvColor ->
                    vm.currentFieldOption.textColor = color.toColor()
                    vm.textColor.value = color
                })
        }
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "hintText")
            TextField(value = vm.hintText.value, onValueChange = {
                vm.currentFieldOption.hintText = it
                vm.hintText.value = it
            })
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "hintTextColor")
            ClassicColorPicker(
                Modifier.size(200.dp),
                color = vm.hintTextColor.value,
                onColorChanged = { color: HsvColor ->
                    vm.currentFieldOption.hintTextColor = color.toColor()
                    vm.textColor.value = color
                })
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "background")
            ClassicColorPicker(
                Modifier.size(200.dp),
                color = vm.background.value,
                onColorChanged = { color: HsvColor ->
                    vm.currentFieldOption.background = color.toColor()
                    vm.background.value = color
                })
        }
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "borderWidth")
            NumberPicker(value = vm.borderWidth.value, onValueChange = {
                vm.currentFieldOption.borderWidth = it.dp
                vm.borderWidth.value = it
            }, range = (0..10))
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "borderColor")
            ClassicColorPicker(Modifier.size(200.dp), color = vm.background.value, onColorChanged = { color: HsvColor ->
                vm.currentFieldOption.borderColor = color.toColor()
                vm.borderColor.value = color
            })
        }
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "shapeRadius")
            NumberPicker(value = vm.shapeRadius.value, onValueChange = {
                vm.currentFieldOption.shapeRadius = it.dp
                vm.shapeRadius.value = it
            }, range = (1..10))
        }
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            var isNumberValid by remember {
                mutableStateOf(true)
            }
            Text(text = "positionX and positionY")
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Row {
                    TextField(
                        modifier = Modifier
                            .width(100.dp)
                            .padding(end = 16.dp),
                        value = vm.positionX.value,
                        isError = !isNumberValid,
                        onValueChange = {
                            try {
                                vm.currentFieldOption.positionX = it.toInt().dp
                                vm.positionX.value = it
                            } catch (exception: NumberFormatException) {
                                isNumberValid = false
                            }
                        })
                    TextField(
                        modifier = Modifier
                            .width(100.dp)
                            .padding(start = 16.dp),
                        value = vm.positionY.value,
                        isError = !isNumberValid,
                        onValueChange = {
                            try {
                                vm.currentFieldOption.positionY = it.toInt().dp
                                vm.positionY.value = it
                            } catch (exception: NumberFormatException) {
                                isNumberValid = false
                            }
                        })
                }
                if (!isNumberValid) {
                    Text(
                        text = "Please, enter valid number",
                        color = Color.Red
                    )
                }
            }
        }
        Button(onClick = {
            if (vm.fieldCount.value - 1 == addedFieldCount) {
                navController.navigate(RESULT_SCREEN)
                vm.applyField()
            } else {
                println(vm.fieldOptions)
                MainScope().launch {
                    scrollState.scrollTo(0)
                }
                vm.applyField()
                addedFieldCount++
            }
        }) {
            Text(text = "Go next!")
        }
    }
}

@Preview
@Composable
private fun FieldOptionsScreen_Preview() {
    FieldOptionsScreen(rememberNavController(), 3)
}
