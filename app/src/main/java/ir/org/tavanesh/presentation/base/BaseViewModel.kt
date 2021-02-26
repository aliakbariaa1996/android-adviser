package ir.org.tavanesh.presentation.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import ir.org.tavanesh.core.utils.*
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository

open class BaseViewModel(
    private val viewUseCase: ViewUseCaseRepository,
) : ViewModel() {

    fun setToolbar(toolbar: EsToolbar){
        viewUseCase.setToolbar(toolbar)
    }

    fun back(){
        viewUseCase.navigateUser(PopBack())
    }

    fun navigateTo(destination:Int, bundle:Bundle?=null){
        viewUseCase.navigateUser(Navigate(destination, bundle))
    }

    fun showToast(message:String){
        viewUseCase.alertUser(Toast(message))
    }

    fun menuOpen(){
        viewUseCase.navigateUser(SlidingMenu())
    }

    fun setProgress(progress:Boolean){
        viewUseCase.setProgress(progress)
    }

    fun setFailure(failure: Failure){
        viewUseCase.setFailure(failure)
    }
}