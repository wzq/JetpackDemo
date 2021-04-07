// package com.wzq.jetpack.test.video
//
// import android.content.Context
// import android.graphics.Color
// import android.net.Uri
// import android.util.AttributeSet
// import android.util.Log
// import android.view.ViewGroup
// import android.widget.FrameLayout
// import com.google.android.exoplayer2.ExoPlayer
// import com.google.android.exoplayer2.MediaItem
// import com.google.android.exoplayer2.Player
// import com.google.android.exoplayer2.SimpleExoPlayer
// import com.google.android.exoplayer2.ui.PlayerView
// import timber.log.Timber
//
// /**
// * create by wzq on 2020/11/16
// *
// */
// class CustomVideoView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
//    FrameLayout(context, attrs, defStyleAttr), Player.EventListener {
//
//    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
//
//    constructor(context: Context) : this(context, null)
//
//    init {
//        layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
//        setBackgroundColor(Color.BLACK)
//        initPlayer()
//    }
//
//    lateinit var player: ExoPlayer
//
//    var currentUrl: String? = null
//
//    var needResume = false
//
//    private fun initPlayer() {
//        val videoView = PlayerView(context)
//        player = SimpleExoPlayer.Builder(context).build()
//        player.addListener(this)
//        player.prepare()
//        videoView.player = player
//        addView(videoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
//        Timber.i("player init")
//    }
//
//    fun setUrl(url: String) {
//        if (currentUrl == url) {
//            //same url
//        } else {
//            currentUrl = url
//            val uri = Uri.parse(url)
//            val mediaItem: MediaItem = MediaItem.fromUri(uri)
//            player.setMediaItem(mediaItem)
//            Timber.i("player prepare")
//        }
//    }
//
//    fun play() {
//        if (!player.isPlaying) {
//            player.play()
//        }
//    }
//
//    private fun pause() {
//        needResume = if (player.isPlaying) {
//            player.pause()
//            true
//        } else {
//            false
//        }
//    }
//
//    private fun resume() {
//        if (!player.isPlaying && needResume) {
//            player.play()
//        }
//        needResume = false
//    }
//
//    fun releasePlayer() {
//        player.removeListener(this)
//        player.release()
//        Timber.i("player release")
//    }
//
//    override fun setActivated(activated: Boolean) {
//        if (activated) {
//            resume()
//        } else {
//            pause()
//        }
//        super.setActivated(activated)
//    }
//
//    override fun onPlaybackStateChanged(state: Int) {
//        val stateString: String = when (state) {
//            ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"  // 播放器已实例化，但尚未准备好。
//            ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -" //播放器无法从当前位置播放，因为没有缓冲足够的数据
//            ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -" //可以立即从当前位置开始。这意味着如果播放器的playWhenReady属性为，则播放器将自动开始播放媒体true。如果是false，则播放器暂停
//            ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -" //播放器已完成播放媒体
//            else -> "UNKNOWN_STATE             -"
//        }
//        Timber.i(stateString)
//    }
//
// }
