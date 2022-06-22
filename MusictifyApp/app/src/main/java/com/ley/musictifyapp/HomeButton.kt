package com.ley.musictifyapp

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ley.musictifyapp.databinding.FragmentHomeButtonBinding


class Home : Fragment() {

    private var _binding: FragmentHomeButtonBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeButtonBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.likedSongs.setOnClickListener {
            val likedSongs = LikedSongs()
            activity!!
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.home, likedSongs)
                .commitNow()
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}