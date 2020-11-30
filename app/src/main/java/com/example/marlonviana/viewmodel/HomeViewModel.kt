package com.example.marlonviana.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.areuin.android.api.RequestApiMarvel
import com.example.marlonviana.AppMaster
import com.example.marlonviana.model.RespondCharacter
import com.example.marlonviana.util.Validations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.marlonviana.R

class HomeViewModel:ViewModel(), LifecycleObserver {
    private val ctx = AppMaster.contextApp()
    val respondCharacter = MutableLiveData<RespondCharacter>()
    val respondCharacterData = MutableLiveData<RespondCharacter>()

    //region VIEW
    fun getListMarvel(){
        doRequestCharacters()
    }

    fun getDataCharacter(id:String){
        doRequestDataCharacter(id)
    }
    //endregion

    //region VIEWMODEL
    private fun doRequestCharacters(){
        if (Validations.netWorking())
        {
            viewModelScope.launch(Dispatchers.IO) {
                var pag = 0
                respondCharacter.value?.data?.offset?.let { pag = it+20 }
                val respond = RequestApiMarvel().characters(pag)
                respondCharacter.postValue(respond)
            }
        }
        else
        {
            respondCharacter.value = RespondCharacter().apply {
                message = ctx.getString(R.string.not_internet)
            }
        }
    }

    private fun doRequestDataCharacter(id:String){
        if (Validations.netWorking())
        {
            viewModelScope.launch(Dispatchers.IO) {
                val respond = RequestApiMarvel().characterData(id)
                respondCharacterData.postValue(respond)
            }
        }
        else
        {
            respondCharacterData.value = RespondCharacter().apply {
                message = ctx.getString(R.string.not_internet)
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStar() {
        doRequestCharacters()
    }
    //endregion

}