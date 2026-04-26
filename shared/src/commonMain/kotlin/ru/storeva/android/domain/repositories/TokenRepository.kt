package ru.storeva.android.domain.repositories

interface TokenRepository {
    fun saveAccessToken(token: String)

    fun saveRefreshToken(token: String)

    fun getAccessToken(): String

    fun getRefreshToken(): String
}