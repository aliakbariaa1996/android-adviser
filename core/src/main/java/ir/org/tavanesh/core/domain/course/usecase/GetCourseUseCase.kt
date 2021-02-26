package ir.org.tavanesh.core.domain.course.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.course.entity.Course
import ir.org.tavanesh.core.domain.course.repository.CourseRepository
import ir.org.tavanesh.core.domain.course.repository.GetCourseParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetCourseUseCase @Inject constructor(
    private val advisedCourseRepository: CourseRepository
) : Usecase<Course, GetCourseParams>() {
    override suspend fun call(
        params: GetCourseParams?
    ): Result<Course, Failure> {
        return params?.let {
            advisedCourseRepository.getCourse(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
