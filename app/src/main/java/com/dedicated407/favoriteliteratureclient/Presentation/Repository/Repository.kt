package com.dedicated407.favoriteliteratureclient.Presentation.Repository

import com.dedicated407.favoriteliteratureclient.DI.ServiceLocator
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.IWebService
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Requests.JwtTokenRequestModel
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.JwtTokenResponseModel
import java.lang.Exception

class Repository {
    private var mApiFavLit: IWebService = ServiceLocator.getInstance().getApiFavLit()

    suspend fun authentication(email: String, password: String) =
        mApiFavLit.authentication(JwtTokenRequestModel(email, password)).accessToken
}