package com.tim.burtons.model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("cost")
    val cost: Double?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("size")
    val size: String?,
    @SerializedName("type")
    val type: String?
)