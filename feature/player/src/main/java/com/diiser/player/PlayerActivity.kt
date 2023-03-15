package com.diiser.player

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.diiser.model.player.Music
import com.diiser.navigation.player.PlayerNavigation
import com.diiser.player.IntentExtensions.getParcelable
import com.diiser.player.databinding.ActivityPlayerBinding
import com.diiser.viewmodelutils.observerEvents
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerActivity : AppCompatActivity() {

    private val othersMusicAdapter: OthersMusicAdapter by lazy {
        OthersMusicAdapter {
            clickListOtherMusicItem(it)
        }
    }

    private lateinit var binding: ActivityPlayerBinding
    private val imgHead by lazy { binding.imgHead }
    private val btnNext by lazy { binding.btnNext }
    private val btnBack by lazy { binding.btnBack }
    private val btnPlay by lazy { binding.btnPlay }
    private val list by lazy { binding.listOthersMusics }
    private val txtMusicName by lazy { binding.txtMusicName }
    private val txtArtistName by lazy { binding.txtArtistName }

    private val dataModelExtra by lazy {
        intent.getParcelable<Music>(
            PlayerNavigation.EXTRA_DATAMODEL,
        )
    }

    private val playerViewModel: PlayerViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setupView()
        setupViewModel()

    }

    private fun setupView(dataModel: Music? = dataModelExtra) {
        dataModel?.run {
            Glide.with(binding.root).load(album.cover).circleCrop()
                .placeholder(android.R.drawable.ic_delete)
                .into(imgHead)

            txtArtistName.text = getString(R.string.play_artist, artist.name)
            txtMusicName.text = title

            val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
                setAudioStreamType(AudioManager.STREAM_MUSIC)
                setDataSource(preview)
                prepare()
            }

            btnPlay.setOnClickListener {
                if (mediaPlayer?.isPlaying == false) {
                    btnPlay.background =
                        ContextCompat.getDrawable(this@PlayerActivity, R.drawable.icon_pause)
                    mediaPlayer.start()
                } else {
                    btnPlay.background =
                        ContextCompat.getDrawable(this@PlayerActivity, R.drawable.icon_play)
                    mediaPlayer?.pause()
                }
            }

            with(list) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = othersMusicAdapter
            }

        }

    }

    private fun setupViewModel() {
        with(playerViewModel) {
            getOthersMusicByArtist(dataModelExtra?.artist?.id ?: 0)

            musicLiveData.observerEvents(this@PlayerActivity, onSuccess = {
                othersMusicAdapter.setItems(it.musicList)
            }, onError = {

            }, onLoading = {

            })


        }
    }

    private fun clickListOtherMusicItem(music: Music) {
        setupView(music)
    }
}
