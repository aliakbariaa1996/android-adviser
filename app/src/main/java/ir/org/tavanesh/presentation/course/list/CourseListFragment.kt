package ir.org.tavanesh.presentation.course.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.R
import ir.org.tavanesh.core.utils.EsToolbar
import ir.org.tavanesh.core.utils.OBJECT
import ir.org.tavanesh.core.utils.ToolbarButton
import ir.org.tavanesh.databinding.FragmentCourseListBinding
import kotlinx.android.synthetic.main.fragment_course_list.*

@AndroidEntryPoint
class CourseListFragment : Fragment() {

    private  val viewModel: CourseListViewModel  by viewModels()


    private val courseAdapter = CourseListAdapter{ course ->
        val bundle = Bundle()
        bundle.putSerializable(OBJECT, course)
        viewModel.navigateTo(R.id.action_courseListFragment_to_courseInfoFragment, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCourseListBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setToolbar(
            EsToolbar(
                titleResource = R.string.hint_courses,
                leftLead = ToolbarButton(
                    R.drawable.ic_arrow_left_dark,
                    callback = { viewModel.back() }
                )
            )
        )

        initCourses()

    }

    private fun initCourses(){
        rvCourse.adapter = courseAdapter

        viewModel.courses.observe(viewLifecycleOwner, {
            it?.let{
                courseAdapter.submitList(it)
                courseAdapter.notifyDataSetChanged()
            }
        })

        viewModel.getCourses()
        rvCourse.setOnLoadMoreListener {
            viewModel.getCourses()
        }
    }

}