package com.ley.musictifyapp.data.entities

data class Song (
    val album : String ="",
    val artist : String = "",
    val imageUrl : String = "",
    val isLiked : Boolean = false,
    val mediaId : String = "",
    val songUrl : String = "",
    val title : String = ""
)