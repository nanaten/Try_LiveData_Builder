package com.nanaten.try_livedata_builder

import android.util.Log
import androidx.lifecycle.*
import com.nanaten.try_livedata_builder.api.ApiService
import com.nanaten.try_livedata_builder.entity.Repos
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
}