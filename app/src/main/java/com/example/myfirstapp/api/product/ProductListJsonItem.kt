package com.example.myfirstapp.api.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductListJsonItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
): Parcelable