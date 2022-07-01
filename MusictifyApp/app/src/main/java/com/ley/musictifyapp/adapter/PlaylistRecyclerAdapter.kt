package com.ley.musictifyapp.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ley.musictifyapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.artist_recycler_row.view.*
import kotlinx.android.synthetic.main.playlists_recycler_row.view.*

class PlaylistRecyclerAdapter(val playlistList : ArrayList<String>, val playlistImage : ArrayList<Bitmap>) : RecyclerView.Adapter<PlaylistRecyclerAdapter.PlaylistHolder>(){
    class PlaylistHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.playlists_recycler_row,parent,false)
        return PlaylistHolder(view)
    }

    override fun onBindViewHolder(holder: PlaylistHolder, position: Int) {
        holder.itemView.recycler.text = playlistList[position]
        holder.itemView.ivItemImage.setImageBitmap(playlistImage[position])


    }

    override fun getItemCount(): Int {
        return playlistList.size

    }
}
