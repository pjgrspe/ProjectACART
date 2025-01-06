package ph.edu.auf.gorospe.patrickjason.projectacart.model.service

import com.google.type.DateTime
import org.checkerframework.checker.units.qual.Time

interface BookingService {
    suspend fun addBookingHistory(
        userId: String,
        timeBooked: String,
        start: String,
        destination: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )
}