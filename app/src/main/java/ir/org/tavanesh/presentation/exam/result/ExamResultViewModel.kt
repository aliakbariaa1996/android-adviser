package ir.org.tavanesh.presentation.exam.result

import android.os.Bundle
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.G
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.advisor.repository.GetAdviceCentersParams
import ir.org.tavanesh.core.domain.city.entity.City
import ir.org.tavanesh.core.domain.city.repository.GetAllCityProvincesParams
import ir.org.tavanesh.core.domain.city.repository.GetCitiesOfProvinceParams
import ir.org.tavanesh.core.domain.city.repository.GetProvincesParams
import ir.org.tavanesh.core.domain.city.usecase.GetAllCityProvincesUseCase
import ir.org.tavanesh.core.domain.city.usecase.GetCitiesOfProvinceUseCase
import ir.org.tavanesh.core.domain.city.usecase.GetProvincesUseCase
import ir.org.tavanesh.core.domain.user.repository.UpdateUserAppUsageParams
import ir.org.tavanesh.core.domain.user.usecase.UpdateUserAppUsageUseCase
import ir.org.tavanesh.core.utils.Navigate
import ir.org.tavanesh.core.utils.OBJECT
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class ExamResultViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val getAllCityProvincesUseCase: GetAllCityProvincesUseCase,
    private val getProvincesUseCase: GetProvincesUseCase,
    private val getCitiesOfProvinceUseCase: GetCitiesOfProvinceUseCase,
    private val updateUserAppUsageUseCase: UpdateUserAppUsageUseCase,
) : BaseViewModel(viewUseCase) {

    var showCityPicker = MutableLiveData<Boolean>()
    fun setCityPickerViewStatus(isShow:Boolean){
        showCityPicker.postValue(isShow)
    }

    private var provinceList = mutableListOf<City>()
    var provinces = MutableLiveData<List<City>>()

    private var cityList = mutableListOf<City>()
    var cities = MutableLiveData<List<City>>()

    private lateinit var selectedCity:City

    fun getConsultant() {
        setCityPickerViewStatus(false)
        val bundle = Bundle()
        bundle.putSerializable(OBJECT, GetAdviceCentersParams(
            1, selectedCity.id, selectedCity.parentId, selectedCity.name)
        )
        provinceList.clear()
        provinces.postValue(provinceList)
        cityList.clear()
        cities.postValue(cityList)
        viewUseCase.navigateUser(
            Navigate(
                R.id.action_examResultFragment_to_adviceCenterListFragment, bundle
            )
        )
    }

    fun checkCityProvinceInit(){
        viewModelScope.launch {
            viewUseCase.setProgress(true)
            val result = getAllCityProvincesUseCase.call(GetAllCityProvincesParams())
            viewUseCase.setProgress(false)

            result.fold(
                success = {
                    setCityPickerViewStatus(true)
                    getProvinces()
                },
                failure = {
                  viewUseCase.setFailure(it)
                },
            )
        }
    }

    private fun getProvinces(){
        viewModelScope.launch {
            val result = getProvincesUseCase.call(GetProvincesParams())

            result.fold(
                success = {
                    provinceList.clear()
                    provinceList.addAll(it)
                    provinces.postValue(provinceList)
                },
                failure = {
                    viewUseCase.setFailure(it)
                },
            )
        }
    }

    fun getCities(provinceId:Int){
        viewModelScope.launch {
            val result = getCitiesOfProvinceUseCase.call(GetCitiesOfProvinceParams(provinceId))

            result.fold(
                success = {
                    cityList.clear()
                    cityList.addAll(it)
                    cities.postValue(cityList)
                },
                failure = {
                    viewUseCase.setFailure(it)
                },
            )
        }
    }
    fun setCityProvince(city:City){
        selectedCity = city
    }

    fun setFirstExamConfig(){
        if(G.isFirstExam){
            viewModelScope.launch {
                updateUserAppUsageUseCase.call(UpdateUserAppUsageParams(
                    isFirstExamTake = true,
                ))
            }
        }
    }
}