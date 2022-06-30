package com.ley.musictifyapp.adapter


import androidx.recyclerview.widget.AsyncListDiffer
import com.bumptech.glide.RequestManager
import com.ley.musictifyapp.R
import kotlinx.android.synthetic.main.list_item.view.*
import javax.inject.Inject

class SongAdapter @Inject constructor(
    private val glide: RequestManager
) : BaseSongAdapter(R.layout.list_item) {

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.itemView.apply {
            tvPrimary.text = song.artist
            tvSecondary.text = song.songUrl
            glide.load(song.subtitle).into(ivItemImage)

            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(song)
                }
            }
        }
    }

}
