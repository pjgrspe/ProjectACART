package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.HistorySection
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.MapArea
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.SearchSection
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.TopBar

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFFFF7E8))
    ) {
        TopBar()
        SearchSection()
        MapArea()
        HistorySection()
    }
}
