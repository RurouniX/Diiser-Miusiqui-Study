package com.diiser.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diiser.R
import com.diiser.databinding.ListItemHomeBinding
import com.diiser.model.home.DataModel

class HomeViewAdapter(private val clickListHomeItem: (DataModel) -> Unit) :
    RecyclerView.Adapter<HomeViewHolder>() {

    private var searchModelList = listOf<DataModel>()

    fun setItems(dataModelList: List<DataModel>) {
        this.searchModelList = dataModelList
        notifyItemRangeChanged(0, searchModelList.size)
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
        holder.bind(searchModelList[position], clickListHomeItem)

}


class HomeViewHolder(private val view: ListItemHomeBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(dataModel: DataModel, clickListHomeItem: (DataModel) -> Unit) {
        with(view) {
            Glide.with(view.root).load(dataModel.album.cover).circleCrop()
                .placeholder(R.drawable.ic_music_placeholder)
                .into(itemArtistImg)

            itemArtistTitle.text = dataModel.title
            itemArtistSubTitle.text = dataModel.artist.name
            root.setOnClickListener {
                clickListHomeItem(dataModel)
            }
        }
    }
}