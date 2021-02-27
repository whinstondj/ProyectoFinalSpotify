package com.example.spotifyprojectmaster.presentation.fragments.songlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyprojectmaster.base.BaseState
import com.example.spotifyprojectmaster.data.SpotifyRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class SongListViewModel: ViewModel() {

    private val state = MutableLiveData<BaseState>()
    fun getState(): LiveData<BaseState> = state

    fun requestInformation(playListId: String = "") {
        viewModelScope.launch {
            try {
                val response = SpotifyRepository().getAllSongs(playListId)
                state.postValue(BaseState.Normal(SongListState(response)))
            } catch (e: Exception) {
                state.postValue(BaseState.Error(e))
            }
        }
    }
}