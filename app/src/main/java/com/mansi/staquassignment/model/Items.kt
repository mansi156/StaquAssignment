package com.mansi.staquassignment.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class Items (
@field:SerializedName("answer_count") val AnswerCount: Int,
@field:SerializedName("content_license") val contentLicense: String,
@field:SerializedName("creation_date") val creationDate: Long,
@field:SerializedName("is_answered") val isAnswered: Boolean,
@field:SerializedName("last_activity_date") val lastActivityDate: Long,
@field:SerializedName("link") val link: String,
@field:SerializedName("owner") val owner: Owner,
@field:SerializedName("question_id") val questionId: Int,
@field:SerializedName("score") val score: Int,
@field:SerializedName("tags") val tags: List<String>,
@field:SerializedName("title") val title: String,
@field:SerializedName("viewCount") val viewCount: Int,
)
