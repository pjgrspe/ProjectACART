package ph.edu.auf.gorospe.patrickjason.projectacart.model.service

import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow
import ph.edu.auf.gorospe.patrickjason.projectacart.model.User

interface AccountService {
    val currentUser: Flow<User?>
    val currentUserId: String
    fun hasUser(): Boolean
    suspend fun getUserProfile(): User
    suspend fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )
    suspend fun signOut()
    suspend fun registerUser(
        name: String,
        username: String,
        email: String,
        password: String,
        phoneNumber: String,
        profilePictureBitmap: Bitmap?,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )

}