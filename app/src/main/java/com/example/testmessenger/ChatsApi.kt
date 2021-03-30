package com.example.testmessenger

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ChatsApi {

    @GET("/get_user_chats/{userId}")
    @Headers("Content-Type: application/json")
    fun getUserChats(@Path("userId") userId: String): Single<ChatModelList>

    @GET("/get_chat_messages/{chatId}")
    @Headers("Content-Type: application/json")
    fun getChatsMessages(@Path("chatId") chatId: String): Single<MessageModelList>
}