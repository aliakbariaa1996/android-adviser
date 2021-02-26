package ir.org.tavanesh.data.mappers

import ir.org.tavanesh.core.domain.video.entity.Video
import ir.org.tavanesh.data.models.VideoModel

fun VideoModel.toVideo(): Video {
    return Video(
        id = this.id,
        name = this.name,
        description = this.description,
        videoUrl = videoUrl,
        questions = this.questions?.toQuestion() ?: run { mutableListOf() }
    )
}

fun List<VideoModel>.toVideo(): List<Video> {
    val list = mutableListOf<Video>()
    this.let {
        it.forEach { item ->
            list.add(item.toVideo())
        }
    }
    return list
}