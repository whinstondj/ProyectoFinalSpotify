package com.example.spotifyprojectmaster.presentation.fragments.detailartist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.spotifyprojectmaster.R
import com.example.spotifyprojectmaster.base.BaseExtraData
import com.example.spotifyprojectmaster.base.BaseState
import com.example.spotifyprojectmaster.data.NoArtistException
import com.example.spotifyprojectmaster.data.model.Followers
import com.example.spotifyprojectmaster.databinding.FragmentSongDetailBinding


class SongDetailFragment : Fragment() {

    lateinit var binding: FragmentSongDetailBinding

    private val viewModel: SongDetailViewModel by viewModels()

    private val args: SongDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSongDetailBinding.inflate(layoutInflater, container, false)

        viewModel.getState().observe(viewLifecycleOwner, { state ->
            when (state) {
                is BaseState.Normal -> onNormal(state.data as SongDetailState)
                is BaseState.Loading -> onLoading(state.dataLoading)
                is BaseState.Error -> onError(state.dataError)
            }
        })

        //setupView()
        viewModel.requestInformation(args.artistId)

        return binding.root
    }

    private fun setupView() {
        // Setup recycler view
        binding.fragmentDetailProgressBar.visibility = View.VISIBLE
        binding.buttonWebPage.visibility = View.GONE
    }

    private fun onError(dataError: Throwable) {
        when (dataError) {
            is NoArtistException -> {

            } else -> {

        }
        }
    }

    private fun onLoading(dataLoading: BaseExtraData?) {
        binding.fragmentDetailProgressBar.visibility = View.VISIBLE
        binding.buttonWebPage.visibility = View.GONE
    }

    private fun onNormal(songDetailState: SongDetailState) {
        binding.fragmentDetailProgressBar.visibility = View.GONE
        songDetailState.artistDetail?.let { item ->
            binding.artistDetailFragmentArtistNameText.text = item.name
            binding.artistDetailFragmentArtistFollowersText.text = getString(R.string.FollowersDetailsFragmentText) + item.followers.total.toString()
            binding.artistDetailFragmentArtistPopularityText.text = getString(R.string.PopularityDetailsFragmentText) +item.popularity.toString()

            Glide
                .with(requireContext())
                .load(item.images.firstOrNull()?.url ?: "https://ryrsupport.net/wp-content/uploads/2018/05/que-significa-el-codigo-de-error-http-404-not-foun.jpg")
                .centerCrop()
                .circleCrop()
                .placeholder(R.drawable.play_button_icon)
                .into(binding.imageViewArtistDetaill);

            binding.buttonWebPage.setOnClickListener {
            binding.artistDetailFragmentWebView.loadUrl(item.external_urls.spotify)
            }
        }
        binding.buttonWebPage.visibility = View.VISIBLE
    }

}