package com.example.testmessenger
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatMiniAdapter (private val values: List<ChatMini>): RecyclerView.Adapter<ChatMiniAdapter.ChatMiniViewHolder>() {

    override fun getItemCount() = values.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMiniViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_chat_mini, parent, false)
        return ChatMiniViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatMiniViewHolder, position: Int) {
        holder.chIconView?.setImageURI(values[position].chIcon) //посмотреть работу uri
        holder.chUncheckedView?.text = values[position].chUnchecked.toString()
        holder.chNameView?.text = values[position].chName
        holder.chLastMesView?.text = values[position].chLastMessage
    }

    class ChatMiniViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var chIconView: ImageView? = null
        var chNameView: TextView? = null
        var chLastMesView: TextView? = null
        var chUncheckedView: TextView? = null

        init{
            chIconView = itemView?.findViewById(R.id.chatIcon)
            chNameView = itemView?.findViewById(R.id.chatName)
            chLastMesView = itemView?.findViewById(R.id.chatLastMes)
            chUncheckedView = itemView?.findViewById(R.id.chatUnchecked)
        }
    }
}