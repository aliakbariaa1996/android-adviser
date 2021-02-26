package ir.org.tavanesh.presentation.adviser.advicecenterlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.advisor.repository.GetAdviceCentersParams
import ir.org.tavanesh.core.utils.EsToolbar
import ir.org.tavanesh.core.utils.OBJECT
import ir.org.tavanesh.core.utils.ToolbarButton
import ir.org.tavanesh.databinding.FragmentAdviceCenterListBinding
import kotlinx.android.synthetic.main.fragment_advice_center_list.*

@AndroidEntryPoint
class AdviceCenterListFragment : Fragment() {

    private val viewModel: AdviceCenterListViewModel by viewModels()
    private val adviceCenterAdapter = AdviceCenterRvAdapter{ adviceCenter ->
        val bundle = Bundle()
        bundle.putSerializable(OBJECT, adviceCenter)
        viewModel.navigateTo(R.id.action_adviceCenterListFragment_to_adviceCenterFragment,bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAdviceCenterListBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val params: GetAdviceCentersParams = arguments?.getSerializable(OBJECT) as GetAdviceCentersParams
        viewModel.setParams(params)

        viewModel.setToolbar(
            EsToolbar(
                titleResource = R.string.hint_consult_centers,
                leftLead = ToolbarButton(
                    R.drawable.ic_arrow_left_dark,
                    callback = { viewModel.back() }
                )
            )
        )

        initAdviceCenterListView()

    }

    private fun initAdviceCenterListView(){
        rvAdviceCenterList.adapter = adviceCenterAdapter

        viewModel.adviceCenters.observe(viewLifecycleOwner, {
            it?.let {
                adviceCenterAdapter.submitList(it)
                adviceCenterAdapter.notifyDataSetChanged()
            }
        })

        viewModel.getAdviceCenters()
        rvAdviceCenterList.setOnLoadMoreListener {
            viewModel.getAdviceCenters()
        }

    }

}