package ir.org.tavanesh.presentation.auth.verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.data.platform.model.VerificationViews
import ir.org.tavanesh.databinding.FragmentVerificationBinding
import kotlinx.android.synthetic.main.fragment_verification.*

@AndroidEntryPoint
class VerificationFragment : Fragment() {

    private val viewModel: VerificationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVerificationBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.changeState(VerificationViews.MOBILE)

        edtVerifyCode.doAfterTextChanged {
            if(it.toString().length==6){
                viewModel.checkVerifyCode(it.toString())
            }
        }
    }

}