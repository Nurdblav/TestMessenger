package com.example.testmessenger
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ChatMiniAdapter (private  val onItemClicked: (String) -> Unit): RecyclerView.Adapter<ChatMiniAdapter.ChatMiniViewHolder>() {
    private var values: List<ChatModelItem> = ArrayList()


    override fun getItemCount() = values.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMiniViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_chat_mini, parent, false)
        return ChatMiniViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatMiniViewHolder, position: Int) {
        holder.bind(values[position])
    }

    fun setItems (values:List<ChatModelItem>){
        this.values = values
        notifyDataSetChanged()
    }

    inner class ChatMiniViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var chIconView: ImageView = itemView.findViewById(R.id.chatIcon)
        var chNameView: TextView = itemView.findViewById(R.id.chatName)
        var chLastMesView: TextView = itemView.findViewById(R.id.chatLastMes)
        var chUncheckedView: TextView = itemView.findViewById(R.id.chatUnchecked)

        fun bind(item: ChatModelItem){
            Glide
                    .with(itemView.context)
                    .load(item.cImageUrl)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(chIconView)

            chUncheckedView.text = item.cUnchecked.toString()
            chNameView.text = item.chatName
            chLastMesView.text = item.cLastMessage

            itemView.setOnClickListener{
                onItemClicked.invoke(item.chatId)
            }
        }
    }
}