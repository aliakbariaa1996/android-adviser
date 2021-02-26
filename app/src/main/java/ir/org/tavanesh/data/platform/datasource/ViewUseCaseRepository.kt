package ir.org.tavanesh.data.platform.datasource

import androidx.lifecycle.LiveData
import ir.org.tavanesh.core.utils.EsToolbar
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NavPage
import ir.org.tavanesh.core.utils.UiAlert

interface ViewUseCaseRepository {

    fun setProgress(isProgress: Boolean)
    fun provideProgress():LiveData<Boolean>

    fun navigateUser(navPage: NavPage)
    fun provideNavigation():LiveData<NavPage>

    fun alertUser(uiAlert: UiAlert)
    fun provideAlert():LiveData<UiAlert>

    fun setToolbar(toolbar: EsToolbar)
    fun provideToolbar():LiveData<EsToolbar>

    fun setFailure(failure: Failure)
    fun provideFailure(): LiveData<Failure>

    fun closeKeyboard()
    fun provideKeyboardCloseRequest():LiveData<Boolean>

}
