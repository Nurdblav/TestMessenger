package com.example.testmessenger

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ActivityRegister : AppCompatActivity() {

    lateinit var chatsApi: ChatsApi
    var passIdent = false
    var detailLogin = false
    var internetbool = true
    lateinit var EditTextPassAcc : EditText
    lateinit var EditTextEmail : EditText
    lateinit var EditTextF_Name : EditText
    lateinit var EditTextS_Name : EditText
    lateinit var EditTextPass : EditText
    lateinit var EditTextLogin : EditText
    lateinit var textlogin : TextView

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
    fun checkNewLogin(chatsApi: ChatsApi, newlogin :String){

        chatsApi.checkLogin(newlogin)

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                detailLogin = it.resLog =="OK"
                    if(detailLogin)
                    {
                        textlogin.setTextColor(Color.WHITE)
                    }
                    else
                    {
                        textlogin.setTextColor(Color.RED)
                        Toast.makeText(this, "Такой логин существует",Toast.LENGTH_SHORT).show()
                    }
            },{
                internetbool = false
            })

    }

    @SuppressLint("CheckResult")
    fun regNewAcc(chatsApi: ChatsApi, login: String, email: String, pass: String, f_name: String, s_name: String){

        chatsApi.regNewAcc(login,email,pass,f_name,s_name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                this.finish()
            },{
                Toast.makeText(this, "Проблема с Интернетом повторите позже",Toast.LENGTH_SHORT).show()
            })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val textPassAcc = findViewById<TextView>(R.id.textView6)
        EditTextPassAcc = findViewById(R.id.TextPasswordAccept)
        EditTextPass = findViewById(R.id.TextPassword)
        EditTextLogin = findViewById(R.id.TextLogin)
        textlogin = findViewById(R.id.textView3)
        configureRetrofit()
        EditTextPassAcc.onFocusChangeListener = View.OnFocusChangeListener{_, b ->
            if (b){}
            else{
                var tpa = EditTextPassAcc.text.toString()
                var tp = EditTextPass.text.toString()
                passIdent = tpa == tp
                if(passIdent){
                    textPassAcc.setTextColor(Color.WHITE)
                }
                else{
                    textPassAcc.setTextColor(Color.RED)
                    Toast.makeText(this, "Повторите пароль",Toast.LENGTH_SHORT).show()
                }
            }
        }

        EditTextLogin.onFocusChangeListener = View.OnFocusChangeListener{view, b ->
            if (b){}
            else{
                var loginString = EditTextLogin.text.toString()
                checkNewLogin(chatsApi,loginString)
            }
        }

    }

    fun btnRegisterNewAcc(view:View){
        EditTextEmail = findViewById(R.id.TextEmail)
        EditTextF_Name = findViewById(R.id.TextFirstName)
        EditTextS_Name = findViewById(R.id.TextSecondName)
        regNewAcc(chatsApi,EditTextLogin.text.toString(),EditTextEmail.text.toString(),
            EditTextPass.text.toString(),EditTextF_Name.text.toString(),EditTextS_Name.text.toString())
    }
}