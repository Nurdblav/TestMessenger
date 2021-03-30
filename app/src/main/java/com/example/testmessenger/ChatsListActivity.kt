package com.example.testmessenger

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ChatsListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    lateinit var chatsApi: ChatsApi
    lateinit var chatsMiniList: ArrayList<ChatMini>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        recyclerView = findViewById(R.id.chatListRecycler)
        configureRetrofit()

        fetchChatsList(chatsApi)

        recyclerView.layoutManager = LinearLayoutManager(this)
        // вызвать адаптер
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

    fun fetchChatsList(chatsApi: ChatsApi){
        chatsApi.getUserChats("1")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("TAG", it.toString())
                for (model in it.data){
                    var mChatMini = ChatMini(model.chatId, model.chatName, model.cImageUrl, model.cLastMessage, 0)

                }
            },{

            })
    }
}