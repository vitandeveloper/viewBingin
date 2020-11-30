package com.areuin.android.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * @author Marlon Viana on 30/07/2019
 * @email 92marlonViana@gmail.com
 */
interface ApiMarvel {
    @GET ("characters")
    @Headers("Content-Type:application/json", "Accept:application/json")
    fun getCharacters (
            @Query ("ts")ts:String,
            @Query ("apikey")apiKey:String,
            @Query ("hash")hash:String,
            @Query ("offset")offset:Int,
            @Query ("limit")limit:Int
    ): Call<ResponseBody>

    @GET ("characters/{Id}")
    @Headers("Content-Type:application/json", "Accept:application/json")
    fun getCharacterData (
        @Path ("Id")id:String,
        @Query ("ts")ts:String,
        @Query ("apikey")apiKey:String,
        @Query ("hash")hash:String
    ): Call<ResponseBody>
}