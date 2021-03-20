package com.mansi.staquassignment.model

import com.google.gson.annotations.SerializedName

data class Owner (
    @field:SerializedName("display_name") val display_name : String,
    @field:SerializedName("link") val link : String,
    @field:SerializedName("profile_image") val profileImage : String,
    @field:SerializedName("reputation") val reputation : String,
    @field:SerializedName("user_id") val userId : String,
    @field:SerializedName("user_type") val userType : String,
        )