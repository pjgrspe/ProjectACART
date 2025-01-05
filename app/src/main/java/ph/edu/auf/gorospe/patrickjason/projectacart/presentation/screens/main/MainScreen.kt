package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.HistorySection
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.MapArea
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.SearchSection
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.TopBar
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.AppTheme
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize().background(AppTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        TopBar()
        SearchSection(context)
        MapArea(context)
        HistorySection()
    }
}
