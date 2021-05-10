package com.zolax.chuckjokes.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zolax.chuckjokes.models.Root
import com.zolax.chuckjokes.repositories.ApiRepository
import com.zolax.chuckjokes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokesViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {
    val jokesData = MutableLiveData<Resource<Root>>()

    fun getJokes(count: Int) = viewModelScope.launch {
        jokesData.value?.let {
            if (it is Resource.Loading){
                return@launch
            }
        }
        jokesData.postValue(Resource.Loading())
        val response = apiRepository.getJokes(count)
        jokesData.postValue(response)
    }
}