package ir.org.tavanesh.data.datasource

import ir.org.tavanesh.core.domain.course.entity.Course
import ir.org.tavanesh.core.domain.course.repository.GetCourseListParams
import ir.org.tavanesh.core.domain.course.repository.GetCourseParams

interface CourseRemoteDataSource {
    suspend fun getCourseList(params: GetCourseListParams): List<Course>

    suspend fun getCourse(params: GetCourseParams): Course
}