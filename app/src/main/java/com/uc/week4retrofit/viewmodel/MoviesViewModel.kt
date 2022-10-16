package com.uc.week4retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.week4retrofit.model.Genre
import com.uc.week4retrofit.model.MovieDetails
import com.uc.week4retrofit.repository.NowPlayingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.uc.week4retrofit.model.Result
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: NowPlayingRepository): ViewModel(){

        // Get Now Playing Data
        val _nowPlaying: MutableLiveData<ArrayList<Result>> by lazy {
            MutableLiveData<ArrayList<Result>>()
        }

        val nowPlaying: LiveData<ArrayList<Result>>
        get() = _nowPlaying


        fun getNowPlaying(apiKey: String, language: String, page: Int) =
            viewModelScope.launch {
                repository.getNowPlayingData(apiKey, language, page).let {
                    response ->
                        if (response.isSuccessful){
                            _nowPlaying.postValue(response.body()?.results as
                            ArrayList<Result>?)
                        }else{
                            Log.e("Get Data", "Failed!")
                        }
                }
            }

    //Get Movie Details Data
    val _movieDetails: MutableLiveData<MovieDetails> by lazy {
        MutableLiveData<MovieDetails>()
    }

    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetails


    fun getMovieDetails(apiKey: String, movieId: Int) =
        viewModelScope.launch {
            repository.getMovieDetailsData(apiKey, movieId).let {
                    response ->
                if (response.isSuccessful){
                    _movieDetails.postValue(response.body() as MovieDetails)
                }else{
                    Log.e("Get Movie Details Data", "Failed!")
                }
            }
        }


    //Get Genre Data
    val _genreDetails: MutableLiveData<List<Genre>> by lazy {
        MutableLiveData<List<Genre>>()
    }

    val genreDetails: LiveData<List<Genre>>
        get() = _genreDetails

    fun getGenreDetails(apiKey: String, genreId: Int) =
        viewModelScope.launch {
            repository.getMovieDetailsData(apiKey, genreId).let {
                response ->
                if (response.isSuccessful){
                    _genreDetails.postValue(response.body() as List<Genre>)
                }else {
                    Log.e("Get Genre Details Data", "Failed!")
                }
            }
        }
}