package com.areuin.android.dagger.component

import com.areuin.android.api.RequestApiMarvel
import com.areuin.android.dagger.module.NetworkModule

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ComponentNetwork {
    fun injectApiUser (requestApiMarvel: RequestApiMarvel)

    @Component.Builder
    interface Builder {
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): ComponentNetwork
    }
}