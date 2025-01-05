package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ph.edu.auf.gorospe.patrickjason.projectacart.R
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.AppTheme

@Composable
fun HistorySection(onColumnClick: (String, String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Previous Routes", style = AppTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
                .clickable { onColumnClick("14:05", "AUF to SM City Clark") },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("14:05")
                Text("AUF")
            }
            Column {
                Text("14:31")
                Text("SM City Clark")
            }
            IconButton(onClick = { /* TODO: Navigate */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Navigate",
                    tint = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
                .clickable { onColumnClick("05:05", "HAU to SM Telebastagan") },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("05:05")
                Text("HAU")
            }
            Column {
                Text("14:31")
                Text("SM Telebastagan")
            }
            IconButton(onClick = { /* TODO: Navigate */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Navigate",
                    tint = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistorySectionPreview() {
    MaterialTheme {
        HistorySection { _, _ -> }
    }
}

@Preview(showBackground = true)
@Composable
fun HistorySectionPreview() {
    AppTheme {
        HistorySection()
    }
}