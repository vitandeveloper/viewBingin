package com.example.marlonviana.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.areuin.android.room.AppDataBase
import com.example.marlonviana.model.DataCharacters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavViewModel : ViewModel(), LifecycleObserver {
    val respondCharacter = MutableLiveData<MutableList<DataCharacters>>()
    private val dao = AppDataBase.getInstance().daoRoom()

    //region VIEW
    fun getFavList(){
        getLocalFavs()
    }
    //endregion

    //region VIEWMODEL
    private fun getLocalFavs(){
        viewModelScope.launch(Dispatchers.IO) {
            respondCharacter.postValue(dao.getAllCharacters() as MutableList<DataCharacters>)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        getLocalFavs()
    }
    //endregion
}