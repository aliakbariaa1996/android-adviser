package ir.org.tavanesh.data.mappers

import ir.org.tavanesh.core.domain.course.entity.Course
import ir.org.tavanesh.data.models.CourseModel

fun CourseModel.toCourse(): Course {
    return Course(
        id = this.id,
        name = this.title,
        teacherName = this.teacherName,
        timeCost = this.duration,
        backgroundColor = this.color,
        imageUrl = this.image,
        description = this.description,
        rate = this.rate.toFloat(),
        videos = this.videos?.toVideo() ?:run{ mutableListOf()}
    )
}

fun List<CourseModel>.toCourse(): List<Course> {
    val list = mutableListOf<Course>()
    this.let {
        it.forEach { item ->
            list.add(item.toCourse())
        }
    }
    return list
}