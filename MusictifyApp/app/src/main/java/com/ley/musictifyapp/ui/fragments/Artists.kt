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
import com.ley.musictifyapp.adapter.ArtistRecyclerAdapter
import com.ley.musictifyapp.model.Artist
import kotlinx.android.synthetic.main.fragment_artists.*


class Artists : Fragment() {

    private lateinit var db : FirebaseFirestore
    private lateinit var artistArrayList : ArrayList<Artist>
    private lateinit var artistAdapter : ArtistRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Firebase.firestore
        artistArrayList = ArrayList<Artist>()

        recyclerView2.layoutManager = LinearLayoutManager(context)
        artistAdapter = ArtistRecyclerAdapter(artistArrayList)
        recyclerView2.adapter = artistAdapter

        getData()
    }

    private  fun getData(){
        db.collection("songs").addSnapshotListener { value, error ->
            if(error != null){
                Toast.makeText(requireActivity(),error.localizedMessage,Toast.LENGTH_LONG).show()
            }
            else{
                if(value != null){
                    if(!value.isEmpty){
                        val documents = value.documents

                        for (document in documents){
                            val artist = document.get("artist") as String
                            val imageUrl = document.get("artistImage") as String

                            val artists = Artist(artist,imageUrl)
                            artistArrayList.add(artists)
                        }
                        artistAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artists,container,false)
    }

}