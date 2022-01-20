package com.mon.mymovies.ui.main

import android.annotation.SuppressLint
import android.app.Instrumentation
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AndroidRuntimeException
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mon.mymovies.R
import com.mon.mymovies.databinding.ActivityMainBinding
import com.mon.mymovies.model.Movie
import com.mon.mymovies.model.MovieDbClient
import com.mon.mymovies.detail.DetailActivity
import kotlinx.coroutines.launch
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    companion object {
        private const val DEFAULT_REGION = "US"
    }

    private val moviesAdapter = MoviesAdapter(emptyList()) { navigateTo(it) }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            requestPopularMovies(isGranted)

            // comentamos la petición de permisos para poner uina region por defecto si deniegan la localización
            /*val message = when {
                isGranted -> "Permission Granted"
                shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_COARSE_LOCATION) -> "Should show Rationale"
                else -> "Permission Denied"
            }
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()*/
        }

    override fun onCreate(savedInstanceState: Bundle?) {  // carga la activity
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.recycler.adapter = moviesAdapter

        requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    @SuppressLint("MissingPermission")  //para que no comprubeve el permission check
    private fun requestPopularMovies(isLocationGranted: Boolean) {

        if(isLocationGranted){
            fusedLocationClient.lastLocation.addOnCompleteListener{
                doRequestPopularMovies(getRegionFromLocation(it.result))
            }
        } else {
            doRequestPopularMovies(DEFAULT_REGION)
        }
    }

    private fun doRequestPopularMovies(region: String) {
        lifecycleScope.launch {
            val apikey = getString(R.string.api_key)
            val popularMovies = MovieDbClient.service.listPopularMovies(apikey, region)
            moviesAdapter.movies = popularMovies.results
            moviesAdapter.notifyDataSetChanged()
        }
    }

    private fun getRegionFromLocation(location: Location?): String{
        if(location == null){
            return DEFAULT_REGION
        }
        val geocoder = Geocoder(this)
        val result = geocoder.getFromLocation(
            location.latitude,
            location.longitude,
            1
        )
        return result.firstOrNull()?.countryCode ?: DEFAULT_REGION  // empleamos ? para controlar la posibilidad de que el valor sea null y si lo es devolver por defecto US
    }

    private fun navigateTo(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
        intent

        startActivity(intent)
    }
}