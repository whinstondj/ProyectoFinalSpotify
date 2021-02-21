package com.example.spotifyprojectmaster.presentation.fragments.songlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.spotifyprojectmaster.R
import com.example.spotifyprojectmaster.databinding.FragmentSongListBinding


class SongListFragment : Fragment() {

    lateinit var binding: FragmentSongListBinding
    val viewModel: SongListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSongListBinding.inflate(inflater, container, false)
        return binding.root
    }

}