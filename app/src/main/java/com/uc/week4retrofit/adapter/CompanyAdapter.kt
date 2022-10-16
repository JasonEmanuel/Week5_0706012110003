package com.uc.week4retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uc.week4retrofit.R
import com.uc.week4retrofit.helper.Const
import com.uc.week4retrofit.model.ProductionCompany

    class CompanyAdapter(private val dataSet: List<ProductionCompany>) :
            RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imgView: ImageView

            init {
                imgView = view.findViewById(R.id.iv_production_company)
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.card_production_company, viewGroup, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            Glide.with(viewHolder.itemView).load(Const.IMG_URL + dataSet[position].logo_path)
                .into(viewHolder.imgView)
        }

        override fun getItemCount() = dataSet.size

    }
