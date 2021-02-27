package com.example.spotifyprojectmaster.presentation.fragments.detailartist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyprojectmaster.base.BaseState
import com.example.spotifyprojectmaster.data.SpotifyRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class SongDetailViewModel: ViewModel() {

    private val state = MutableLiveData<BaseState>()
    fun getState(): LiveData<BaseState> = state

    fun requestInformation(artistId: String) {
        viewModelScope.launch {
            try {
                val response = SpotifyRepository().getArtistInfo(artistId)
                state.postValue(BaseState.Normal(SongDetailState(response)))
            } catch (e: Exception) {
                state.postValue(BaseState.Error(e))
            }
        }
    }

}