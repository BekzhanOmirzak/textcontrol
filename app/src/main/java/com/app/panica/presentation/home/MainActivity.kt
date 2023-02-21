package com.app.panica.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.panica.presentation.ui.theme.PanicaTheme
import com.app.panica.presentation.utils.Route
import com.app.panica.R

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {

		}

	}
}


@Composable
fun MainBottomNavigationBar(
	items: List<BottomNavItem>, navController: NavController
) {
	val backStackEntryState = navController.currentBackStackEntryAsState()

	BottomNavigation(
		modifier = Modifier.fillMaxWidth(),
		backgroundColor = Color.White
	) {
		items.forEach {
			val selected = it.route == backStackEntryState.value?.destination?.route
			BottomNavigationItem(modifier = Modifier.fillMaxSize(), onClick = {
				if (!selected) {
					navController.navigate(it.route)
				}
			}, icon = {
				if (selected) {

				} else {

				}
			}, selected = selected
			)
		}
	}
}


@Composable
@Preview(showBackground = true)
fun HomeScreen() {
	PanicaTheme {

		val navController = rememberNavController()
		val items = listOf(
			BottomNavItem(Route.SEARCH, "Поиск", R.drawable.btm_ic_search),
			BottomNavItem(Route.SAVED, "Избранное", R.drawable.btm_ic_heart),
			BottomNavItem(Route.ADD_LOST_ITEMS, "Обьявления", R.drawable.btm_ic_add_announce),
			BottomNavItem(Route.MESSAGE_CHAT, "Сообщение", R.drawable.btm_ic_message),
			BottomNavItem(Route.PROFILE, "Профиль", R.drawable.btm_ic_profile),
		)
		// A surface container using the 'background' color from the theme
		Surface(
			modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
		) {
			Scaffold(bottomBar = {
				MainBottomNavigationBar(
					items = items, navController = navController
				)
			}) {

			}
		}

	}
}

