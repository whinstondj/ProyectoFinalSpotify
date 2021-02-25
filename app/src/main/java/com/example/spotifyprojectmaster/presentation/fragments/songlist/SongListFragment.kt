package com.example.spotifyprojectmaster.presentation.fragments.songlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotifyprojectmaster.base.BaseExtraData
import com.example.spotifyprojectmaster.base.BaseState
import com.example.spotifyprojectmaster.databinding.FragmentSongListBinding


class SongListFragment : Fragment() {

    lateinit var binding: FragmentSongListBinding

    private val viewModel: SongListViewModel by viewModels()

    private lateinit var mAdapter: SongListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSongListBinding.inflate(layoutInflater, container, false)

        viewModel.getState().observe(viewLifecycleOwner, { state ->
            when (state) {
                is BaseState.Normal -> onNormal(state.data as SongListState)
                is BaseState.Loading -> onLoading(state.dataLoading)
                is BaseState.Error -> onError(state.dataError)
            }
        })

        setupView()

        viewModel.requestInformation("6Qf2sXTjlH3HH30Ijo6AUp")

        return binding.root
    }

    private fun setupView() {
        // Setup recycler view
        mAdapter = SongListAdapter(listOf(), requireActivity()) { itemTrack ->
            Toast.makeText(requireActivity(), itemTrack.track.artists[0].id, Toast.LENGTH_SHORT).show()
        }
        binding.fragmentSongListRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun onError(dataError: Throwable) {

    }

    private fun onLoading(dataLoading: BaseExtraData?) {

    }

    private fun onNormal(songListState: SongListState) {
        mAdapter.updateList(songListState.songList)
    }

}