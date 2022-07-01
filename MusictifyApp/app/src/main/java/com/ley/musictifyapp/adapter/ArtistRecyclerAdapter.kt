package com.ley.musictifyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ley.musictifyapp.R
import com.ley.musictifyapp.model.Artist
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.artist_recycler_row.view.*

class ArtistRecyclerAdapter (private val artistList: ArrayList<Artist>): RecyclerView.Adapter<ArtistRecyclerAdapter.ArtistHolder>() {
    class ArtistHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.artist_recycler_row,parent,false)
        return ArtistHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        holder.itemView.tvArtistName.text = artistList.get(position).artist
        Picasso.get().load(artistList.get(position).imageUrl).into(holder.itemView.artistImageView)
    }

    override fun getItemCount(): Int {
        return  artistList.size
    }
}