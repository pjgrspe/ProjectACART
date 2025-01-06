import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.HistorySection
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.MapArea
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.SearchSection
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components.TopBar
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.AppTheme.typography
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.GrayMedium
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.PrimaryDarker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberBottomSheetScaffoldState()
    var bottomSheetContent by remember { mutableStateOf<Pair<String, String>?>(null) }
    var destination by remember { mutableStateOf("") }

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetContent = {
            bottomSheetContent?.let { content ->
                BottomSheetContent(
                    context = context,
                    time = content.first,
                    route = content.second
                )
            }
        },
        sheetPeekHeight = 0.dp
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF7E8))
        ) {
            item { Spacer(modifier = Modifier.height(16.dp))
                TopBar() }
            item { SearchSection(context, destination) { address ->
                destination = address
            } }
            item {
                MapArea(context) { address ->
                    destination = address
                }
            }
            item {
                HistorySection(onColumnClick = { time, route ->
                    coroutineScope.launch {
                        bottomSheetContent = time to route
                        sheetState.bottomSheetState.expand() // Show the bottom sheet
                    }
                })
            }
        }
    }
}

@Composable
fun BottomSheetContent(context: Context, time: String, route: String) {
    val travelDurationMinutes = 25 // Replace this with the actual calculation
    val travelDurationSeconds = 5 // Replace this with the actual calculation

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Trip Details",
            style = typography.h3,
            color = PrimaryDarker,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = time,
                style = typography.h1,
                fontSize = 40.sp,
                color = PrimaryDarker
            )
            Text(
                text = "$travelDurationMinutes minutes and $travelDurationSeconds seconds",
                style = typography.subtitle2,
                color = GrayMedium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Point A (Departure)
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = "Departure",
                modifier = Modifier.size(36.dp),
                tint = PrimaryDarker
            )
            Text(
                text = "Angeles University Foundation",
                style = typography.body2,
                color = PrimaryDarker,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Vertical Line (connecting Point A and Point B)
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(100.dp)
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Point B (Arrival)
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = "Arrival",
                modifier = Modifier.size(36.dp),
                tint = PrimaryDarker
            )
            Text(
                text = "SM City Clark",
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
    BottomSheetContent(LocalContext.current, "14:05", "AUF to SM City Clark")
}
