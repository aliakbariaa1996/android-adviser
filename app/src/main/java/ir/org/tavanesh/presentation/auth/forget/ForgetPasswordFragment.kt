package ir.org.tavanesh.presentation.auth.forget

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.data.platform.model.ForgetPasswordViews
import ir.org.tavanesh.databinding.FragmentForgetPasswordBinding
import kotlinx.android.synthetic.main.fragment_forget_password.*

@AndroidEntryPoint
class ForgetPasswordFragment : Fragment() {

    private val viewModel: ForgetPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentForgetPasswordBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.changeState(ForgetPasswordViews.MOBILE)

        edtVerifyCode.doAfterTextChanged {
            if(it.toString().length==6){
                viewModel.checkVerifyCode(it.toString())
            }
        }

    }


}