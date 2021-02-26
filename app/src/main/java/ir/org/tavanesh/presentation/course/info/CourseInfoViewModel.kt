package ir.org.tavanesh.presentation.course.info

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.core.domain.course.entity.Course
import ir.org.tavanesh.core.domain.course.repository.GetCourseParams
import ir.org.tavanesh.core.domain.course.usecase.GetCourseUseCase
import ir.org.tavanesh.core.domain.video.entity.Video
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class CourseInfoViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val getCourseUseCase: GetCourseUseCase,
) : BaseViewModel(viewUseCase) {

    var course = MutableLiveData<Course>()
    private var courseId:String = ""
    fun setCourse(course:Course){
        courseId = course.id
        this.course.postValue(course)
    }
    fun getCourseInfo() {
        viewModelScope.launch {
            viewUseCase.setProgress(true)
            val result = getCourseUseCase.call(GetCourseParams(courseId))
            viewUseCase.setProgress(false)
            result.fold(
                success = {
                    course.postValue(it)
                    videoList.clear()
                    videoList.addAll(it.videos!!)
                    videos.postValue(videoList)
                },
                failure = {
                    viewUseCase.setFailure(it)
                }
            )
        }
    }

    private var videoList = mutableListOf<Video>()
    var videos = MutableLiveData<List<Video>>()

}