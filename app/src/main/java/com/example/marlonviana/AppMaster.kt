package com.example.marlonviana

import android.app.Application
import android.content.Context
import android.util.Log

class AppMaster: Application() {

    companion object {
        private lateinit var ctx: Context

        fun contextApp(): Context {
            return ctx
        }
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
    }
}