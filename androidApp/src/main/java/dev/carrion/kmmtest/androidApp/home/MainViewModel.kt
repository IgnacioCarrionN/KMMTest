package dev.carrion.kmmtest.androidApp.home

import androidx.lifecycle.*
import dev.carrion.kmmtest.common.FactBo
import dev.carrion.repository.FactRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val repository: FactRepository) : ViewModel() {

    class Factory(private val factRepository: FactRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(factRepository) as T
        }
    }

    private val _state: MutableLiveData<List<FactBo>> = MutableLiveData()

    val state: LiveData<List<FactBo>>
        get() = _state

    private val _navigate: MutableSharedFlow<Long> = MutableSharedFlow()
    val navigate: SharedFlow<Long>
        get() = _navigate

    init {
        getFactsFromLocal()
        fetchNewFact()
    }

    private fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    private fun getFactsFromLocal() {
        viewModelScope.launch {
            repository.getFacts().collect {
                _state.postValue(it)
            }
        }
    }

    fun fetchNewFact() {
        viewModelScope.launch {
            repository.fetchFact()
        }
    }

    fun goToFactDetail(position: Int) {
        viewModelScope.launch {
            val factId = state.value?.get(position)?.id?.toLongOrNull()

            if (factId != null) {
                _navigate.emit(factId)
            }
        }
    }
}