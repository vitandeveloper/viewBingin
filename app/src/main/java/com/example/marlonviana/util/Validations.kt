package com.example.marlonviana.util

import android.content.Context
import android.net.ConnectivityManager
import com.example.marlonviana.AppMaster

class Validations {
    companion object{

        fun netWorking():Boolean{
            val cm = AppMaster.contextApp().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork?.isConnectedOrConnecting == true
        }
    }
}