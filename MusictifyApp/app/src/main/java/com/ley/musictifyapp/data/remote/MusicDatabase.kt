package com.ley.musictifyapp.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.ley.musictifyapp.data.entities.Song
import com.ley.musictifyapp.other.Constants.SONG_COLLECTION
import kotlinx.coroutines.tasks.await

class MusicDatabase {
    private val firestore = FirebaseFirestore.getInstance()
    private val songCollection = firestore.collection(SONG_COLLECTION)

    suspend fun getAllSongs():List<Song>{
        return try {
            songCollection.get().await().toObjects(Song::class.java)
        }catch (e:Exception){
            emptyList()
        }
    }
}