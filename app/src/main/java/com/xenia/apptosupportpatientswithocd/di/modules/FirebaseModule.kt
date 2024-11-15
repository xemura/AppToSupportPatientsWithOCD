package com.xenia.apptosupportpatientswithocd.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xenia.apptosupportpatientswithocd.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class FirebaseModule {
    @ApplicationScope
    @Provides
    fun provideFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @ApplicationScope
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}