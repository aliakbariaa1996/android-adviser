package ir.org.tavanesh.data.impl.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.course.entity.Course
import ir.org.tavanesh.core.domain.course.repository.CourseRepository
import ir.org.tavanesh.core.domain.course.repository.GetCourseListParams
import ir.org.tavanesh.core.domain.course.repository.GetCourseParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.data.datasource.CourseRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseRemoteDataSource: CourseRemoteDataSource,
): CourseRepository {
    override suspend fun getCourseList(params: GetCourseListParams): Result<List<Course>, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = courseRemoteDataSource.getCourseList(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun getCourse(params: GetCourseParams): Result<Course, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = courseRemoteDataSource.getCourse(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }
}