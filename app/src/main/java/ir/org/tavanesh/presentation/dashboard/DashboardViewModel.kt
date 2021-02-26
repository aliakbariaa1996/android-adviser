package ir.org.tavanesh.presentation.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.course.entity.Course
import ir.org.tavanesh.core.domain.course.repository.GetCourseListParams
import ir.org.tavanesh.core.domain.course.usecase.GetCourseListUseCase
import ir.org.tavanesh.core.domain.exam.entity.Exam
import ir.org.tavanesh.core.domain.exam.repository.GetExamListParams
import ir.org.tavanesh.core.domain.exam.usecase.GetExamListUseCase
import ir.org.tavanesh.core.utils.Navigate
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class DashboardViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val getCourseListUseCase: GetCourseListUseCase,
    private val getExamListUseCase: GetExamListUseCase,
) : BaseViewModel(viewUseCase) {

    private var courseList = mutableListOf<Course>()
    var courses = MutableLiveData<List<Course>>()
    private var isCoursesReady = false
    fun getCourses(){
        if(!isCoursesReady){
            viewModelScope.launch {
                viewUseCase.setProgress(true)
                val result = getCourseListUseCase.call(GetCourseListParams(1))
                viewUseCase.setProgress(false)
                result.fold(
                    success = {
                        courseList.addAll(it)
                        courses.postValue(courseList)
                        isCoursesReady = true
                    },
                    failure = {
                        viewUseCase.setFailure(it)
                    }
                )
            }
        }
    }

    private var examList = mutableListOf<Exam>()
    var exams = MutableLiveData<List<Exam>>()
    private var isExamsReady = false
    fun getExams(){
        if(!isExamsReady){
            viewModelScope.launch {
                viewUseCase.setProgress(true)
                val result = getExamListUseCase.call(GetExamListParams(1))
                viewUseCase.setProgress(false)
                result.fold(
                    success = {
                        examList.addAll(it)
                        examList.add(Exam(
                            id= "-1",
                            name= "بیشتر",
                            description= "نمایش همه آزمون ها",
                            image= "",
                            questionCount= 0,
                            testTime= 0,
                            centerId= "",
                        ))
                        exams.postValue(examList)
                        isExamsReady = true
                    },
                    failure = {
                        viewUseCase.setFailure(it)
                    }
                )
            }
        }
    }

    fun goToMoreCoursePage(){
        viewUseCase.navigateUser(Navigate(R.id.action_dashboardFragment_to_courseListFragment))
    }

}