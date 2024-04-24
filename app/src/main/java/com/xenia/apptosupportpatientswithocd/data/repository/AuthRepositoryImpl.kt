package com.xenia.apptosupportpatientswithocd.data.repository

import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xenia.apptosupportpatientswithocd.domain.entity.AuthState
import com.xenia.apptosupportpatientswithocd.domain.entity.UserModel
import com.xenia.apptosupportpatientswithocd.domain.repository.AuthRepository
import com.xenia.apptosupportpatientswithocd.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStoreDatabase: FirebaseFirestore
) : AuthRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private val checkAuthStateEvents = MutableSharedFlow<Unit>(replay = 1)
    private val authStateFlow = flow {
        checkAuthStateEvents.emit(Unit)
        checkAuthStateEvents.collect {

            val auth: FirebaseAuth = FirebaseAuth.getInstance()
            val currentUser = auth.currentUser

            Log.d("TAG", currentUser?.uid.toString())

            val authState = if (currentUser == null) {
                AuthState.NotAuthorized
            } else AuthState.Authorized

            emit(authState)
        }
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = AuthState.Initial
    )

    override fun getAuthStateFlow(): StateFlow<AuthState> {
        coroutineScope.launch {
            checkAuthStateEvents.emit(Unit)
        }

        return authStateFlow
    }

    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(
                email, password
            ).await()
            emit(Resource.Success(result))
            getAuthStateFlow()
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override fun registerUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            Log.d("TAG inside", "result.toString()")

            val result = firebaseAuth.createUserWithEmailAndPassword(
                email, password
            ).await()

            Log.d("TAG inside", result.toString())

            val currentUser = firebaseAuth.currentUser
            if (currentUser != null) {
                if (currentUser.uid.isNotEmpty()) {
                    fireStoreDatabase.collection(currentUser.uid).document("userInfo").set(
                        UserModel("", false, "08:00")
                    ).await()
                    Log.d("TAG inside", currentUser.uid)
                }
                Log.d("TAG", currentUser.uid)

            }

            emit(Resource.Success(result))
            getAuthStateFlow()
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override fun signOut() {
        firebaseAuth.signOut()
        getAuthStateFlow()
    }
}