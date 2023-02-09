package com.dandelion.textcontrol.fieldOptions

import android.text.Layout.Alignment
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class FieldOptions(
    val isInputEnabled: Boolean = true,
    val content: String = "",
    val fontSize: TextUnit = 10.sp,
    val textColor: Color = Color.Black,
    val hintText: String = "",
    val hintTextColor: Color = Color.Gray,
    val background: Color = Color.White,
    val borderWidth: Dp = 0.dp,
    val borderColor: Color = Color.Transparent,
    val shapeRadius: Dp = 0.dp,
    val positionX: Int? = null, // if null then not apply
    val positionY:Int? = null,
    val lineSpacing: Dp = 0.dp,
    val lineCount: Int = 0,
    val isScrollable: Boolean = false,
    val padding: PaddingValues = PaddingValues(0.dp),
    val alignment: Alignment?,
    val underlineWidth: Dp = 0.dp,
    val underlineColor: Color = Color.Black,
    val width: Dp,
    val height: Dp,
    val elevationColor: Color = Color.White,
    val elevationOffset: Float = 0f
)
