package com.dicoding.tripinaja

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CardViewDestinationAdaptor(private val listDestination: ArrayList<Destination>) :
    RecyclerView.Adapter<CardViewDestinationAdaptor.CardViewViewHolder>() {

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_destination)
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var tvCategory: TextView = itemView.findViewById(R.id.tv_category)
        var tvRating: TextView = itemView.findViewById(R.id.tv_rating)
        var tvDescription: TextView = itemView.findViewById(R.id.tv_description)
    }

    override fun getItemCount(): Int {
        return listDestination.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_destination, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val destination = listDestination[position]
        Glide.with(holder.itemView.context)
            .load(destination.photo)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)
        holder.tvName.text = destination.name
        holder.tvCategory.text = destination.category
        holder.tvRating.text = destination.rating
        holder.tvDescription.text = destination.description
        holder.itemView.setOnClickListener {

            val mDetailFragment = DetailFragment()
            val name = listDestination[holder.adapterPosition].name
            val category = listDestination[holder.adapterPosition].category
            val rating = listDestination[holder.adapterPosition].rating
            val description = listDestination[holder.adapterPosition].description
            val address = listDestination[holder.adapterPosition].address
            val openDay = listDestination[holder.adapterPosition].openDate
            val openTime = listDestination[holder.adapterPosition].openTime
            val mapLink = listDestination[holder.adapterPosition].mapLink
            val photo = listDestination[holder.adapterPosition].photo

            val mBundle = Bundle()
            mBundle.putString(DetailFragment.EXTRA_NAME, name)
            mBundle.putString(DetailFragment.EXTRA_CATEGORY, category)
            mBundle.putString(DetailFragment.EXTRA_RATING, rating)
            mBundle.putString(DetailFragment.EXTRA_DESCRIPTION, description)
            mBundle.putString(DetailFragment.EXTRA_ADDRESS, address)
            mBundle.putString(DetailFragment.EXTRA_OPENDAY, openDay)
            mBundle.putString(DetailFragment.EXTRA_OPENTIME, openTime)
            mBundle.putString(DetailFragment.EXTRA_MAPLINK, mapLink)
            mBundle.putInt(DetailFragment.EXTRA_PHOTO, photo)
            mDetailFragment.arguments = mBundle

            val activity = it.context as AppCompatActivity
            activity.supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    mDetailFragment,
                    DetailFragment::class.java.simpleName
                )
                .commit()

        }
    }
}
