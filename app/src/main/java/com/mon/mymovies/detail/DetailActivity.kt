package com.mon.mymovies.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.mon.mymovies.R
import com.mon.mymovies.databinding.ActivityDetailBinding
import com.mon.mymovies.model.Movie

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "DetailActivity:movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

        if (movie != null){
            title = movie.title
            Glide
                .with(this)
                .load("https://image.tmdb.org/t/p/w780/${movie.poster_path}")
                .into(binding.backdrop)
            binding.sumary.text = movie.overview +movie.overview +movie.overview +movie.overview +movie.overview +movie.overview +movie.overview +movie.overview +movie.overview +movie.overview
            bindDetailInfo(binding.detailInfo, movie)

            binding.fab.setOnClickListener {
                val message = "Add to favorites"
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun bindDetailInfo(detailInfo: TextView, movie: Movie) {
        detailInfo.text = buildSpannedString {
            appendInf( R.string.original_language, movie.original_language)
            appendInf( R.string.original_title, movie.original_title)
            appendInf( R.string.releade_date, movie.release_date)
            appendInf( R.string.popularity, movie.popularity.toString())
            appendInf( R.string.vote_average, movie.vote_average.toString())
        }
    }

    private fun SpannableStringBuilder.appendInf(stringRes: Int, value: String){
        bold {
            append(getString(stringRes))
            append(": ")
        }
        appendLine(value)
    }
}