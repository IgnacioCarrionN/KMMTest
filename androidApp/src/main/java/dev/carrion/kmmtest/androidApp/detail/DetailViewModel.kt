package dev.carrion.kmmtest.androidApp.detail

import androidx.lifecycle.*
import dev.carrion.kmmtest.common.FactBo
import dev.carrion.repository.FactRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(private val id: Long, private val repository: FactRepository) : ViewModel() {

    class Factory(private val id: Long, private val repository: FactRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailViewModel(id, repository) as T
        }
    }

    private val _state: MutableLiveData<FactBo> = MutableLiveData()

    val state: LiveData<FactBo>
        get() = _state

    init {
        getFactById()
    }

    private fun getFactById() {
        viewModelScope.launch {
            repository.getFactById(id).collect {
                _state.postValue(it)
            }
        }
    }
}