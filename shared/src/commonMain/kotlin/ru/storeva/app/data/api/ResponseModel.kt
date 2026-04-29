package ru.storeva.app.data.api

interface ResponseModel {
    val success: Boolean
    val error: String?
    val code: Int?
}