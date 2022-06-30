package com.ley.musictifyapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ley.musictifyapp.R
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
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            if (transaction != null) {
                transaction.replace(R.id.frameLayout, LikedSongs())
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
        binding.PlaylistsButton.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            if (transaction != null) {
                transaction.replace(R.id.frameLayout, Playlists())
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
        binding.ArtistsButton.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            if (transaction != null) {
                transaction.replace(R.id.frameLayout, Artists())
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
        binding.AlbumsButton.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            if (transaction != null) {
                transaction.replace(R.id.frameLayout, Albums())
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}