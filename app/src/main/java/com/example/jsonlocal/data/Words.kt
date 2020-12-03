package com.example.jsonlocal.data

import com.google.gson.annotations.SerializedName

// для работы этого локал data class необходим gson!!!
data class Words(
    @SerializedName("value") val value: String,
    @SerializedName("id") val id: Int,
    @SerializedName("imageName") val imageName: String,
    @SerializedName("imageSrc") val imageSrc: String,
    @SerializedName("videoURL") val videoURL: String,
    @SerializedName("URlforTest") val uRlforTest: String,
    @SerializedName("isInGame") val isInGame: Boolean,
    @SerializedName("arrayOfLetters") val arrayOfLetters: List<String>
)