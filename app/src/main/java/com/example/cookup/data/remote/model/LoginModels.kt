package com.example.cookup.data.remote.model

// This data class represents the JSON body you send to the API when logging in
data class LoginRequest(
    val email: String,
    val password: String
)

// This data class represents the JSON response you receive from the API after login
data class LoginResponse(
    val token: String,     // The authentication token (JWT, etc.)
    val userId: Int,       // The user ID assigned by the backend
    val name: String       // The user's name
    // Add more fields if your API returns them (e.g., role, email, etc.)
)
