package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.HistorySection
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.MapArea
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.SearchSection
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.TopBar
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.AppTheme.typography
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.GrayMedium
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.PrimaryDarker
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.AppTheme
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberBottomSheetScaffoldState()
    var bottomSheetContent by remember { mutableStateOf<Pair<String, String>?>(null) }

    BottomSheetScaffold(
        scaffoldState = sheetState,
            sheetContent = {
            bottomSheetContent?.let { content: Pair<String, String> ->
                BottomSheetContent(time = content.first, route = content.second)
            }
        },
        sheetPeekHeight = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF7E8))
        ) {
            TopBar()
            SearchSection()
            MapArea()
            HistorySection(onColumnClick = { time, route ->
                coroutineScope.launch {
                    bottomSheetContent = time to route
                    sheetState.bottomSheetState.expand() // Show the bottom sheet
                }
            })
        }
    }
}

@Composable
fun BottomSheetContent(time: String, route: String) {

    val travelDurationMinutes = 25 // Replace this with the actual calculation
    val travelDurationSeconds = 5 // Replace this with the actual calculation

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Trip Details",
                style = typography.h3,
                color = PrimaryDarker,
                textAlign = Center
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                time,
                style = typography.h1,
                fontSize = 40.sp,
                color = PrimaryDarker,
                textAlign = TextAlign.Start
            )
            Text(
                "$travelDurationMinutes minutes and $travelDurationSeconds seconds",
                style = typography.subtitle2,
                color = GrayMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Point A (Departure)
            Icon(
                imageVector = Icons.Default.Place, // Use a relevant icon
                contentDescription = "Departure",
                modifier = Modifier.size(36.dp),
                tint = PrimaryDarker
            )
            Text(
                "Angeles University Foundation",
                style = typography.body2,
                color = PrimaryDarker,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Vertical Line (connecting Point A and Point B)
            Box(
                modifier = Modifier
                    .width(2.dp) // Line thickness
                    .height(100.dp) // Line height (adjust based on your layout)
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Point B (Arrival)
            Icon(
                imageVector = Icons.Default.Place, // Use a relevant icon
                contentDescription = "Arrival",
                modifier = Modifier.size(36.dp),
                tint = PrimaryDarker
            )
            Text(
                "SM City Clark",
                style = typography.body2,
                color = PrimaryDarker,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview() {
    BottomSheetContent("14:05", "AUF to SM City Clark")
}


