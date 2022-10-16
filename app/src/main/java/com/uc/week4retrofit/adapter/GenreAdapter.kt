package com.uc.week4retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.uc.week4retrofit.R
import com.uc.week4retrofit.model.Genre
import com.uc.week4retrofit.model.MovieDetails


class GenreAdapter(private val dataSet: List<Genre>) :
            RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvGenre: TextView
            val cvGenre: CardView

            init {
                tvGenre = view.findViewById(R.id.tv_genre)
                cvGenre = view.findViewById(R.id.cv_genre)
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.card_genre, viewGroup, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.tvGenre.text = dataSet[position].name
        }

        override fun getItemCount() = dataSet.size
    }

