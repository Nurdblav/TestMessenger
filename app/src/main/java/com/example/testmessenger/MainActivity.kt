package com.example.testmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent;
import android.widget.Button
import com.example.testmessenger.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mButton : Button = findViewById(R.id.button_sign_in)
        mButton.setOnClickListener{
            val intent2 = Intent(this, ChatsListActivity::class.java)
            startActivity(intent2)
        }
    }
    fun btnReg(view : View){
        val intent = Intent(this, ActivityRegister::class.java)
        startActivity(intent)

    }

//    fun btnLog(view : View){
//        val intent2 = Intent(this, ChatMini::class.java)
//        startActivity(intent2)
//
//    }
}