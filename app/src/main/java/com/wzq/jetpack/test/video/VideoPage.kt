//package com.wzq.jetpack.test.video
//
//import android.annotation.SuppressLint
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.google.android.exoplayer2.*
//import com.google.android.exoplayer2.ui.PlayerView
//import com.wzq.jetpack.R
//import timber.log.Timber
//
//
///**
// * create by wzq on 2020/11/6
// *
// */
//class VideoPage : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_video, container, false)
//    }
//
//    lateinit var player: ExoPlayer
//    lateinit var videoView: PlayerView
//    lateinit var playbackStateListener: PlaybackStateListener
//    lateinit var fullScreenBtn: View
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        videoView = view.findViewById(R.id.video)
//        fullScreenBtn = view.findViewById(R.id.exo_fullscreen_btn)
//        fullScreenBtn.setOnClickListener {
//            it.visibility = View.GONE
//        }
//        playbackStateListener = PlaybackStateListener()
//    }
//
//    class PlaybackStateListener : Player.EventListener {
//        override fun onPlaybackStateChanged(state: Int) {
//            val stateString: String = when (state) {
//                ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"  // 播放器已实例化，但尚未准备好。
//                ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -" //播放器无法从当前位置播放，因为没有缓冲足够的数据
//                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -" //可以立即从当前位置开始。这意味着如果播放器的playWhenReady属性为，则播放器将自动开始播放媒体true。如果是false，则播放器暂停
//                ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -" //播放器已完成播放媒体
//                else -> "UNKNOWN_STATE             -"
//            }
//            Timber.i(stateString)
//        }
//
//        override fun onTimelineChanged(timeline: Timeline, reason: Int) {
//            println(reason)
//        }
//
//        override fun onPositionDiscontinuity(reason: Int) {
//            println(reason)
//        }
//
//        override fun onIsPlayingChanged(isPlaying: Boolean) {
//            println(isPlaying)
//
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        initPlayer()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        releasePlayer()
//    }
//
//    override fun onResume() {
//        super.onResume()
//    }
//
//    private fun initPlayer() {
//        player = SimpleExoPlayer.Builder(requireContext()).build()
//        player.addListener(playbackStateListener)
//        videoView.player = player
//        val uri =
//            Uri.parse("video url")
//        val mediaItem: MediaItem = MediaItem.fromUri(uri)
//        player.setMediaItem(mediaItem)
//        player.playWhenReady = true
//        player.prepare()
//    }
//
//    private fun releasePlayer() {
//        player.removeListener(playbackStateListener)
//        player.release()
//    }
//
//}