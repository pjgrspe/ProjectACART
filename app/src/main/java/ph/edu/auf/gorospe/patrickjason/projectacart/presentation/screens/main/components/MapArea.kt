package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.google.android.gms.location.LocationServices
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import java.util.Locale
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput

@SuppressLint("MissingPermission", "ClickableViewAccessibility")
@Composable
fun MapArea(context: Context, onLocationSelected: (String) -> Unit) {
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var location by remember { mutableStateOf<Point?>(null) }
    var mapView: MapView? = remember { null }

    LaunchedEffect(Unit) {
        fusedLocationClient.lastLocation.addOnSuccessListener { loc ->
            loc?.let {
                location = Point.fromLngLat(it.longitude, it.latitude)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            // Prevent scroll when interacting with map
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { /* Consume press events */ },
                    onTap = { /* Consume tap events */ },
                    onDoubleTap = { /* Consume double tap events */ },
                    onLongPress = { /* Consume long press events */ }
                )
            }
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                MapView(ctx).apply {
                    mapView = this
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    mapboxMap.apply {
                        loadStyleUri(Style.MAPBOX_STREETS) {
                            val cameraOptions = CameraOptions.Builder()
                                .center(Point.fromLngLat(120.5887, 15.1441)) // Default location
                                .zoom(12.5) // Initial zoom level
                                .build()
                            setCamera(cameraOptions)
                        }
                        addOnMapClickListener { point ->
                            val geocoder = Geocoder(context, Locale.getDefault())
                            val addresses = geocoder.getFromLocation(point.latitude(), point.longitude(), 1)
                            val address = addresses?.get(0)?.getAddressLine(0) ?: "Unknown location"
                            onLocationSelected(address)
                            true
                        }
                    }
                    // Disable parent scrolling when touching the map
                    setOnTouchListener { _, _ ->
                        parent.requestDisallowInterceptTouchEvent(true)
                        false
                    }
                }
            }
        )

        IconButton(
            onClick = {
                location?.let {
                    mapView?.mapboxMap?.flyTo(
                        CameraOptions.Builder()
                            .center(it)
                            .zoom(14.0)
                            .build()
                    )
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Recenter"
            )
        }
    }
}