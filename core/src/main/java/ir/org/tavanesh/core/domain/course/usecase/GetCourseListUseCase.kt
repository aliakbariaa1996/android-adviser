package ir.org.tavanesh.core.domain.course.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.course.entity.Course
import ir.org.tavanesh.core.domain.course.repository.CourseRepository
import ir.org.tavanesh.core.domain.course.repository.GetCourseListParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetCourseListUseCase @Inject constructor(
    private val advisedCourseRepository: CourseRepository
) : Usecase<List<Course>, GetCourseListParams>() {
    override suspend fun call(
        params: GetCourseListParams?
    ): Result<List<Course>, Failure> {
        return params?.let {
            advisedCourseRepository.getCourseList(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
