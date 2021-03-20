package com.mansi.staquassignment.model

import com.google.gson.annotations.SerializedName

data class ItemResponse (
        @field:SerializedName("has_more") val hasMore: Boolean,
        @field:SerializedName("quota_max") val quotaMax: Int,
        @field:SerializedName("quota_remaining") val quotaRemaining: Int,
        @field:SerializedName("items") val items: List<Items>

        )

