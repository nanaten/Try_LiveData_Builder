package com.nanaten.try_livedata_builder.api

import com.nanaten.try_livedata_builder.entity.Repos
import retrofit2.http.GET

interface GithubApi {
    @GET("repositories")
    suspend fun getRepos(): List<Repos>
}