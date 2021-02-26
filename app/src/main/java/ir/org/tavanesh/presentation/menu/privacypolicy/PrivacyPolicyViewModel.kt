package ir.org.tavanesh.presentation.menu.privacypolicy

import androidx.hilt.lifecycle.ViewModelInject
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.presentation.base.BaseViewModel

class PrivacyPolicyViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
) : BaseViewModel(viewUseCase) {

}