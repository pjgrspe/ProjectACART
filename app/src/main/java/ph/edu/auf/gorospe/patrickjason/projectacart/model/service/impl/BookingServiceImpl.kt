package ph.edu.auf.gorospe.patrickjason.projectacart.model.service.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.google.type.DateTime
import kotlinx.coroutines.tasks.await
import ph.edu.auf.gorospe.patrickjason.projectacart.model.service.BookingService
import javax.inject.Inject

class BookingServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): BookingService{
    override suspend fun addBookingHistory(
        userId: String,
        timeBooked: String,
        start: String,
        destination: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        try {
            val bookingData = hashMapOf(
                "Start" to start,
                "Destination" to destination,
                "Time Booked" to timeBooked
            )
            firestore.collection("users")
                .document(userId)
                .collection("BookingHistory")
                .document(timeBooked.toString()) // Use timeBooked as the document ID
                .set(bookingData)
                .await()
            onSuccess()
        } catch (e: Exception) {
            onFailure(e)
        }
    }
}