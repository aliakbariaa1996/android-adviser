package ir.org.tavanesh.data.platform.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ir.org.tavanesh.core.utils.EsToolbar
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NavPage
import ir.org.tavanesh.core.utils.UiAlert
import ir.org.tavanesh.data.datasource.AdviserRemoteDataSource
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import javax.inject.Inject

class ViewUseCaseRepositoryImpl @Inject constructor() : ViewUseCaseRepository {

    private var progress = MutableLiveData<Boolean>()
    private var page = MutableLiveData<NavPage>()
    private var alert = MutableLiveData<UiAlert>()
    private var toolbarType = MutableLiveData<EsToolbar>()
    private var failure = MutableLiveData<Failure>()
    private var closeKeyBoard = MutableLiveData<Boolean>()

    override fun setProgress(isProgress: Boolean) {
        progress.postValue(isProgress)
    }

    override fun provideProgress(): LiveData<Boolean> {
        return progress
    }

    override fun navigateUser(navPage: NavPage) {
        page.postValue(navPage)
    }

    override fun provideNavigation(): LiveData<NavPage> {
        return page
    }

    override fun alertUser(uiAlert: UiAlert) {
        alert.postValue(uiAlert)
    }

    override fun provideAlert(): LiveData<UiAlert> {
        return alert
    }

    override fun setToolbar(toolbar: EsToolbar) {
        toolbarType.postValue(toolbar)
    }

    override fun provideToolbar(): LiveData<EsToolbar> {
        return toolbarType
    }

    override fun setFailure(failure: Failure) {
        this.failure.postValue(failure)
    }

    override fun provideFailure(): LiveData<Failure> {
        return failure
    }

    private var isClose = false
    override fun closeKeyboard() {
        closeKeyBoard.postValue(!isClose)
    }

    override fun provideKeyboardCloseRequest() = closeKeyBoard

}