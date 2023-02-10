package com.dandelion.textcontrol.ui.screens.options

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.godaddy.android.colorpicker.HsvColor

class FieldOptionsVM : ViewModel() {
    val fieldCount = mutableStateOf(0)
    val fieldOptions = mutableListOf<FieldOptions>()
    var currentFieldOption = FieldOptions()

    val isInputEnabled = mutableStateOf(true)
    val content = mutableStateOf("")
    val fontSize = mutableStateOf(10)
    val textColor = mutableStateOf(HsvColor.DEFAULT)
    val hintText = mutableStateOf("")
    val hintTextColor = mutableStateOf(HsvColor.DEFAULT)
    val background = mutableStateOf(HsvColor.DEFAULT)
    val borderWidth = mutableStateOf(0.dp)
    val borderColor = mutableStateOf(Color.Transparent)
    val shapeRadius = mutableStateOf(0.dp)
    val positionX = mutableStateOf(null) // if null then not apply
    val positionY = mutableStateOf(null)
    val lineSpacing = mutableStateOf(0.dp)
    val lineCount = mutableStateOf(0)
    val isScrollable = mutableStateOf(false)
    val padding = mutableStateOf(PaddingValues(0.dp))
    val alignment = mutableStateOf("")
    val underlineWidth = mutableStateOf(0.dp)
    val underlineColor = mutableStateOf(Color.Black)
    val width = mutableStateOf(0.dp)
    val height = mutableStateOf(0.dp)
    val elevationColor = mutableStateOf(Color.White)
    val elevationOffset = mutableStateOf(0f)

    fun applyField() {
        fieldOptions.add(currentFieldOption)
        isInputEnabled.value = true
        content.value = ""
        currentFieldOption = FieldOptions()
    }
}
