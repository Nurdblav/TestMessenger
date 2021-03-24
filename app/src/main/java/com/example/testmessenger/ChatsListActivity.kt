package com.example.testmessenger

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatsListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        recyclerView = findViewById(R.id.chatListRecycler)

        recyclerView.layoutManager = LinearLayoutManager(this)
        // вызвать адаптер
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
//            Создание нового чата
        }
    }
}