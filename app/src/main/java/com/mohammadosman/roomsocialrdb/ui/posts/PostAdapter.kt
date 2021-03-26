package com.mohammadosman.roomsocialrdb.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mohammadosman.roomsocialrdb.R
import com.mohammadosman.roomsocialrdb.data.model.Posts


class PostAdapter(
) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    private val diffUtl = object : DiffUtil.ItemCallback<Posts>() {
        override fun areItemsTheSame(
            oldItem: Posts,
            newItem: Posts
        ): Boolean {
            return oldItem.pId == newItem.pId
        }

        override fun areContentsTheSame(
            oldItem: Posts,
            newItem: Posts
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtl)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_posts_item,
            parent,
            false
        )
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        when (holder) {
            is PostViewHolder -> {
                val data = differ.currentList[position]
                holder.bind(data)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Posts>) {
        differ.submitList(list)
    }


    inner class PostViewHolder(itmView: View) : RecyclerView.ViewHolder(itmView) {

        private val username: TextView = itmView.findViewById(R.id.txtView_username)
        private val desc: TextView = itmView.findViewById(R.id.txtView_posDesc)
        private val imgView: ImageView = itmView.findViewById(R.id.imgView_postImg)

        fun bind(t: Posts) {
            desc.text = t.postDesc
            imgView.setImageResource(t.postPicture ?: 0)
            username.text = "Created by: ${t.userName}"
        }
    }

}