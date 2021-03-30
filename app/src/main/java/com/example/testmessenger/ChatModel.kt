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

data class CheckLogin(
    @SerializedName("detail")
    val resLog : String
)

data class NewAccount(
    @SerializedName("login")
    val newLog : String,

    @SerializedName("email")
    val newEmail : String,

    @SerializedName("password")
    val newPass : String,

    @SerializedName("first_name")
    val newF_name : String,

    @SerializedName("second_name")
    val newS_name : String
)

data class Auth(
    @SerializedName("detail")
    val isLog : String,

    @SerializedName("user_id")
    val idUser : String? = null
)
