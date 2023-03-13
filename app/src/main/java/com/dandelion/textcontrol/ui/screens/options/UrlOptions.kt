package com.dandelion.textcontrol.ui.screens.options

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class UrlOptions(
	val target: String = "",
	val addressLink: String = "https://www.google.com/",
	var fontSize: TextUnit = 10.sp,
	var textColor: Color = Color.Black,
	var underlineWidth: Dp = 0.dp,
	var underlineColor: Color = Color.Black,
)
