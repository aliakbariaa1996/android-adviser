package ir.org.tavanesh.presentation.consult.show

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import ir.org.tavanesh.core.domain.consult.entity.Consult
import ir.org.tavanesh.core.domain.consult.repository.GetConsultingInfoParams
import ir.org.tavanesh.core.domain.consult.usecase.GetConsultingInfoUseCase
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class ConsultInfoViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val getConsultingInfoUseCase: GetConsultingInfoUseCase,
) : BaseViewModel(viewUseCase) {

    private var consult: Consult? = null
    var consultLive = MutableLiveData<Consult>()
    fun setConsult(consult: Consult){
        this.consult = consult
        consultLive.postValue(consult)
        getConsultInfo(consult.id)
    }
    private fun getConsultInfo(consulId:String){
        viewModelScope.launch {
            viewUseCase.setProgress(true)
            val result = getConsultingInfoUseCase.call(GetConsultingInfoParams(consulId))
            viewUseCase.setProgress(false)

            result.fold(
                success = {
                    consult = it
                    consultLive.postValue(it)
                },
                failure = {
                    viewUseCase.setFailure(it)
                }
            )
        }
    }
    fun getConsultPlaceLatLng():LatLng?{
        consult?.let{
            return LatLng(it.latitude, it.longitude)
        }?:run{
            return null
        }
    }
}