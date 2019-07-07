package com.simiomobile.masterdetail.api.response.allcoins

import com.google.gson.annotations.SerializedName

data class Roi(
    @field:SerializedName("currency")
    val currency: String? = null,
    @field:SerializedName("percentage")
    val percentage: Double? = null,
    @field:SerializedName("times")
    val times: Double? = null
)