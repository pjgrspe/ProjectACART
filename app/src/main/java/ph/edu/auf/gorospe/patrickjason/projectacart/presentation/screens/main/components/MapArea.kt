package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components

import android.content.Context
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.animation.camera
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions

@Composable
fun MapArea(context: Context) {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        factory = { ctx ->
            MapView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                getMapboxMap().apply {
                    loadStyleUri(Style.MAPBOX_STREETS) {
                        val cameraOptions = CameraOptions.Builder()
                            .center(Point.fromLngLat(120.5887, 15.1441)) // Angeles, Pampanga
                            .zoom(10.0) // Initial zoom level
                            .build()
                        setCamera(cameraOptions)
                    }
                }
            }
        }
    )
}