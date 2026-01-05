package com.example.cookup.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookup.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    /* ---------------- LOGIN ---------------- */

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun onEmailChange(newEmail: String) {
        _loginUiState.value = _loginUiState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _loginUiState.value = _loginUiState.value.copy(password = newPassword)
    }

    fun login() {
        val state = _loginUiState.value

        if (state.email.isBlank() || state.password.isBlank()) {
            _loginUiState.value =
                state.copy(errorMessage = "Please enter both email and password")
            return
        }

        viewModelScope.launch {
            _loginUiState.value = state.copy(isLoading = true, errorMessage = null)

            try {
                authRepository.login(state.email, state.password)

                _loginUiState.value = _loginUiState.value.copy(
                    isLoading = false,
                    isSuccess = true
                )
            } catch (e: Exception) {
                _loginUiState.value = _loginUiState.value.copy(
                    isLoading = false,
                    isSuccess = false,
                    errorMessage = e.message ?: "Login failed"
                )
            }
        }
    }

    fun resetSuccess() {
        _loginUiState.value = _loginUiState.value.copy(isSuccess = false)
    }

    /* ---------------- REGISTER ---------------- */

    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> = _registerUiState

    fun onRegisterNameChange(value: String) {
        _registerUiState.value = _registerUiState.value.copy(name = value)
    }

    fun onRegisterEmailChange(value: String) {
        _registerUiState.value = _registerUiState.value.copy(email = value)
    }

    fun onRegisterPasswordChange(value: String) {
        _registerUiState.value = _registerUiState.value.copy(password = value)
    }

    fun register() {
        val state = _registerUiState.value

        if (state.name.isBlank() || state.email.isBlank() || state.password.isBlank()) {
            _registerUiState.value =
                state.copy(errorMessage = "All fields are required")
            return
        }

        viewModelScope.launch {
            _registerUiState.value = state.copy(isLoading = true, errorMessage = null)

            try {
                authRepository.register(state.email, state.password)

                _registerUiState.value = _registerUiState.value.copy(
                    isLoading = false,
                    isSuccess = true
                )
            } catch (e: Exception) {
                _registerUiState.value = _registerUiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Registration failed"
                )
            }
        }
    }

    fun resetRegisterSuccess() {
        _registerUiState.value = _registerUiState.value.copy(isSuccess = false)
    }

    /* ---------------- LOGOUT ---------------- */

    fun logout() {
        authRepository.logout()
        _loginUiState.value = LoginUiState()
        _registerUiState.value = RegisterUiState()
    }

    fun isLoggedIn(): Boolean {
        return authRepository.isLoggedIn()
    }
}
