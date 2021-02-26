package ir.org.tavanesh.presentation.course.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.core.domain.course.entity.Course
import ir.org.tavanesh.core.domain.course.repository.GetCourseListParams
import ir.org.tavanesh.core.domain.course.usecase.GetCourseListUseCase
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class CourseListViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val getCourseListUseCase: GetCourseListUseCase,
) : BaseViewModel(viewUseCase) {

    private var courseList = mutableListOf<Course>()
    var courses = MutableLiveData<List<Course>>()
    private var page: Int = 1
    private var allDataFetched = false
    fun getCourses() {
        if(!allDataFetched){
            viewModelScope.launch {
                viewUseCase.setProgress(true)
                val result = getCourseListUseCase.call(GetCourseListParams(page))
                viewUseCase.setProgress(false)
                result.fold(
                    success = {
                        courseList.addAll(it)
                        courses.postValue(courseList)
                        page++
                        if(it.isEmpty()) allDataFetched = true
                    },
                    failure = {
                        viewUseCase.setFailure(it)
                    }
                )
            }
        }
    }

}