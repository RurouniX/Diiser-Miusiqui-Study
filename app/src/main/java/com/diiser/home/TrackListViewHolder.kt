package com.diiser.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diiser.R
import com.diiser.model.search.Track
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_artist_home.*

class TrackListViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(track: Track) = with(track) {
        itemArtistSubTitle.text = containerView.context.getString(R.string.home_item_subtitle,artist.name, album.title)
        Glide.with(containerView).load(album.cover).circleCrop().placeholder(R.drawable.ic_art_track).into(itemArtistImg)
        itemArtistTitle.text = title
    }

}