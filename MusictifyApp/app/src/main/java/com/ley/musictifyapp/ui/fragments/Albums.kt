package com.ley.musictifyapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ley.musictifyapp.R
import com.ley.musictifyapp.adapter.AlbumRecyclerAdapter
import com.ley.musictifyapp.adapter.ArtistRecyclerAdapter
import com.ley.musictifyapp.model.Artist
import kotlinx.android.synthetic.main.fragment_albums.*
import kotlinx.android.synthetic.main.fragment_artists.*

class Albums : Fragment() {
    private lateinit var db : FirebaseFirestore
    private lateinit var albumArrayList : ArrayList<Artist>
    private lateinit var albumAdapter : AlbumRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Firebase.firestore
        albumArrayList = ArrayList<Artist>()

        albumRecyclerView.layoutManager = LinearLayoutManager(context)
        albumAdapter = AlbumRecyclerAdapter(albumArrayList)
        albumRecyclerView.adapter = albumAdapter

        getData()
    }

    private  fun getData(){
        db.collection("songs").addSnapshotListener { value, error ->
            if(error != null){
                Toast.makeText(requireActivity(),error.localizedMessage, Toast.LENGTH_LONG).show()
            }
            else{
                if(value != null){
                    if(!value.isEmpty){
                        val documents = value.documents

                        for (document in documents){
                            val album = document.get("album") as String
                            val imageUrl = document.get("imageUrl") as String

                            val albums = Artist(album,imageUrl)
                            albumArrayList.add(albums)
                        }
                        albumAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

}