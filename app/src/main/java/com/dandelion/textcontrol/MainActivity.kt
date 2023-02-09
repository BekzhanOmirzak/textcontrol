package com.dandelion.textcontrol

import android.os.Bundle
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.dandelion.textcontrol.navigation.NavigationHost
import com.dandelion.textcontrol.ui.theme.TextControlTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            val navController = rememberNavController()
            TextControlTheme {
                NavigationHost(navController = navController)
            }
        }
    }
}
