package ir.org.tavanesh.presentation.adviser.advicecenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.advisor.entity.AdviceCenter
import ir.org.tavanesh.core.utils.EsToolbar
import ir.org.tavanesh.core.utils.ITEM_ID
import ir.org.tavanesh.core.utils.OBJECT
import ir.org.tavanesh.core.utils.ToolbarButton
import ir.org.tavanesh.databinding.FragmentAdviceCenterBinding
import ir.org.tavanesh.presentation.adviser.adviser.AdviserRvAdapter
import kotlinx.android.synthetic.main.fragment_advice_center.*

@AndroidEntryPoint
class AdviceCenterFragment : Fragment() {

    private val viewModel: AdviceCenterViewModel by viewModels()
    private val adviserAdapter = AdviserRvAdapter{ adviser ->
        val bundle = Bundle()
        bundle.putSerializable(OBJECT, adviser)
        bundle.putString(ITEM_ID, viewModel.adviceCenterId)
        viewModel.navigateTo(R.id.action_adviceCenterFragment_to_adviserFragment, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAdviceCenterBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adviceCenter: AdviceCenter = arguments?.getSerializable(OBJECT) as AdviceCenter
        viewModel.getAdviceCenterInfo(adviceCenter)

        viewModel.setToolbar(
            EsToolbar(
                titleResource = R.string.hint_consult_centers_info,
                leftLead = ToolbarButton(
                    R.drawable.ic_arrow_left_dark,
                    callback = { viewModel.back() }
                )
            )
        )

        initAdviserListView()
    }

    private fun initAdviserListView() {
        rvAdvisers.adapter = adviserAdapter

        viewModel.advisers.observe(viewLifecycleOwner, {
            it?.let{
                adviserAdapter.submitList(it)
                adviserAdapter.notifyDataSetChanged()
            }
        })

        viewModel.getAdvisers()
        rvAdvisers.setOnLoadMoreListener {
            viewModel.getAdvisers()
        }

    }

}