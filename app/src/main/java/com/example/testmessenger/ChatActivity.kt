package com.example.testmessenger

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ChatActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    lateinit var chatsApi: ChatsApi
    lateinit var chatId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        chatId = intent.getStringExtra("chatId").toString()
        configureRetrofit()
        recyclerView = findViewById(R.id.messageRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ChatMessagesAdapter()

        fetchChatMessages(chatsApi,chatId)

    }

    private fun configureRetrofit(){
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://messenger-android.herokuapp.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        chatsApi = retrofit.create(ChatsApi::class.java)
    }


    @SuppressLint("CheckResult")
    fun fetchChatMessages(chatsApi: ChatsApi, id: String){
        chatsApi.getChatsMessages(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("TAG", it.toString())
                (recyclerView.adapter as ChatMessagesAdapter).setItems(it.data)
            },{

            })
    }
}