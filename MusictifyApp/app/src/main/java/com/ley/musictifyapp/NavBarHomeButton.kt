package com.ley.musictifyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import com.ley.musictifyapp.databinding.FragmentNavBarHomeButtonBinding


class NavBarHomeButton : Fragment() {
    private var _binding: FragmentNavBarHomeButtonBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNavBarHomeButtonBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.LikedSongsButton.setOnClickListener {
            val action = NavBarHomeButtonDirections.actionNavBarHomeButtonToLikedSongs()
            Navigation.findNavController(it).navigate(action)
        }
        binding.PlaylistsButton.setOnClickListener {
            val action1 = NavBarHomeButtonDirections.actionNavBarHomeButtonToPlaylists()
            Navigation.findNavController(it).navigate(action1)
        }
        binding.ArtistsButton.setOnClickListener {
            val action2 = NavBarHomeButtonDirections.actionNavBarHomeButtonToArtists()
            Navigation.findNavController(it).navigate(action2)
        }
        binding.AlbumsButton.setOnClickListener {
            val action3 = NavBarHomeButtonDirections.actionNavBarHomeButtonToAlbums()
            Navigation.findNavController(it).navigate(action3)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}