package com.example.cars.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.MyResponse
import com.example.cars.model.CarView
import com.example.cars.model.toCarView
import com.example.domain.usecase.FetchCarsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CarsViewModel @Inject constructor(
    private val fetchCarsUseCase: FetchCarsUseCase,
) : ViewModel() {

    private var _myList: MutableLiveData<List<CarView>> = MutableLiveData(emptyList())
    val myList: LiveData<List<CarView>> get() = _myList

    private var _showLoading = MutableLiveData(false)
    val showLoading: MutableLiveData<Boolean> get() = _showLoading

    private var _error: Flow<MyResponse.Error> = emptyFlow()
    val error: Flow<MyResponse.Error> get() = _error

    init {
        getAllCars()
    }

    private fun getAllCars() {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.postValue(true)
            val carList = fetchCarsUseCase.invoke()
            carList.collect { myResponse ->
                when (myResponse) {
                    is MyResponse.Success -> {
                        _showLoading.postValue(false)
                        _myList.postValue(myResponse.data.map { it.toCarView() })
                    }
                    is MyResponse.Error -> {
                        _showLoading.postValue(false)
                        _error = flowOf(MyResponse.Error(myResponse.error))
                    }
                    else -> {
                    }
                }
            }
        }
    }
}