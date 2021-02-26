package ir.org.tavanesh.presentation.menu.support

import androidx.hilt.lifecycle.ViewModelInject
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.presentation.base.BaseViewModel


class SupportViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
) : BaseViewModel(viewUseCase) {


}