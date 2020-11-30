package com.areuin.android.api

import android.app.Activity
import android.util.Log
import com.example.marlonviana.AppMaster
import com.example.marlonviana.R
import com.example.marlonviana.model.RespondStandar
import com.example.marlonviana.util.MessageManager
import com.example.marlonviana.util.RESPOND_CODE
import com.example.marlonviana.util.RESPOND_DATA
import com.example.marlonviana.util.RESPOND_MSJ
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response


class BodyApiManager {

    companion object{
        fun proccesData(response: Response<ResponseBody>): RespondStandar {
            return if (response.isSuccessful)
            {
                response.getResponseSuccesBody()
            }
            else
            {
                response.getResponseErrorBody()
            }
        }

        private fun Response<ResponseBody>.getResponseErrorBody(): RespondStandar {
            return try {
                val obj = this.getErrorBody()
                val message = obj?.getString(RESPOND_MSJ).toString()
                val code = this.code()

                RespondStandar().apply {
                    this.message = message
                    this.code  = code
                }
            }
            catch (e: Exception)
            {
                Log.e("GetData - Exception", e.toString())

                RespondStandar().apply {
                    code = this@getResponseErrorBody.code()
                    message = getMessageError(this@getResponseErrorBody.message())
                }
            }
        }

        private fun Response<ResponseBody>.getResponseSuccesBody(): RespondStandar {
            return if (this.body()!=null)
            {
                val jsonObject: JsonObject = JsonParser().parse(this.body()?.string()).asJsonObject
                val code = jsonObject.get(RESPOND_CODE).toString().toInt()
                val data = jsonObject.get(RESPOND_DATA).toString()

                RespondStandar().apply {
                    code.let { this.code = it }

                    if (data.isNotEmpty()){
                        this.data = data
                    }
                }
            }
            else
            {
                RespondStandar().apply {
                    code =  this@getResponseSuccesBody.code()
                    message = this@getResponseSuccesBody.message()
                }
            }
        }

        private fun Response<ResponseBody>?.getErrorBody(): JSONObject? {
            var error: String? = ""
            this?.let {
                error = it.errorBody()?.string()
                error?.let { it1 -> Log.e("ErrorBody", it1) }
            }
            return JSONObject(error)
        }

        private fun getMessageError(message: String?):String{
            return if (message.isNullOrEmpty())
            {
                AppMaster.contextApp().getString(R.string.something_failed)
            }
            else
            {
                message
            }
        }


        fun basicResponse(activity: Activity, code: Int?, message: String?):Boolean{
            when (code) {
                200 -> {
                    if (!message.isNullOrEmpty()) { MessageManager.show(message, 1, activity) }
                    return true
                }
                in 400..402 -> {
                    MessageManager.show(message, 2, activity)
                    return false
                }
                403 -> {
                    return false
                }
                in 404..599 -> {
                    MessageManager.show(message, 2, activity)
                    return false
                }
                else -> {
                   MessageManager.show(message, 0, activity)
                    return  false
                }
            }
        }


    }
}