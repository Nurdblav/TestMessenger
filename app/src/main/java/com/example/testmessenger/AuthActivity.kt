package com.example.testmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent;
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.testmessenger.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        val login : EditText = findViewById(R.id.TextLoginAuth)
        val pass :EditText = findViewById(R.id.TextPasswordAuth)
        val mButton: Button = findViewById(R.id.button_sign_in_auth)
        mButton.setOnClickListener {
            var logStr = login.text.toString()
            var pasStr = pass.text.toString()
            if ((logStr=="tolik")&&(pasStr=="kekw"))
            {
                val intent = Intent(this, ChatsListActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Неправильный логин или пароль", Toast.LENGTH_SHORT).show()
            }
        }
    }
}