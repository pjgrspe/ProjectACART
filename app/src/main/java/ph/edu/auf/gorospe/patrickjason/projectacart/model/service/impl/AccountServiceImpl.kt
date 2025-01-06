package ph.edu.auf.gorospe.patrickjason.projectacart.model.service.impl

import android.graphics.Bitmap
import android.util.Base64
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import ph.edu.auf.gorospe.patrickjason.projectacart.model.User
import ph.edu.auf.gorospe.patrickjason.projectacart.model.service.AccountService
import java.io.ByteArrayOutputStream
import javax.inject.Inject


class AccountServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : AccountService {
    override val currentUser: Flow<User?>
        get() = callbackFlow {
        val listener =
            FirebaseAuth.AuthStateListener { auth ->
                this.trySend(auth.currentUser?.let { User(it.uid)})
            }

        Firebase.auth.addAuthStateListener(listener)
        awaitClose { Firebase.auth.removeAuthStateListener(listener) }
    }

    override val currentUserId: String
        get() = Firebase.auth.currentUser?.uid.orEmpty()

    override fun hasUser(): Boolean {
        return Firebase.auth.currentUser != null
    }

    override suspend fun getUserProfile(): User {
        return Firebase.auth.currentUser?.let { getUserFromFirestore(it.uid) } ?: User()
    }

    override suspend fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        try {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            onSuccess()
        } catch (e: Exception) {
            onFailure(e)
        }
    }
    override suspend fun signOut(
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        try {
            Firebase.auth.signOut()
            onSuccess()
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    private suspend fun getUserFromFirestore(uid: String): User {
        val document = firestore.collection("users").document(uid).get().await()
        return document.toObject(User::class.java) ?: User()
    }

    override suspend fun registerUser(
        name: String,
        username: String,
        email: String,
        password: String,
        phoneNumber: String,
        profilePictureBitmap: Bitmap?,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        try {
            val authResult = Firebase.auth.createUserWithEmailAndPassword(email, password).await()
            val userId = authResult.user?.uid ?: throw Exception("User ID is null")
            saveUserData(
                userId,
                name,
                username,
                email,
                phoneNumber,
                bitmapToBase64(profilePictureBitmap),
                firestore
            )
            onSuccess()
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    private suspend fun saveUserData(
        userId: String,
        name: String,
        username: String,
        email: String,
        phoneNumber: String,
        profilePicture: String?,
        db: FirebaseFirestore
    ) {
        val user = hashMapOf(
            "name" to name,
            "username" to username,
            "email" to email,
            "phoneNumber" to phoneNumber,
            "profilePicture" to profilePicture
        )
        db.collection("users").document(userId).set(user).await()
    }

    private fun bitmapToBase64(bitmap: Bitmap?): String? {
        if (bitmap == null) return null
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}