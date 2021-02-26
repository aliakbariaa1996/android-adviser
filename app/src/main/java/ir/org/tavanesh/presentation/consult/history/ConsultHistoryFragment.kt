package ir.org.tavanesh.presentation.consult.history

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
import ir.org.tavanesh.databinding.FragmentConsultHistoryBinding
import kotlinx.android.synthetic.main.fragment_consult_history.*

@AndroidEntryPoint
class ConsultHistoryFragment : Fragment() {

    private val viewModel: ConsultHistoryViewModel by viewModels()
    private val consultAdapter = ConsultHistoryRvAdapter{ consult ->
        val bundle = Bundle()
        bundle.putSerializable(OBJECT, consult)
        viewModel.navigateTo(R.id.action_consultHistoryFragment_to_consultInfoFragment, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentConsultHistoryBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setToolbar(
            EsToolbar(
                titleResource = R.string.hint_consults,
                leftLead = ToolbarButton(
                    R.drawable.ic_arrow_left_dark,
                    callback = { viewModel.back() }
                )
            )
        )

        initConsults()
    }

    private fun initConsults(){
        rvConsults.adapter = consultAdapter

        viewModel.consults.observe(viewLifecycleOwner, {
            it?.let{
                consultAdapter.submitList(it)
                consultAdapter.notifyDataSetChanged()
            }
        })

        viewModel.getConsultHistory()
        rvConsults.setOnLoadMoreListener {
            viewModel.getConsultHistory()
        }
    }

}