package com.diiser.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diiser.R
import com.diiser.model.search.Track

class TrackHomeAdapter : RecyclerView.Adapter<TrackListViewHolder>() {

    private lateinit var trackList: List<Track>

    fun setItem(artistList: List<Track>){
        this.trackList = artistList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TrackListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_artist_home,
                parent,
                false
            )
        )

    override fun getItemCount() = trackList.size

    override fun onBindViewHolder(holder: TrackListViewHolder, position: Int) =
        holder.bind(trackList[position])

}