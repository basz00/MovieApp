package com.baz.movie.movies.nowplaying

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baz.movie.extensions.retrofitCallback
import com.baz.movie.movies.data.Movie
import com.baz.movie.movies.data.MovieResult
import com.baz.movie.movies.data.MovieResponse
import javax.inject.Inject

internal class NowPlayingRemoteRepository @Inject constructor(
        private val api: NowPlayingApi) : NowPlayingRepository {

    private val movieResultLiveData = MutableLiveData<MovieResult>()
    private val movies = mutableListOf<Movie>()

    override fun getNowPlayingMovies(page: Int): LiveData<MovieResult> {
        movieResultLiveData.value = MovieResult.Progress(true)
        api.nowPlaying(page).enqueue(retrofitCallback(::onSuccess, ::onFailed))
        return movieResultLiveData
    }

    private fun onSuccess(movieResponse: MovieResponse?) {
        movieResultLiveData.value = MovieResult.Progress(false)
        movieResponse?.movies?.let {
            movies += it
            movieResultLiveData.value = MovieResult.Success(movies.toList())
        }
    }

    private fun onFailed(error: String) {
        movieResultLiveData.value = MovieResult.Progress(false)
        movieResultLiveData.value = MovieResult.Failed(error)
    }
}