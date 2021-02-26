package ir.org.tavanesh.presentation.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.R
import ir.org.tavanesh.core.utils.OBJECT
import ir.org.tavanesh.databinding.FragmentDashboardBinding
import ir.org.tavanesh.presentation.dashboard.adapter.MainCourseAdapter
import ir.org.tavanesh.presentation.dashboard.adapter.MainExamAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val viewModel: DashboardViewModel by viewModels()

    private val examAdapter = MainExamAdapter{ exam, position ->
        if(exam.id=="-1"){
            viewModel.navigateTo(R.id.action_dashboardFragment_to_examListFragment)
        }else{
            val bundle = Bundle()
            bundle.putSerializable(OBJECT, exam)
            viewModel.navigateTo(R.id.action_dashboardFragment_to_examShowFragment, bundle)
        }
    }

    private val courseAdapter = MainCourseAdapter{ course ->
        val bundle = Bundle()
        bundle.putSerializable(OBJECT, course)
        viewModel.navigateTo(R.id.action_dashboardFragment_to_courseInfoFragment, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDashboardBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCourses()
        initExams()
    }

    private fun initCourses(){
        rvCourse.adapter = courseAdapter

        viewModel.courses.observe(viewLifecycleOwner,  {
            it?.let{
                courseAdapter.submitList(it)
                courseAdapter.notifyDataSetChanged()
            }
        })

        viewModel.getCourses()
    }

    private fun initExams(){
        rvExams.adapter = examAdapter

        viewModel.exams.observe(viewLifecycleOwner,  {
            it?.let{
                examAdapter.submitList(it)
                examAdapter.notifyDataSetChanged()
            }
        })

        viewModel.getExams()
    }


}