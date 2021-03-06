package com.ley.musictifyapp.ui.fragments

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ley.musictifyapp.R
import com.ley.musictifyapp.adapter.PlaylistRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_playlists.*
import kotlinx.android.synthetic.main.playlists_recycler_row.*


class Playlists : Fragment() {
    var playlistNameList = ArrayList<String>()
    var playlistIdList = ArrayList<Int>()
    var playlistImage = ArrayList<Bitmap>()

    private lateinit var listeAdapter: PlaylistRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeAdapter = PlaylistRecyclerAdapter(playlistNameList,playlistImage)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = listeAdapter

        sqlVeriAlma()

    }

    fun sqlVeriAlma(){

        try {
            activity?.let {
                val database = it.openOrCreateDatabase("Playlists",Context.MODE_PRIVATE,null)

                val cursor = database.rawQuery("SELECT * FROM playlists",null)
                val playlistName = cursor.getColumnIndex("playlistName")
                val playlistId = cursor.getColumnIndex("id")
                val getPhoto = cursor.getColumnIndex("gorsel")

                playlistNameList.clear()
                playlistIdList.clear()
                playlistImage.clear()


                while (cursor.moveToNext()){
                    playlistNameList.add(cursor.getString(playlistName))
                    playlistIdList.add(cursor.getInt(playlistId))

                    val byteDizisi = cursor.getBlob(getPhoto)
                    val bitmap = BitmapFactory.decodeByteArray(byteDizisi,0,byteDizisi.size)
                    playlistImage.add(bitmap)

                }
                listeAdapter.notifyDataSetChanged()
                cursor.close()
            }
        }catch (e : Exception){

        }
    }

}