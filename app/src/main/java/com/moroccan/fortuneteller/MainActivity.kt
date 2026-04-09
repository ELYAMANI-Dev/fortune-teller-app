package com.moroccan.fortuneteller

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moroccan.fortuneteller.ui.screens.ChatScreen
import com.moroccan.fortuneteller.ui.screens.HoroscopeScreen
import com.moroccan.fortuneteller.ui.screens.TarotScreen
import com.moroccan.fortuneteller.ui.theme.BrandBottomNav
import com.moroccan.fortuneteller.ui.theme.BrandGray
import com.moroccan.fortuneteller.ui.theme.BrandPrimary
import com.moroccan.fortuneteller.ui.theme.BrandWhite
import com.moroccan.fortuneteller.ui.theme.FortuneTellerTheme
import com.moroccan.fortuneteller.viewmodel.FortuneTellerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )
        super.onCreate(savedInstanceState)
        setContent {
            FortuneTellerTheme {
                FortuneTellerRoot()
            }
        }
    }
}

@Composable
private fun FortuneTellerRoot() {
    val vm: FortuneTellerViewModel = viewModel()
    var selectedTab by remember { mutableIntStateOf(0) }

    val tabs = listOf(
        Triple("Chat", Icons.Default.Chat, "Chat"),
        Triple("Horoscope", Icons.Default.Stars, "Horoscope"),
        Triple("Tarot", Icons.Default.AutoAwesome, "Tarot")
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(containerColor = BrandBottomNav) {
                tabs.forEachIndexed { index, (label, icon, _) ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = BrandPrimary,
                            selectedTextColor = BrandPrimary,
                            unselectedIconColor = BrandGray,
                            unselectedTextColor = BrandGray,
                            indicatorColor = BrandBottomNav
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (selectedTab) {
                0 -> ChatScreen(vm)
                1 -> HoroscopeScreen(vm)
                2 -> TarotScreen(vm)
            }
        }
    }
}
