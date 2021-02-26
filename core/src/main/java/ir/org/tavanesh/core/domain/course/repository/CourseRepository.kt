package ir.org.tavanesh.core.domain.course.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.course.entity.Course
import ir.org.tavanesh.core.utils.Failure

interface CourseRepository {
    suspend fun getCourseList(params: GetCourseListParams): Result<List<Course>, Failure>

    suspend fun getCourse(params: GetCourseParams): Result<Course, Failure>
}