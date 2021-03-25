package com.example.testmessenger

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Login(
    var login: String? = null
)

data class NewAccount(
        var firstName: String? = null,
        var secondName: String? = null,
        var email: String? = null,
        var phone: String? = null,
        var login: String? = null,
        var password: String? = null
)

object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!
    }
}

class ActivityRegister : AppCompatActivity() {

    var passIdent = false
    var serv = RetrofitClient.getClient("https://messenger-android.herokuapp.com/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val EditTextPassAcc = findViewById(R.id.TextPasswordAccept) as EditText
        val EditTextPass = findViewById(R.id.TextPassword) as EditText
        val textPassAcc = findViewById(R.id.textView6) as TextView
        EditTextPassAcc.onFocusChangeListener = View.OnFocusChangeListener{view, b ->
            if (b){}
            else{
                var tpa = EditTextPassAcc.text.toString()
                var tp = EditTextPass.text.toString()
                passIdent = tpa == tp
                if(passIdent){
                    textPassAcc.setTextColor(Color.WHITE)
//                    Toast.makeText(this, "Все норм",Toast.LENGTH_SHORT).show()
                }
                else{
                    textPassAcc.setTextColor(Color.RED)
                    Toast.makeText(this, "Повторите пароль",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    fun btnRegisterNewAcc(view:View){
        var pass = (findViewById(R.id.TextPassword) as EditText).text.toString()
        var passAcc = (findViewById(R.id.TextPasswordAccept) as EditText).text.toString()
        val EditTextLogin = findViewById(R.id.TextLogin) as EditText
        var loginString = EditTextLogin.text.toString()
        this.finish()
//        @GET("check_login/?login=$loginString")
//        fun searchLogin()
//        val passIdent = pass==passAcc
//        if (passIdent){
//            Toast.makeText(this, "Все норм",Toast.LENGTH_SHORT).show()
//        }
//        else
//            Toast.makeText(this, "Повторите пароль",Toast.LENGTH_SHORT).show()
    }
}