package com.app.panica.presentation.utils

import androidx.navigation.NavHostController


fun NavHostController.navigateTo(route: String) {
	if (route == Route.POP_BACK_STACK)
		popBackStack()
	else
		navigate(route)
}
