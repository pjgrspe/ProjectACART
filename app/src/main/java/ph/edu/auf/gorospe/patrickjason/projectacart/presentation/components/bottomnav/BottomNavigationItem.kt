package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.bottomnav

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val title: String,
    val badgeCount: Int? = null
)