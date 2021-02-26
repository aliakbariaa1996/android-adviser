package ir.org.tavanesh.presentation.exam.show


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.exam.entity.Exam
import ir.org.tavanesh.core.utils.EsToolbar
import ir.org.tavanesh.core.utils.OBJECT
import ir.org.tavanesh.core.utils.ToolbarButton
import ir.org.tavanesh.databinding.FragmentExamShowBinding
import ir.org.tavanesh.ext.loadUrl
import kotlinx.android.synthetic.main.fragment_exam_show.*

@AndroidEntryPoint
class ExamShowFragment : Fragment() {

    private val viewModel: ExamShowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentExamShowBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exam: Exam = arguments?.getSerializable(OBJECT) as Exam
        viewModel.getExam(exam)

        viewModel.setToolbar(
            EsToolbar(
                titleResource = R.string.hint_exam_info,
                leftLead = ToolbarButton(
                    R.drawable.ic_arrow_left_dark,
                    callback = { viewModel.back() }
                )
            )
        )

        viewModel.exam.observe(viewLifecycleOwner, {
            it?.let{
                imgExam.loadUrl(it.image)
            }
        })
    }
}