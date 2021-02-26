package ir.org.tavanesh.presentation.menu.profile

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.gun0912.tedpermission.TedPermissionResult
import com.tedpark.tedpermission.rx2.TedRx2Permission
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.R
import ir.org.tavanesh.core.utils.EsToolbar
import ir.org.tavanesh.core.utils.ProviderFailure
import ir.org.tavanesh.core.utils.ToolbarButton
import ir.org.tavanesh.core.utils.provider_storage
import ir.org.tavanesh.databinding.FragmentProfileBinding
import ir.org.tavanesh.ext.loadCircle
import kotlinx.android.synthetic.main.fragment_profile.*

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setToolbar(
            EsToolbar(
                titleResource = R.string.hint_edit_profile,
                leftLead = ToolbarButton(
                    R.drawable.ic_arrow_left_dark,
                    callback = { viewModel.back() }
                )
            )
        )
        initUserInfo()
    }

    private fun initUserInfo(){
        viewModel.getUser()
        viewModel.userLive.observe(viewLifecycleOwner, {
            it?.let{
                ivAvatar.loadCircle(it.avatar)
            }
        })
        btnChangeAvatar.setOnClickListener {
            getPermissions()
        }
    }


    @SuppressLint("CheckResult")
    fun getPermissions() {
        TedRx2Permission.with(context)
            .setPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .request()
            .subscribe(
                { tedPermissionResult: TedPermissionResult ->
                    if (tedPermissionResult.isGranted) {
                        context?.let {
                            openImagePicker()
                        }
                    } else {
                        viewModel.setFailure(ProviderFailure(provider_storage))
                    }
                },
                { throwable: Throwable? -> }
            )
    }

    private fun openImagePicker(){
        CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .setBackgroundColor(R.color.toast_background)
            .start(requireContext(), this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val result = CropImage.getActivityResult(data)
            result.uri.path?.let {
                viewModel.setAvatarPath(it)
                ivAvatar.loadCircle(it)
            }
        }
    }


}