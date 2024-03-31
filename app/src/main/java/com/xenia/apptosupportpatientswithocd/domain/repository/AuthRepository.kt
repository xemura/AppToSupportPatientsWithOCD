package com.xenia.apptosupportpatientswithocd.domain.repository

import com.google.firebase.auth.AuthResult
import com.xenia.apptosupportpatientswithocd.domain.entity.AuthState
import com.xenia.apptosupportpatientswithocd.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    fun getAuthStateFlow(): StateFlow<AuthState>
    fun loginUser(email:String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email:String, password: String): Flow<Resource<AuthResult>>

    fun signOut()

}