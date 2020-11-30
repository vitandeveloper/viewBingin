package com.areuin.android.api

import android.util.Log
import com.areuin.android.dagger.component.ComponentNetwork
import com.areuin.android.dagger.component.DaggerComponentNetwork
import com.areuin.android.dagger.module.NetworkModule
import com.example.marlonviana.AppMaster
import com.example.marlonviana.R
import com.example.marlonviana.model.DataMasterCharacters
import com.example.marlonviana.model.RespondCharacter
import com.example.marlonviana.util.HashManager
import com.example.marlonviana.util.KEY_API_PUBLIC
import com.google.gson.Gson
import retrofit2.awaitResponse
import javax.inject.Inject

class RequestApiMarvel {
    @Inject
    lateinit var apiUser: ApiMarvel

    private val injector: ComponentNetwork = DaggerComponentNetwork
            .builder()
            .networkModule(NetworkModule)
            .build()

    private val ctx = AppMaster.contextApp()

    init {
        injector.injectApiUser(this)
    }


    suspend fun characters(offset:Int): RespondCharacter {
        var respondCharacter = RespondCharacter()

        try
        {
            val respondAwait = apiUser.getCharacters(
                HashManager.getTimestamp(),
                KEY_API_PUBLIC,
                HashManager.makeHashHmd5(),
                offset,
               20
            ).awaitResponse()

            val respondStandar = BodyApiManager.proccesData(respondAwait)

            respondCharacter.code = respondStandar.code

            if (!respondStandar.data.isNullOrEmpty())
            {
                respondCharacter.data = Gson().fromJson(respondStandar.data, DataMasterCharacters::class.java)
            }
        }
        catch (e:java.net.SocketTimeoutException)
        {
            respondCharacter.apply {
                message = ctx.getString(R.string.unestable_internet)
            }
        }
        catch (e:java.net.UnknownHostException)
        {
            respondCharacter.apply {
                message = ctx.getString(R.string.unestable_internet)
            }
        }
        catch (e: Exception)
        {
            Log.e("EXCEP",e.toString())
            respondCharacter.apply {
                message = ctx.getString(R.string.something_failed)
            }
        }

        return respondCharacter
    }

    suspend fun characterData(id: String):RespondCharacter{
        var respondCharacter = RespondCharacter()

        try
        {
            val respondAwait = apiUser.getCharacterData(
                id,
                HashManager.getTimestamp(),
                KEY_API_PUBLIC,
                HashManager.makeHashHmd5()
            ).awaitResponse()

            val respondStandar = BodyApiManager.proccesData(respondAwait)

            respondCharacter .code = respondStandar.code

            if (!respondStandar.data.isNullOrEmpty())
            {
                respondCharacter.data = Gson().fromJson(respondStandar.data, DataMasterCharacters::class.java)
            }

        }
        catch (e:java.net.SocketTimeoutException)
        {
            respondCharacter .apply {
                message = ctx.getString(R.string.unestable_internet)
            }
        }
        catch (e:java.net.UnknownHostException)
        {
            respondCharacter .apply {
                message = ctx.getString(R.string.unestable_internet)
            }
        }
        catch (e:Exception)
        {
            e.message?.let { Log.e("EXCEP", it) }
            respondCharacter .apply {
                message = ctx.getString(R.string.something_failed)
            }
        }

        return respondCharacter
    }


}