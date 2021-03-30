package com.example.testmessenger

import android.widget.EditText
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatsApi {

    @GET("/get_user_chats/{userId}")
    @Headers("Content-Type: application/json")
    fun getUserChats(@Path("userId") userId: String): Single<ChatModelList>

    @GET("/check_login/")
    @Headers("Content-Type: application/json")
    fun checkLogin(@Query("login") login: String): Single<CheckLogin>

    @GET("/registration/")
    @Headers("Content-Type: application/json")
    fun regNewAcc(@Query ("login") loginNew: String, @Query ("email") email: String,
                  @Query ("password") pass: String,@Query ("first_name") f_name: String,
                  @Query ("second_name") s_name: String): Single<NewAccount>

    @GET("authentication/")
    @Headers("Content-Type: application/json")
    fun authUser(@Query("login_or_email") login_or_email: String,
                 @Query("password")passAuth: String): Single<Auth>
}