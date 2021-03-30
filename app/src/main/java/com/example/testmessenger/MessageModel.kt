package com.example.testmessenger

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MessageModelList(val data: List<MessageModelItem>): Parcelable{

    @Parcelize
    data class MessageModelItem(
        @SerializedName("user_id")
        val userId: Int,

        @SerializedName("login")
        val userLogin: String,

        @SerializedName("date")
        val createDate: String,

        @SerializedName("text")
        val messageText: String
    ):Parcelable


}

