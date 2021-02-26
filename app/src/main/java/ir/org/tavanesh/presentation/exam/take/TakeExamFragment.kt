package ir.org.tavanesh.presentation.exam.take

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
import ir.org.tavanesh.databinding.FragmentTakeExamBinding
import kotlinx.android.synthetic.main.fragment_take_exam.*

@AndroidEntryPoint
class TakeExamFragment : Fragment() {

    private  val viewModel: TakeExamViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTakeExamBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initViews(radioLayout, checkLayout, edtInput)

        val exam: Exam = arguments?.getSerializable(OBJECT) as Exam
        viewModel.setExam(exam)
        viewModel.getQuestions()

        viewModel.setToolbar(
            EsToolbar(
                titleResource = R.string.hint_exam,
                rightLead = ToolbarButton(
                    iconResource = R.drawable.ic_arrow_right_dark,
                    callback = {
                        viewModel.previewsQuestion()
                    }
                )
            )
        )

        viewModel.currentQuestion.observe(viewLifecycleOwner, {
            it?.let{
                examProgress.max = exam.questionCount
                examProgress.progress = it.index
            }
        })
    }

}