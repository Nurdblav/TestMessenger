package com.example.testmessenger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ChatMessagesAdapter: RecyclerView.Adapter<ChatMessagesAdapter.ChatMessagesViewHolder>() {
    private var values: List<MessageModelList.MessageModelItem> = ArrayList()

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessagesAdapter.ChatMessagesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_message_exp, parent, false)
        return ChatMessagesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatMessagesAdapter.ChatMessagesViewHolder, position: Int) {
        holder.bind(values[position])
    }

    fun setItems (values:List<MessageModelList.MessageModelItem>){
        this.values = values
        notifyDataSetChanged()
    }


    inner class ChatMessagesViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var userIconView: ImageView = itemView.findViewById(R.id.userIcon)
        var userNameView: TextView = itemView.findViewById(R.id.userName)
        var userMessageView: TextView = itemView.findViewById(R.id.userMessage)
        var messageTimeView: TextView = itemView.findViewById(R.id.messageTime)

        fun bind(item:MessageModelList.MessageModelItem){
            Glide
                .with(itemView.context)
                .load("https://messenger-android.herokuapp.com/media/chats/gorilla.jpg")
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(userIconView)
            userNameView.text = item.userLogin
            userMessageView.text = item.messageText
            messageTimeView.text = item.createDate

        }
    }

}