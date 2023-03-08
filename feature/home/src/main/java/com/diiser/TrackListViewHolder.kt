package com.diiser

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diiser.model.search.Track

class TrackListViewHolder(private val containerView: View) :
    RecyclerView.ViewHolder(containerView) {
    fun bind(track: Track) = with(track) {
        with(containerView) {
            findViewById<TextView>(R.id.itemArtistSubTitle).text = containerView.context.getString(
                R.string.home_item_subtitle,
                artist.name,
                album.title
            )
            Glide.with(containerView).load(album.cover).circleCrop()
                .placeholder(R.drawable.ic_art_track)
                .into(findViewById(R.id.itemArtistImg))
            findViewById<TextView>(R.id.itemArtistTitle).text = title
        }
    }

}