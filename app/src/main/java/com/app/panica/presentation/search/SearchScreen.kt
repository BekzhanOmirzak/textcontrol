package com.app.panica.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.app.panica.R
import com.app.panica.presentation.ui.theme.Bcg_Txt_Field_Search
import com.app.panica.presentation.ui.theme.Checked_Blue
import com.app.panica.presentation.ui.theme.Hint_Color
import com.app.panica.presentation.utils.Route


@Composable
fun SearchScreen(navigate: (String) -> Unit) {

	Column(modifier = Modifier.fillMaxSize()) {
		ConstraintLayout(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 15.dp, start = 10.dp, end = 10.dp)
		) {
			val (edt_search, img_google) = createRefs()
			Box(
				modifier = Modifier
					.constrainAs(edt_search) {
						end.linkTo(img_google.start)
						start.linkTo(parent.start)
						top.linkTo(parent.top)
						bottom.linkTo(parent.bottom)
						width = Dimension.fillToConstraints
						height = Dimension.fillToConstraints
					}
					.background(color = Bcg_Txt_Field_Search, shape = RoundedCornerShape(10.dp))
			) {
				SearchEditText("Поиск в Алмате", modifier = Modifier.align(Alignment.CenterStart)) {

				}
			}
			Image(painter = painterResource(id = R.drawable.ic_google_map),
				contentDescription = "Icon Google Maps",
				modifier = Modifier
					.constrainAs(img_google) {
						end.linkTo(parent.end)
					}
					.clickable {
						navigate(Route.SEARCH_IN_MAP)
					}
			)
		}
	}

}


@Preview(showBackground = true)
@Composable
fun ShowPreviewOfSearchScreen() {
	SearchScreen {

	}
}

@Composable
fun SearchEditText(hint: String, modifier: Modifier = Modifier, onSearch: (String) -> Unit) {

	var text by remember { mutableStateOf("") }
	var isHintDisplayed by remember {
		mutableStateOf(hint != "")
	}

	Box(modifier = modifier) {
		BasicTextField(
			value = text,
			onValueChange = {
				text = it
				onSearch(it)
			},
			maxLines = 1,
			singleLine = true,
			textStyle = TextStyle(color = Color.Black),
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 10.dp, vertical = 5.dp)
				.onFocusChanged {
					isHintDisplayed = !it.isFocused
				}
		)
		if (isHintDisplayed) {
			Row(modifier = Modifier.padding(top = 2.dp, start = 10.dp)) {
				Image(
					painter = painterResource(id = R.drawable.btm_ic_search),
					contentDescription = null,
					modifier = Modifier
						.width(20.dp)
						.height(20.dp)
						.padding(top = 2.dp)
				)
				Spacer(modifier = Modifier.width(7.dp))
				Text(
					text = hint,
					color = Hint_Color,
					modifier = Modifier.fillMaxWidth()
				)
			}
		}
	}

}


@Composable
fun Experiment() {

	ConstraintLayout(modifier = Modifier.fillMaxSize()) {

		val leftGuidLine = createGuidelineFromAbsoluteLeft(0.01f)
		val rightGuideLine = createGuidelineFromAbsoluteRight(0.01f)
		val topGuidLine = createGuidelineFromTop(0.01f)
		val middleGuidLine = createGuidelineFromTop(0.45f)

		val surface = createRef()

		Box(modifier = Modifier
			.background(color = Checked_Blue)
			.constrainAs(surface) {
				top.linkTo(topGuidLine)
				start.linkTo(leftGuidLine)
				end.linkTo(rightGuideLine)
				bottom.linkTo(middleGuidLine)
				width = Dimension.fillToConstraints
				height = Dimension.fillToConstraints
			}) {

		}
		val (profileImg, notificationImg, middleImg) = createRefs()

		Image(painter = painterResource(id = R.drawable.btm_ic_profile),
			contentDescription = null,
			modifier = Modifier
				.constrainAs(profileImg) {
					top.linkTo(topGuidLine, margin = 10.dp)
					start.linkTo(leftGuidLine)
				}
				.padding(start = 10.dp))
		Image(painter = painterResource(id = R.drawable.btm_ic_message),
			contentDescription = null,
			modifier = Modifier
				.constrainAs(notificationImg) {
					top.linkTo(profileImg.top)
					bottom.linkTo(profileImg.bottom)
					end.linkTo(rightGuideLine, margin = 10.dp)
				}
				.padding(end = 10.dp))
		Image(painter = painterResource(id = R.drawable.btm_ic_heart),
			contentDescription = null,
			modifier = Modifier.constrainAs(middleImg) {
				top.linkTo(profileImg.top)
				bottom.linkTo(profileImg.bottom)
			})
		createHorizontalChain(
			profileImg, middleImg, notificationImg, chainStyle = ChainStyle.SpreadInside
		)

	}

}


