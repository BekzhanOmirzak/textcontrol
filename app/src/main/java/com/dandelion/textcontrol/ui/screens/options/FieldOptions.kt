package com.dandelion.textcontrol.ui.screens.options

import android.text.Layout.Alignment
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class FieldOptions(
    var isInputEnabled: Boolean = true,
    var content: String = "",
    var fontSize: TextUnit = 10.sp,
    var textColor: Color = Color.Black,
    var hintText: String = "",
    var hintTextColor: Color = Color.Gray,
    var background: Color = Color.White,
    var borderWidth: Dp = 0.dp,
    var borderColor: Color = Color.Transparent,
    var shapeRadius: Dp = 0.dp,
    var positionX: Dp = 0.dp,
    var positionY: Dp = 0.dp,
    var lineSpacing: Dp = 0.dp,
    var lineCount: Int = 0,
    var isScrollable: Boolean = false,
    var padding: PaddingValues = PaddingValues(0.dp),
    var alignment: Alignment? = null,
    var underlineWidth: Dp = 0.dp,
    var underlineColor: Color = Color.Black,
    var width: Dp? = null,
    var height: Dp? = null,
    var elevationColor: Color = Color.White,
    var elevationOffset: Float = 0f
)
