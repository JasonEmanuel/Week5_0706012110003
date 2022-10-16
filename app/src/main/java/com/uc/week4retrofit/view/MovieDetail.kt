package com.uc.week4retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.uc.week4retrofit.adapter.CompanyAdapter
import com.uc.week4retrofit.adapter.GenreAdapter
import com.uc.week4retrofit.adapter.LanguageAdapter
import com.uc.week4retrofit.databinding.ActivityMovieDetailBinding
import com.uc.week4retrofit.helper.Const
import com.uc.week4retrofit.model.Genre
import com.uc.week4retrofit.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetail : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var companyAdapter: CompanyAdapter
    private lateinit var languageAdapter: LanguageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvOverviewDetail.visibility = View.INVISIBLE
        binding.imgPosterMovieDetail.visibility = View.INVISIBLE
        binding.tvTitleMovieDetail.visibility = View.INVISIBLE
        binding.rvLanguage.visibility = View.INVISIBLE
        binding.rvCompany.visibility = View.INVISIBLE
        binding.rvGenre.visibility = View.INVISIBLE
        binding.tvTitleCompanyDetail.visibility = View.INVISIBLE
        binding.tvTitleOverviewDetail.visibility = View.INVISIBLE
        binding.tvTitleLanguageDetails.visibility = View.INVISIBLE
        binding.tvTitleLanguageDetail.visibility = View.INVISIBLE

        val movieID = intent.getIntExtra("movie_id", 0)
        Toast.makeText(applicationContext, "Movie ID : ${movieID}", Toast.LENGTH_SHORT).show()

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel.getMovieDetails(Const.API_KEY, movieID)
        viewModel.movieDetails.observe(this, Observer {
            response->
            if (response != null){
                binding.progressBar2.visibility = View.INVISIBLE
                binding.tvOverviewDetail.visibility = View.VISIBLE
                binding.imgPosterMovieDetail.visibility = View.VISIBLE
                binding.tvTitleMovieDetail.visibility = View.VISIBLE
                binding.rvLanguage.visibility = View.VISIBLE
                binding.rvCompany.visibility = View.VISIBLE
                binding.rvGenre.visibility = View.VISIBLE
                binding.tvTitleCompanyDetail.visibility = View.VISIBLE
                binding.tvTitleOverviewDetail.visibility = View.VISIBLE
                binding.tvTitleLanguageDetails.visibility = View.VISIBLE
                binding.tvTitleLanguageDetail.visibility = View.VISIBLE
            }
            binding.tvTitleMovieDetail.apply {
                text = response.title
            }
        binding.tvOverviewDetail.text = response.overview

            binding.rvGenre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            genreAdapter = GenreAdapter(response.genres)
            binding.rvGenre.adapter = genreAdapter

            binding.rvCompany.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            companyAdapter = CompanyAdapter(response.production_companies)
            binding.rvCompany.adapter = companyAdapter

            binding.rvLanguage.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            languageAdapter = LanguageAdapter(response.spoken_languages)
            binding.rvLanguage.adapter = languageAdapter

            Glide.with(applicationContext)
                .load(Const.IMG_URL + response.backdrop_path)
                .into(binding.imgPosterMovieDetail)
        })
    }
}