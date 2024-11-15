package com.xenia.apptosupportpatientswithocd

import android.content.Context
import com.xenia.apptosupportpatientswithocd.domain.repository.AuthRepository
import com.xenia.apptosupportpatientswithocd.domain.usecases.auth_usecases.SignOutUseCase
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

class StatisticHomeworkViewModelUnitTest {
    @Test fun test() {
        assert(true)
    }
}

private const val FAKE_STRING = "HELLO WORLD"

@RunWith(MockitoJUnitRunner::class)
class MockedContextTest {

    @Mock
    private lateinit var mockContext: Context

    @Test
    fun checkSignOut() {
        val mockAuthRepository = Mockito.mock(AuthRepository::class.java)

        val useCase = SignOutUseCase(mockAuthRepository)
        useCase.invoke()
        Mockito.verify(mockAuthRepository).signOut()
    }
}