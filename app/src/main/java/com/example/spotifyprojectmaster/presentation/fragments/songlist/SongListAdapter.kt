package com.example.spotifyprojectmaster.presentation.fragments.songlist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spotifyprojectmaster.R
import com.example.spotifyprojectmaster.data.model.Artist
import com.example.spotifyprojectmaster.data.model.ItemTrack
import com.example.spotifyprojectmaster.data.model.Track
import com.example.spotifyprojectmaster.databinding.ItemSongListBinding

class SongListAdapter(private var dataSet: List<ItemTrack>, private val context: Context, private val callback: (item: ItemTrack) -> Unit) : RecyclerView.Adapter<SongListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemSongListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemSongListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]

        viewHolder.binding.apply {
            itemSongListTextViewSongName.text = item.track.name
            itemSongListTextViewAlbumName.text = item.track.album.name

            var allArtist: String = ""

            val numberArtists = item.track.artists.size

            for (artist in item.track.artists) {
                allArtist += artist.name

                allArtist += if (numberArtists>1) { ", " } else { "." }
            }

            itemSongListTextViewArtistName.text = allArtist

            Glide
                .with(context)
                .load(item.track.album.images.firstOrNull()?.url ?: "https://ryrsupport.net/wp-content/uploads/2018/05/que-significa-el-codigo-de-error-http-404-not-foun.jpg")
                .centerCrop()
                .circleCrop()
                .placeholder(R.drawable.play_button_icon)
                .into(itemSongListImageView);
        }

        viewHolder.itemView.setOnClickListener {
            callback.invoke(item)
        }
    }

    override fun getItemCount() = dataSet.size

    fun updateList(newList: List<ItemTrack>){
        dataSet = newList
        notifyDataSetChanged()
    }
}