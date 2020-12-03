package com.example.jsonlocal.data

import com.google.gson.annotations.SerializedName

  // для работы этого локал data class необходим gson!!!
data class MainWords(
	@SerializedName("words") val words: List<Words>
)