package com.example.myapplication.domain.usecases

import android.net.Uri
import com.example.myapplication.domain.UserRepository
import com.example.myapplication.util.EMAIL_ADDRESS_PATTERN
import com.example.myapplication.util.ProcessUiState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EditProfile @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun invoke(username: String, email: String, uri: Uri?): ProcessUiState {

        if (username.trim().isEmpty() || email.trim().isEmpty()) {
            return ProcessUiState.Error("Can't update your profile")
        }

        if (!EMAIL_ADDRESS_PATTERN.matcher(email).matches()) {
            return ProcessUiState.Error("There is an error in email field")
        }

        return userRepository.editProfile(username, email, uri)
    }
}