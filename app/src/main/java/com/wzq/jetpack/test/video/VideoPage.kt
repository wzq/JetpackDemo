package com.wzq.jetpack.test.video

import android.media.MediaDataSource
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.wzq.jetpack.R

/**
 * create by wzq on 2020/11/6
 *
 */
class VideoPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }


    lateinit var player: ExoPlayer
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val videoView = view.findViewById<PlayerView>(R.id.video)
        player = ExoPlayer.Builder(requireContext()).build()
        videoView.player = player

        val source = createExtractorMediaSource(mediaCatalog[0].mediaUri!!)
        player.prepare(source)
        player.playWhenReady = true;
    }


    private fun createExtractorMediaSource(uri: Uri): MediaSource {
        return ExtractorMediaSource.Factory(
            DefaultDataSourceFactory(context, "exoplayer-learning")
        )
            .createMediaSource(uri)
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}