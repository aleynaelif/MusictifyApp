package com.ley.musictifyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ley.musictifyapp.R
import com.ley.musictifyapp.model.Artist
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.artist_recycler_row.view.*

class AlbumRecyclerAdapter(private val albumList: ArrayList<Artist>): RecyclerView.Adapter<AlbumRecyclerAdapter.AlbumHolder>()  {
    class AlbumHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.artist_recycler_row,parent,false)
        return AlbumHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        holder.itemView.tvArtistName.text = albumList.get(position).artist
        Picasso.get().load(albumList.get(position).imageUrl).into(holder.itemView.artistImageView)
    }

    override fun getItemCount(): Int {
        return  albumList.size
    }

}