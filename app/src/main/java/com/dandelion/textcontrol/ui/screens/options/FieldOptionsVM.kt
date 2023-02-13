package com.dandelion.textcontrol.ui.screens.options

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.godaddy.android.colorpicker.HsvColor
import com.godaddy.android.colorpicker.HsvColor.Companion

class FieldOptionsVM : ViewModel() {
    val fieldCount = mutableStateOf(0)
    val fieldOptions = mutableListOf<FieldOptions>()
    var currentFieldOption = FieldOptions()

    val isInputEnabled = mutableStateOf(true)        // +
    val content = mutableStateOf("")                 // +
    val fontSize = mutableStateOf(10)                // +
    val textColor = mutableStateOf(HsvColor.DEFAULT)       // +
    val hintText = mutableStateOf("")                // +
    val hintTextColor = mutableStateOf(HsvColor.DEFAULT)   // +
    val background = mutableStateOf(HsvColor.DEFAULT)      // +
    val borderWidth = mutableStateOf(0)             // +
    val borderColor = mutableStateOf(HsvColor.DEFAULT)     // +
    val shapeRadius = mutableStateOf(0)             // +
    val positionX = mutableStateOf("")              // +
    val positionY = mutableStateOf("")              // +
    val lineSpacing = mutableStateOf(0)             // +
    val lineCount = mutableStateOf(1)               // +
    val isScrollable = mutableStateOf(false)        // +
    val paddingStart = mutableStateOf(0)            // +
    val paddingTop = mutableStateOf(0)              // +
    val paddingEnd = mutableStateOf(0)              // +
    val paddingBottom = mutableStateOf(0)           // +
    val isStartAlignment = mutableStateOf(true)     // +
    val isCenterAlignment = mutableStateOf(false)   // +
    val isEndAlignment = mutableStateOf(false)      // +
    val underlineWidth = mutableStateOf(0)          // +
    val underlineColor = mutableStateOf(HsvColor.DEFAULT) // +
    val width = mutableStateOf("")                  // +
    val height = mutableStateOf("")                 // +
    val elevationColor = mutableStateOf(HsvColor.DEFAULT) // +
    val elevationOffset = mutableStateOf(0)         // +
    val isRegularFont = mutableStateOf(true)        // +
    val maximumCharacters = mutableStateOf("")      // +
    val executionDelay = mutableStateOf("")         // +
    val isPassword = mutableStateOf(false)          // +
    val isNumberKeyboard = mutableStateOf(false)    // +

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
        lineSpacing.value = 0
        lineCount.value = 1
        isScrollable.value = false
        paddingStart.value = 0
        paddingTop.value = 0
        paddingEnd.value = 0
        paddingBottom.value = 0
        isStartAlignment.value = true
        isCenterAlignment.value = false
        isEndAlignment.value = false
        underlineWidth.value = 0
        underlineColor.value = Companion.DEFAULT
        width.value = ""
        height.value = ""
        elevationColor.value = Companion.DEFAULT
        elevationOffset.value = 0
        isRegularFont.value = true
        maximumCharacters.value = ""
        executionDelay.value = ""
        isPassword.value = false
        isNumberKeyboard.value = false
        currentFieldOption = FieldOptions()
    }
}
