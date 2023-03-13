package com.dandelion.textcontrol.ui.screens.options

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dandelion.textcontrol.ui.theme.steagalFontMedium

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
	var lineSpacing: TextUnit = 0.sp,
	var lineCount: Int = 1,
	var isScrollable: Boolean = false,
	var paddingStart: Dp = 0.dp,
	var paddingTop: Dp = 0.dp,
	var paddingEnd: Dp = 0.dp,
	var paddingBottom: Dp = 0.dp,
	var alignment: TextAlign = TextAlign.Start,
	var underlineWidth: Dp = 0.dp,
	var underlineColor: Color = Color.Black,
	var width: Dp = 100.dp,
	var height: Dp = 50.dp,
	var elevationColor: Color = Color.White,
	var elevationOffset: Dp = 0.dp,
	var font: FontFamily = steagalFontMedium,
	var maxCharacters: Int = 1,
	var executionDelay: Long = 0L,
	var isPassword: Boolean = false,
	var keyboardType: KeyboardType = KeyboardType.Text,
	val urlOptions: UrlOptions = UrlOptions()
)
