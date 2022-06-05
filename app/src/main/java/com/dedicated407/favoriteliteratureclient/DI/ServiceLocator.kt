package com.dedicated407.favoriteliteratureclient.DI

import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.AuthInterceptor
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.IWebService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceLocator {
    private var apiFavLit: IWebService? = null
    var jwtToken: String? = null

    companion object {
        private var instance: ServiceLocator? = null

        fun getInstance(): ServiceLocator {
            if (instance == null) {
                instance = ServiceLocator()
            }
            return instance!!
        }
    }

    private fun okHttpClient()
            = OkHttpClient.Builder().addInterceptor(AuthInterceptor(jwtToken)).build()

    fun getApiFavLit(): IWebService {
        if (apiFavLit == null || jwtToken != null) {
            try {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://1c82-109-252-182-143.ngrok.io")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient())
                    .build()

                apiFavLit = retrofit.create(IWebService::class.java)
            } catch (e: Exception) {
                print(e.stackTrace)
            }
        }
        return apiFavLit!!
    }
}