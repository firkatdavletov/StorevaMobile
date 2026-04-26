package ru.storeva.android.data.api

interface ResponseModel {
    val success: Boolean
    val error: String?
    val code: Int?
}