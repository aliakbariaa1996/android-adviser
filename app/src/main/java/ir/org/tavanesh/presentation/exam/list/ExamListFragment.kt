package ir.org.tavanesh.presentation.exam.list


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
import ir.org.tavanesh.databinding.FragmentExamListBinding
import kotlinx.android.synthetic.main.fragment_exam_list.*

@AndroidEntryPoint
class ExamListFragment : Fragment() {

    private  val viewModel: ExamListViewModel by viewModels()
    private val examAdapter = ExamAdapter{ exam ->
        val bundle = Bundle()
        bundle.putSerializable(OBJECT, exam)
        viewModel.navigateTo(R.id.action_examListFragment_to_examShowFragment, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentExamListBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setToolbar(
            EsToolbar(
                titleResource = R.string.hint_exam_list,
                leftLead = ToolbarButton(
                    R.drawable.ic_arrow_left_dark,
                    callback = { viewModel.back() }
                )
            )
        )

        initExamListView()
    }

    private fun initExamListView(){

        rvExams.adapter = examAdapter

        viewModel.exams.observe(viewLifecycleOwner, {
            it?.let {
                examAdapter.submitList(it)
                examAdapter.notifyDataSetChanged()
            }
        })

        viewModel.getExamList()
        rvExams.setOnLoadMoreListener {
            viewModel.getExamList()
        }

    }

}