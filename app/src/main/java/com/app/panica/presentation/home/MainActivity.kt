package com.app.panica.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.panica.presentation.ui.theme.PanicaTheme
import com.app.panica.presentation.utils.Route
import com.app.panica.R
import com.app.panica.presentation.search.SearchScreen
import com.app.panica.presentation.ui.theme.Checked_Blue
import com.app.panica.presentation.utils.navigateTo

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			PanicaTheme {

				val navController = rememberNavController()
				val items = listOf(
					BottomNavItem(Route.SEARCH, "Поиск", R.drawable.btm_ic_search),
					BottomNavItem(Route.SAVED, "Избранное", R.drawable.btm_ic_heart),
					BottomNavItem(
						Route.ADD_FOUND_ITEMS,
						"Обьявления",
						R.drawable.btm_ic_add_announce
					),
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

						NavHost(navController = navController, startDestination = Route.SEARCH) {
							composable(Route.SEARCH) {
								SearchScreen(navigate = navController::navigateTo)
							}
							composable(Route.SAVED) {

							}
							composable(Route.MESSAGE_CHAT) {

							}
							composable(Route.PROFILE) {

							}
							composable(Route.ADD_FOUND_ITEMS) {

							}
							composable(
								Route.SEARCH_IN_MAP
							) {

							}
						}
					}
				}

			}
		}

	}
}


@Composable
fun MainBottomNavigationBar(
	items: List<BottomNavItem>, navController: NavController
) {
	val backStackEntryState = navController.currentBackStackEntryAsState()
	BottomNavigation(
		modifier = Modifier.fillMaxWidth(), backgroundColor = Color.White
	) {
		items.forEach {
			val selected = it.route == backStackEntryState.value?.destination?.route
			BottomNavigationItem(modifier = Modifier.fillMaxSize(), onClick = {
				if (!selected) {
					if (navController.currentBackStackEntry?.id != Route.SEARCH) {

						navController.popBackStack()
					}
					navController.navigate(it.route)
				}
			}, icon = {
				Column(horizontalAlignment = Alignment.CenterHorizontally) {
					Image(
						painter = painterResource(id = it.icon),
						modifier = Modifier
							.height(25.dp)
							.width(25.dp),
						contentDescription = null,
						colorFilter = if (selected) ColorFilter.tint(Checked_Blue) else null
					)
					Spacer(modifier = Modifier.height(3.dp))
					Text(
						text = it.label,
						fontSize = 11.sp,
						maxLines = 1,
						overflow = TextOverflow.Ellipsis,
						color = if (selected) Checked_Blue else Color.Unspecified
					)
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
			BottomNavItem(Route.ADD_FOUND_ITEMS, "Обьявления", R.drawable.btm_ic_add_announce),
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

