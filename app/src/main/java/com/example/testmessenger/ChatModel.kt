package com.example.testmessenger

import com.google.gson.annotations.SerializedName
import java.util.*

data class ChatModelList(val data : List<ChatModelItem>)


data class ChatModelItem (
    @SerializedName("id")
    val chatId : String,

    @SerializedName("name")
    val chatName : String,

    @SerializedName("create_date")
    val cCreateDate : Date,

    @SerializedName("last_message")
    val cLastMessage : String,

    @SerializedName("image")
    val cImageUrl : String
)
