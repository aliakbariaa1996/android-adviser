package ir.org.tavanesh.presentation.menu.privacypolicy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.R
import ir.org.tavanesh.core.utils.EsToolbar
import ir.org.tavanesh.core.utils.ToolbarButton
import ir.org.tavanesh.databinding.FragmentPrivacyPolicyBinding

@AndroidEntryPoint
class PrivacyPolicyFragment : Fragment() {

    private val viewModel: PrivacyPolicyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPrivacyPolicyBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setToolbar(
            EsToolbar(
                titleResource = R.string.hint_privacy_policy,
                leftLead = ToolbarButton(
                    R.drawable.ic_arrow_left_dark,
                    callback = { viewModel.back() }
                )
            )
        )
    }

}