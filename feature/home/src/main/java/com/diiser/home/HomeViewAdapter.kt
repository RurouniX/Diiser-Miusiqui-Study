package com.diiser.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diiser.R
import com.diiser.databinding.ListItemHomeBinding
import com.diiser.model.home.DataModel

class HomeViewAdapter : RecyclerView.Adapter<HomeViewHolder>() {

    private  var searchModelList = listOf<DataModel>()

    fun setItems(dataModelList: List<DataModel>) {
        this.searchModelList = dataModelList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeViewHolder(
        ListItemHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = searchModelList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.bind(searchModelList[position])

}


class HomeViewHolder(private val view: ListItemHomeBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(dataModel: DataModel) {
        with(view) {
            Glide.with(view.root).load(dataModel.album.cover).circleCrop()
                .placeholder(R.drawable.ic_music_placeholder)
                .into(itemArtistImg)

            itemArtistTitle.text = dataModel.title
            itemArtistSubTitle.text = dataModel.artist.name
        }
    }
}