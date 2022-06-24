package com.ley.musictifyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.playlists_recycler_row.view.*

class PlaylistRecyclerAdapter(val playlistList : ArrayList<String>, val idList : ArrayList<Int>) : RecyclerView.Adapter<PlaylistRecyclerAdapter.PlaylistHolder>(){
    class PlaylistHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.playlists_recycler_row,parent,false)
        return PlaylistHolder(view)
    }

    override fun onBindViewHolder(holder: PlaylistHolder, position: Int) {
        holder.itemView.recycler.text = playlistList[position]
    }

    override fun getItemCount(): Int {
        return playlistList.size

    }
}
