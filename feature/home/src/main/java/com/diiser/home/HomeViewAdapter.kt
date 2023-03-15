package com.diiser.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diiser.home.databinding.ListItemHomeBinding
import com.diiser.model.player.Music

class HomeViewAdapter(private val clickListHomeItem: (Music) -> Unit) :
    RecyclerView.Adapter<HomeViewHolder>() {

    private var searchModelList = listOf<Music>()

    fun setItems(dataList: List<Music>) {
        this.searchModelList = dataList
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

    fun bind(data: Music, clickListHomeItem: (Music) -> Unit) {
        with(view) {
            Glide.with(view.root).load(data.album.cover).circleCrop()
                .placeholder(R.drawable.ic_music_placeholder)
                .into(itemArtistImg)

            itemArtistTitle.text = data.title
            itemArtistSubTitle.text = data.artist.name
            root.setOnClickListener {
                clickListHomeItem(data)
            }
        }
    }
}