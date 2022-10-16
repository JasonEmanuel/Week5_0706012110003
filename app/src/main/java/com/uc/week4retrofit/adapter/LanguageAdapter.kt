package com.uc.week4retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.uc.week4retrofit.R
import com.uc.week4retrofit.model.SpokenLanguage


class LanguageAdapter(private val dataSet: List<SpokenLanguage>) :
        RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvLanguage: TextView
        val cvLanguage: CardView

        init {
            tvLanguage = view.findViewById(R.id.tv_language)
            cvLanguage = view.findViewById(R.id.cv_language)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_language, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvLanguage.text = dataSet[position].name
    }

    override fun getItemCount() = dataSet.size

}
