package com.example.testmessenger

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent;
import android.graphics.Color
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.testmessenger.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AuthActivity : AppCompatActivity() {

    lateinit var chatsApi: ChatsApi

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
    fun authUser(chatsApi: ChatsApi, log_or_em :String, password :String){

        chatsApi.authUser(log_or_em,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isLog=="OK")
                {
                    val intent = Intent(this, ChatsListActivity::class.java)
                    intent.putExtra("idUser",it.idUser)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "Неправильный логин или пароль", Toast.LENGTH_SHORT).show()
                }
            },{

            })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        configureRetrofit()
        val login : EditText = findViewById(R.id.TextLoginAuth)
        val pass :EditText = findViewById(R.id.TextPasswordAuth)
        val mButton: Button = findViewById(R.id.button_sign_in_auth)
        mButton.setOnClickListener {
            authUser(chatsApi,login.text.toString(),pass.text.toString())
        }
    }
}