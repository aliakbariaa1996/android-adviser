package ir.org.tavanesh.presentation.menu.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.core.domain.user.entity.User
import ir.org.tavanesh.core.domain.user.repository.GetUserParams
import ir.org.tavanesh.core.domain.user.repository.UpdateUserParams
import ir.org.tavanesh.core.domain.user.usecase.GetUserUseCase
import ir.org.tavanesh.core.domain.user.usecase.UpdateUserUseCase
import ir.org.tavanesh.core.utils.Toast
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.InputStream

class ProfileViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
) : BaseViewModel(viewUseCase) {

    private var user: User = User()
    var userLive = MutableLiveData<User>()
    private var isDataReady = false

    fun getUser() {
        if (!isDataReady) {
            viewModelScope.launch {
                viewUseCase.setProgress(true)
                val result = getUserUseCase.call(GetUserParams())
                viewUseCase.setProgress(false)

                result.fold(
                    success = {
                        user = it
                        userLive.postValue(user)
                        isDataReady = true
                    },
                    failure = { failure ->
                        viewUseCase.setFailure(failure)
                    }
                )
            }
        }
    }

    fun updateUser(username: String, email: String, age: String) {
        viewModelScope.launch {
            viewUseCase.setProgress(true)

            val usernamePart = username.toRequestBody(MultipartBody.FORM)
            val emailPart = email.toRequestBody(MultipartBody.FORM)
            val agePart = age.toRequestBody(MultipartBody.FORM)
            var body: MultipartBody.Part? = null
            imageAvatarPath.let {
                if (it != "") {
                    val file = File(it)

                    body = MultipartBody.Part.createFormData(
                        "avatar",
                        it,
                        file.asRequestBody("image/*".toMediaTypeOrNull())
                    )
                }
            }

            val result = updateUserUseCase.call(
                UpdateUserParams(
                    usernamePart, emailPart, agePart, body
                )
            )
            viewUseCase.setProgress(false)

            result.fold(
                success = {
                    viewUseCase.alertUser(Toast("تغییرات با موفقیت ثبت شدند."))
                },
                failure = { failure ->
                    viewUseCase.setFailure(failure)
                }
            )
        }
    }

    private var imageAvatarPath = ""
    fun setAvatarPath(path: String) {
        imageAvatarPath = path
    }

}