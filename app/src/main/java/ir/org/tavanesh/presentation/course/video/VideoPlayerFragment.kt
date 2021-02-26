package ir.org.tavanesh.presentation.course.video

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.core.utils.ITEM_ID
import ir.org.tavanesh.data.platform.model.PlayerStatus
import ir.org.tavanesh.databinding.FragmentVideoPlayerBinding
import kotlinx.android.synthetic.main.fragment_video_player.*


@AndroidEntryPoint
class VideoPlayerFragment : Fragment() {

    private val viewModel: VideoPlayerViewModel by viewModels()
    private var exoPlayer: SimpleExoPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentVideoPlayerBinding.inflate(inflater)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoId: String = arguments?.getString(ITEM_ID) as String
        viewModel.getVideo(videoId)

        viewModel.initViews(radioLayout, checkLayout, edtInput)

        viewModel.preventLock()
        initPlayer()

    }

    private fun initPlayer(){

        viewModel.playerState.observe(viewLifecycleOwner, {
            it?.let {
                when (it) {
                    PlayerStatus.HOLD -> {
                    }
                    PlayerStatus.START -> {
                        viewModel.video?.let { video ->
                            exoPlayer = SimpleExoPlayer.Builder(requireContext()).build()
                            val mediaSource = buildMediaSource(Uri.parse(video.videoUrl))
                            exoPlayer?.prepare(mediaSource)

                            mediaPlayer.player = exoPlayer
                            exoPlayer?.playWhenReady = true
                            viewModel.initVideoListener(exoPlayer!!)
                            addListenerToPlayer()
                        }
                    }
                    PlayerStatus.PLAY -> {
                        exoPlayer?.playWhenReady = true
                    }
                    PlayerStatus.PAUSE -> {
                        exoPlayer?.playWhenReady = false
                    }
                    PlayerStatus.RESTART -> {
                    }
                    PlayerStatus.CLOSE -> {
                        exoPlayer?.release()
                    }
                }
            }
        })


    }

    private fun addListenerToPlayer(){
        exoPlayer?.addListener(object : Player.EventListener {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                when (playbackState) {
                    Player.STATE_IDLE -> { }
                    Player.STATE_BUFFERING -> { }
                    Player.STATE_READY -> { }
                    Player.STATE_ENDED -> {
                        viewModel.stopTimer()
                    }
                }
            }
        })
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(context, "exoplayer-codelab")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }

    override fun onDestroy() {
        viewModel.stopTimer()
        super.onDestroy()
    }

    override fun onPause() {
        viewModel.setPlayerState(PlayerStatus.PAUSE)
        super.onPause()
    }

    override fun onResume() {
        viewModel.setPlayerState(PlayerStatus.PLAY)
        super.onResume()
    }

    override fun onDetach() {
        exoPlayer?.let{
            it.stop()
            it.release()
            it.clearVideoSurface()
        }
        viewModel.stopTimer()
        super.onDetach()
    }
}