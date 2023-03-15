package com.diiser.player

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diiser.model.player.Music
import com.diiser.player.databinding.ListOthersMusicBinding

class OthersMusicAdapter(private val clickListHomeItem: (Music) -> Unit) :
    RecyclerView.Adapter<OthersMusicViewHolder>() {

    private var musicList = listOf<Music>()

    fun setItems(musicList: List<Music>) {
        this.musicList = musicList
        notifyItemRangeChanged(0, musicList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OthersMusicViewHolder(
        ListOthersMusicBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = musicList.size

    override fun onBindViewHolder(holder: OthersMusicViewHolder, position: Int) {
        holder.bind(musicList[position], clickListHomeItem)

    }
}

class OthersMusicViewHolder(private val view: ListOthersMusicBinding) :
    RecyclerView.ViewHolder(view.root) {

    fun bind(music: Music, clickListHomeItem: (Music) -> Unit) {

        with(view) {
            txtMusicName.text = music.title
            txtDiscName.text = music.album.title

            Glide.with(view.root).load(music.album.cover).circleCrop()
                .placeholder(R.drawable.bg_player)
                .into(img)

            root.setOnClickListener {
                clickListHomeItem(music)
            }

        }

    }

}