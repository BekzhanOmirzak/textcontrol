package com.dandelion.textcontrol.ui.screens.options

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.godaddy.android.colorpicker.HsvColor
import com.godaddy.android.colorpicker.HsvColor.Companion

class FieldOptionsVM : ViewModel() {
    val fieldCount = mutableStateOf(0)
    val fieldOptions = mutableListOf<FieldOptions>()
    var currentFieldOption = FieldOptions()

    val isInputEnabled = mutableStateOf(true)   // +
    val content = mutableStateOf("")            // +
    val fontSize = mutableStateOf(10)           // +
    val textColor = mutableStateOf(HsvColor.DEFAULT)  // +
    val hintText = mutableStateOf("")           // +
    val hintTextColor = mutableStateOf(HsvColor.DEFAULT) // +
    val background = mutableStateOf(HsvColor.DEFAULT) // +
    val borderWidth = mutableStateOf(0)         // +
    val borderColor = mutableStateOf(HsvColor.DEFAULT) // +
    val shapeRadius = mutableStateOf(0)         // +
    val positionX = mutableStateOf("")          // +
    val positionY = mutableStateOf("")          // +
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
        fontSize.value = 10
        textColor.value = HsvColor.DEFAULT
        hintText.value = ""
        hintTextColor.value = Companion.DEFAULT
        background.value = Companion.DEFAULT
        borderWidth.value = 0
        borderColor.value = Companion.DEFAULT
        shapeRadius.value = 0
        positionX.value = ""
        positionY.value = ""
        currentFieldOption = FieldOptions()
    }
}
