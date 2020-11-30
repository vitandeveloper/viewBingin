package com.example.marlonviana.util

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Typeface
import android.widget.Toast
import com.example.marlonviana.AppMaster
import com.example.marlonviana.R

class MessageManager {

    companion object{
        @SuppressLint("NewApi")
        fun show(message:String?, status:Int, act: Activity){
            when(status){
                0->{
                    Toast.makeText(act,message,Toast.LENGTH_LONG).show()
                }
                1 ->{
                    Toast.makeText(act,message,Toast.LENGTH_LONG).show()
                }
                2 ->{
                    Toast.makeText(act,message,Toast.LENGTH_LONG).show()
                }
                else ->{
                    Toast.makeText(act,message,Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
