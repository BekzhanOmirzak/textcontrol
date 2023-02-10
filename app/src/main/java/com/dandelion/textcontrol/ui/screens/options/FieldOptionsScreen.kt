package com.dandelion.textcontrol.ui.screens.options

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

/**
 * 1) add a lazylist for every option of our field
 * 2) create an array of our options in viewmodel with size that we pass from EnterScreen
 * 3) pass resulted array of options to result screen as json and parse it there
 *
 * **/

@Composable
fun FieldOptionsScreen(
    navController: NavController,
    fieldCount: Int,
    vm: FieldOptionsVM = viewModel()
) {
    var addedFieldCount by remember { mutableStateOf(0) }
    vm.fieldCount.value = fieldCount
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
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
            ClassicColorPicker(Modifier.size(200.dp), color = vm.textColor.value, onColorChanged = { color: HsvColor ->
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
            ClassicColorPicker(Modifier.size(200.dp), color = vm.background.value, onColorChanged = { color: HsvColor ->
                vm.currentFieldOption.background = color.toColor()
                vm.background.value = color
            })
        }
        Button(onClick = {
            if (vm.fieldCount.value - 1 == addedFieldCount) {
                // TODO: nav to result screen
                navController.navigate(RESULT_SCREEN)
                vm.applyField()
            } else {
                println(vm.fieldOptions)
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
