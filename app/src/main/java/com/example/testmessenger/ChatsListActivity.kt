package com.example.testmessenger

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class ChatsListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    lateinit var chatsApi: ChatsApi
    lateinit var mesIntent: Intent
    var userId: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        userId = intent.getStringExtra("idUser")
        recyclerView = findViewById(R.id.chatListRecycler)
        configureRetrofit()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ChatMiniAdapter(){
            mesIntent = Intent(this, ChatActivity::class.java)
            mesIntent.putExtra("chatId", it)
            startActivity(mesIntent)
        }

        //fetchChatsList(chatsApi, userId)


        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
//            Создание нового чата
        }
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
    fun fetchChatsList(chatsApi: ChatsApi){
        chatsApi.getUserChats("1")
            .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                Log.e("TAG", it.toString())
                (recyclerView.adapter as ChatMiniAdapter).setItems(it.data)
            },{

            })
    }

//    @SuppressLint("CheckResult")
//    fun fetchChatsList(chatsApi: ChatsApi, userId: String){
//        chatsApi.getUserChats(userId)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.e("TAG", it.toString())
//                (recyclerView.adapter as ChatMiniAdapter).setItems(it.data)
//            },{
//
//            })
//    }

}