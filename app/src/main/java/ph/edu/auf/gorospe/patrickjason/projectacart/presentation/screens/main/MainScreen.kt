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
            item { SearchSection(context) }
            item { MapArea(context) }
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
        // Title
        Text(
            text = "Route History Details",
            style = typography.h4,
            color = PrimaryDarker,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Route and Time Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Route:",
                    style = typography.body1,
                    color = GrayMedium
                )
                Text(
                    text = route,
                    style = typography.h6,
                    color = PrimaryDarker
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "Time Taken:",
                    style = typography.body1,
                    color = GrayMedium
                )
                Text(
                    text = "$travelDurationMinutes min $travelDurationSeconds sec",
                    style = typography.h6,
                    color = PrimaryDarker
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Departure and Arrival Points
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Departure Point
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "Departure",
                    modifier = Modifier.size(24.dp),
                    tint = PrimaryDarker
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Angeles University Foundation",
                    style = typography.body1,
                    color = PrimaryDarker
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Vertical Connector
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(50.dp)
                    .background(GrayMedium)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Arrival Point
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "Arrival",
                    modifier = Modifier.size(24.dp),
                    tint = PrimaryDarker
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "SM City Clark",
                    style = typography.body1,
                    color = PrimaryDarker
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Additional Details (Context of History)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = "Additional Information:",
                style = typography.h6,
                color = PrimaryDarker
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "• Last Used: 2 days ago",
                style = typography.body2,
                color = GrayMedium
            )
            Text(
                text = "• Total Times Used: 5",
                style = typography.body2,
                color = GrayMedium
            )
            Text(
                text = "• Notes: This route avoids major traffic zones",
                style = typography.body2,
                color = GrayMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Footer for Actions (Optional)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = { /* TODO: Handle Edit */ }) {
                Text(text = "Edit", color = PrimaryDarker)
            }
            TextButton(onClick = { /* TODO: Handle Delete */ }) {
                Text(text = "Delete", color = Color.Red)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview() {
    BottomSheetContent(LocalContext.current, "14:05", "AUF to SM City Clark")
}
