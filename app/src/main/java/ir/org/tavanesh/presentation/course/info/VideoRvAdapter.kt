package ir.org.tavanesh.presentation.course.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.org.tavanesh.core.domain.video.entity.Video
import ir.org.tavanesh.core.utils.base.TaskDiffCallback
import ir.org.tavanesh.databinding.RowRvVideosBinding
import ir.org.tavanesh.ext.withZeroString
import kotlinx.android.synthetic.main.row_rv_videos.view.*

class VideoRvAdapter(
    private val onItemClick: (video: Video, position:Int) -> Unit
) :
    ListAdapter<Video, VideoRvAdapter.ViewHolder>(TaskDiffCallback<Video>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowRvVideosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.model = item
        holder.click(item, onItemClick, position)
    }

    fun getItemInPosition(position: Int): Video {
        return this.getItem(position)
    }

    class ViewHolder(val binding: RowRvVideosBinding) : RecyclerView.ViewHolder(binding.root) {

        fun click(item: Video, onItemClick: (item: Video, position:Int) -> Unit, position:Int) {
            itemView.txtNumber.text = (position+1).withZeroString()
            itemView.setOnClickListener {
                onItemClick(item, position)
            }
        }
    }

}