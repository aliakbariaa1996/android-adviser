package ir.org.tavanesh.presentation.course.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.course.entity.Course
import ir.org.tavanesh.core.utils.EsToolbar
import ir.org.tavanesh.core.utils.ITEM_ID
import ir.org.tavanesh.core.utils.OBJECT
import ir.org.tavanesh.core.utils.ToolbarButton
import ir.org.tavanesh.databinding.FragmentCourseInfoBinding
import ir.org.tavanesh.ext.loadUrl
import kotlinx.android.synthetic.main.fragment_course_info.*

@AndroidEntryPoint
class CourseInfoFragment : Fragment() {

    private val viewModel: CourseInfoViewModel by viewModels()

    private val videoRvAdapter = VideoRvAdapter{ video, position ->
        val bundle = Bundle()
        bundle.putString(ITEM_ID, video.id)
        viewModel.navigateTo(R.id.action_courseInfoFragment_to_videoPlayerFragment, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCourseInfoBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val course: Course = arguments?.getSerializable(OBJECT) as Course
        viewModel.setCourse(course)

        viewModel.setToolbar(
            EsToolbar(
                titleResource = R.string.hint_courses,
                leftLead = ToolbarButton(
                    R.drawable.ic_arrow_left_dark,
                    callback = { viewModel.back() }
                )
            )
        )

        initCourseInfo()
        initVideosList()
    }

    private fun initCourseInfo(){
        viewModel.course.observe(viewLifecycleOwner, {
            it?.let{
                ivImage.loadUrl(it.imageUrl)
                ivImage.setBackgroundColor(it.getBackground(ivImage.context))
                viewModel.setToolbar(
                    EsToolbar(
                        titleText = it.name,
                        leftLead = ToolbarButton(
                            R.drawable.ic_arrow_left_dark,
                            callback = { viewModel.back() }
                        )
                    )
                )
            }
        })
    }

    private fun initVideosList(){
        rvCourse.adapter = videoRvAdapter

        viewModel.videos.observe(viewLifecycleOwner, {
            it?.let {
                videoRvAdapter.submitList(it)
                videoRvAdapter.notifyDataSetChanged()
            }
        })

        viewModel.getCourseInfo()
    }

}