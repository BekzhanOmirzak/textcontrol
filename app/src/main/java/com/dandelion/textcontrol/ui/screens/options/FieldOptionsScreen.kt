package com.dandelion.textcontrol.ui.screens.options

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chargemap.compose.numberpicker.NumberPicker
import com.dandelion.textcontrol.navigation.RESULT_SCREEN
import com.dandelion.textcontrol.ui.theme.steagalFontMedium
import com.dandelion.textcontrol.ui.theme.steagalFontRegular
import com.dandelion.textcontrol.utils.toOrdinal
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor
import java.lang.NumberFormatException
import kotlinx.coroutines.launch

@Composable
fun FieldOptionsScreen(
	navController: NavController,
	fieldCount: Int,
	vm: FieldOptionsVM = viewModel()
) {

	var addedFieldCount by remember { mutableStateOf(0) }
	val scrollState = rememberScrollState()
	val coroutineScope = rememberCoroutineScope()
	val keyboardController = LocalFocusManager.current

	vm.fieldCount.value = fieldCount
	Column(
		Modifier
			.fillMaxSize()
			.background(Color.White)
			.verticalScroll(scrollState),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			modifier = Modifier.padding(top = 16.dp),
			text = "Enter ${(addedFieldCount + 1).toOrdinal()} field info"
		)
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
			ClassicColorPicker(
				Modifier.size(200.dp),
				color = vm.background.value,
				onColorChanged = { color: HsvColor ->
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
			}, range = (0..20))
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
			Column(
				modifier = Modifier.fillMaxWidth(),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
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
		Row(
			Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Text(text = "lineSpace")
			NumberPicker(value = vm.lineSpacing.value, onValueChange = {
				vm.currentFieldOption.lineSpacing = it.sp
				vm.lineSpacing.value = it
			}, range = (0..50))
		}
		Row(
			Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Text(text = "lineCount")
			NumberPicker(value = vm.lineCount.value, onValueChange = {
				vm.currentFieldOption.lineCount = it
				vm.lineCount.value = it
			}, range = (1..50))
		}
		Row(
			Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Text(text = "isScrollable")
			Checkbox(checked = vm.isScrollable.value, onCheckedChange = {
				vm.currentFieldOption.isScrollable = it
				vm.isScrollable.value = it
			})
		}
		Row(
			Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			var isNumberValid by remember {
				mutableStateOf(true)
			}
			Text(text = "width and height")
			Column(
				modifier = Modifier.fillMaxWidth(),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Row {
					TextField(
						modifier = Modifier
							.width(100.dp)
							.padding(end = 16.dp),
						value = vm.width.value,
						isError = !isNumberValid,
						onValueChange = {
							try {
								vm.currentFieldOption.width = it.toInt().dp
								vm.width.value = it
							} catch (exception: NumberFormatException) {
								isNumberValid = false
							}
						})
					TextField(
						modifier = Modifier
							.width(100.dp)
							.padding(start = 16.dp),
						value = vm.height.value,
						isError = !isNumberValid,
						onValueChange = {
							try {
								vm.currentFieldOption.height = it.toInt().dp
								vm.height.value = it
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
		Row(
			Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Text(text = "padding (start, top, end, bottom)")
			NumberPicker(value = vm.paddingStart.value, onValueChange = {
				vm.currentFieldOption.paddingStart = it.dp
				vm.paddingStart.value = it
			}, range = (0..500))
			NumberPicker(value = vm.paddingTop.value, onValueChange = {
				vm.currentFieldOption.paddingTop = it.dp
				vm.paddingTop.value = it
			}, range = (0..500))
			NumberPicker(value = vm.paddingEnd.value, onValueChange = {
				vm.currentFieldOption.paddingEnd = it.dp
				vm.paddingEnd.value = it
			}, range = (0..500))
			NumberPicker(value = vm.paddingBottom.value, onValueChange = {
				vm.currentFieldOption.paddingBottom = it.dp
				vm.paddingBottom.value = it
			}, range = (0..500))
		}
		Row(
			Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Text(text = "underlineWidth")
			NumberPicker(value = vm.underlineWidth.value, onValueChange = {
				vm.currentFieldOption.underlineWidth = it.dp
				vm.underlineWidth.value = it
			}, range = (1..50))
		}
		Row(
			Modifier
				.fillMaxWidth()
				.padding(vertical = 10.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Text(text = "underlineColor")
			ClassicColorPicker(
				Modifier.size(200.dp),
				color = vm.background.value,
				onColorChanged = { color: HsvColor ->
					vm.currentFieldOption.underlineColor = color.toColor()
					vm.underlineColor.value = color
				})
		}
		Row(
			Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Text(text = "textAlign (start, center, end)")
			Checkbox(checked = vm.isStartAlignment.value, onCheckedChange = {
				vm.currentFieldOption.alignment = TextAlign.Start
				vm.isStartAlignment.value = true
				vm.isCenterAlignment.value = false
				vm.isEndAlignment.value = false
			})
			Checkbox(checked = vm.isCenterAlignment.value, onCheckedChange = {
				vm.currentFieldOption.alignment = TextAlign.Center
				vm.isStartAlignment.value = false
				vm.isCenterAlignment.value = true
				vm.isEndAlignment.value = false
			})
			Checkbox(checked = vm.isEndAlignment.value, onCheckedChange = {
				vm.currentFieldOption.alignment = TextAlign.End
				vm.isStartAlignment.value = false
				vm.isCenterAlignment.value = false
				vm.isEndAlignment.value = true
			})
		}
		Row(
			Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Text(text = "elevation")
			NumberPicker(value = vm.elevationOffset.value, onValueChange = {
				vm.currentFieldOption.elevationOffset = it.dp
				vm.elevationOffset.value = it
			}, range = (0..50))
		}
		Row(
			Modifier
				.fillMaxWidth()
				.padding(vertical = 10.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Text(text = "elevationColor")
			ClassicColorPicker(
				Modifier.size(200.dp),
				color = vm.elevationColor.value,
				onColorChanged = { color: HsvColor ->
					vm.currentFieldOption.elevationColor = color.toColor()
					vm.elevationColor.value = color
				})
		}
		Row(
			Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Text(text = "font (steagal regular, steagal medium)")
			Checkbox(checked = vm.isRegularFont.value, onCheckedChange = {
				vm.currentFieldOption.font = steagalFontRegular
				vm.isRegularFont.value = true
			})
			Checkbox(checked = !vm.isRegularFont.value, onCheckedChange = {
				vm.currentFieldOption.font = steagalFontMedium
				vm.isRegularFont.value = false
			})
		}
		Row(
			Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {

			var isNumberValid by remember {
				mutableStateOf(true)
			}

			Text(text = "maxCharacters")
			TextField(value = vm.maximumCharacters.value, isError = !isNumberValid,
				onValueChange = {
					try {
						vm.currentFieldOption.maxCharacters = it.toInt()
						vm.maximumCharacters.value = it
					} catch (exception: NumberFormatException) {
						isNumberValid = false
					}
				})
			if (!isNumberValid) {
				Text(
					text = "Please, enter valid number",
					color = Color.Red
				)
			}
		}
		Row(
			Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {

			var isNumberValid by remember {
				mutableStateOf(true)
			}

			Text(text = "executionDelay")
			TextField(value = vm.executionDelay.value, isError = !isNumberValid,
				onValueChange = {
					try {
						vm.currentFieldOption.executionDelay = it.toLong()
						vm.executionDelay.value = it
					} catch (exception: NumberFormatException) {
						isNumberValid = false
					}
				})
			if (!isNumberValid) {
				Text(
					text = "Please, enter valid number",
					color = Color.Red
				)
			}
		}

		Column(
			modifier = Modifier
				.padding(10.dp)
				.border(BorderStroke(1.dp, color = Color.Gray))
				.padding(10.dp)
		) {
			Text(text = "URL")
			val targetContainsInContent =
				vm.urlTarget.value.isNotEmpty() && vm.content.value.contains(
					vm.urlTarget.value
				)
			Row(verticalAlignment = Alignment.CenterVertically) {
				OutlinedTextField(
					value = vm.urlTarget.value,
					onValueChange = { input ->
						vm.urlTarget.value = input
						vm.currentFieldOption.urlOptions.target = input
					},
					label = {
						Text(text = "Target")
					}
				)
				Spacer(modifier = Modifier.width(10.dp))
				Box(
					modifier = Modifier
						.background(
							color = if (targetContainsInContent)
								Color.Green else Color.Red
						)
						.clip(
							CircleShape
						)
						.size(8.dp)
				)
			}
			Spacer(modifier = Modifier.height(10.dp))
			if (targetContainsInContent)
				Column(modifier = Modifier) {
					OutlinedTextField(
						value = vm.addressLink.value,
						onValueChange = { input ->
							vm.addressLink.value = input
							vm.currentFieldOption.urlOptions.addressLink = input
						},
						label = {
							Text(text = "Address Link")
						}
					)

					Row(
						Modifier.fillMaxWidth(),
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.SpaceEvenly
					) {
						Text(text = "url fontSize")
						NumberPicker(value = vm.urlFontSize.value, onValueChange = {
							vm.urlFontSize.value = it
							vm.currentFieldOption.urlOptions.fontSize = it.sp
						}, range = (10..90))
					}
					Row(
						Modifier
							.fillMaxWidth()
							.padding(vertical = 10.dp),
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.SpaceEvenly
					) {
						Text(text = "url textColor")
						ClassicColorPicker(
							Modifier.size(200.dp),
							color = vm.urlTxtColor.value,
							onColorChanged = { color: HsvColor ->
								vm.urlTxtColor.value = color
								vm.currentFieldOption.urlOptions.textColor = color.toColor()
							})
					}

					Row(
						Modifier.fillMaxWidth(),
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.SpaceEvenly
					) {
						Text(text = "url underlineWidth")
						NumberPicker(value = vm.urlUnderLineWidth.value, onValueChange = {
							vm.urlUnderLineWidth.value = it
							vm.currentFieldOption.urlOptions.underlineWidth = it.dp
						}, range = (1..50))
					}
					Row(
						Modifier
							.fillMaxWidth()
							.padding(vertical = 10.dp),
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.SpaceEvenly
					) {
						Text(text = "url underlineColor")
						ClassicColorPicker(
							Modifier.size(200.dp),
							color = vm.urlUnderLineColor.value,
							onColorChanged = { color: HsvColor ->
								vm.urlUnderLineColor.value = color
								vm.currentFieldOption.urlOptions.underlineColor = color.toColor()
							})
					}
				}
		}
		Row(
			Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Text(text = "isPassword")
			Checkbox(checked = vm.isPassword.value, onCheckedChange = {
				vm.currentFieldOption.isPassword = it
				vm.isPassword.value = it
			})
		}
		Row(
			Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Text(text = "number keyboard")
			Checkbox(checked = vm.isNumberKeyboard.value, onCheckedChange = {
				vm.currentFieldOption.keyboardType =
					if (it) KeyboardType.NumberPassword else KeyboardType.Text
				vm.isNumberKeyboard.value = it
			})
		}



		Button(onClick = {
			if (vm.fieldCount.value - 1 == addedFieldCount) {
				navController.navigate(RESULT_SCREEN)
				vm.applyField()
			} else {
				println(vm.fieldOptions)
				coroutineScope.launch {
					scrollState.animateScrollTo(0)
				}
				vm.applyField()
				keyboardController.clearFocus()
				addedFieldCount++
			}
		}) {
			Text(text = "Go next!")
		}

	}
}

@Preview(showBackground = true)
@Composable
private fun PreviewTestingUrlSection() {

	var targetTxtForUrl by remember {
		mutableStateOf("")
	}

	var addressLink by remember {
		mutableStateOf("")
	}


}

@Preview(showBackground = true)
@Composable
private fun FieldOptionsScreen_Preview() {
	FieldOptionsScreen(rememberNavController(), 3)
}
