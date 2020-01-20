package com.nanaten.try_livedata_builder

import android.util.Log
import androidx.lifecycle.*
import com.nanaten.try_livedata_builder.api.ApiService
import com.nanaten.try_livedata_builder.entity.Repos
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {
    val api = ApiService.get()

    // LiveData Builder
    val items: LiveData<List<Repos>> = liveData {
        try {
            val repos = api.getRepos()  // getRepos()は suspend fun
            emit(repos) // emit()で値を更新する
        }
        catch (e: Exception) {
            Log.d("Error", "${e.message}")
            emit(listOf())
        }
    }

    // Normal LiveData
    // No Use
    val mutableData = MutableLiveData<List<Repos>>()
    fun getRepos() {
        viewModelScope.launch {
            try {
                val repos = api.getRepos()
                mutableData.postValue(repos)
            }
            catch (e: Exception) {
                Log.d("Error", "${e.message}")
            }
        }
    }
}