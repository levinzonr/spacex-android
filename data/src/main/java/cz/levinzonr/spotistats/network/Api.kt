package cz.levinzonr.spotistats.network

import cz.levinzonr.spotistats.domain.models.Post
import cz.levinzonr.spotistats.network.models.LaunchResponse
import cz.levinzonr.spotistats.network.models.rocket.RocketResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("launches/upcoming")
    suspend fun getUpcomingLaunches() : List<LaunchResponse>

    @GET("launches/past")
    suspend fun getPastLaunches() : List<LaunchResponse>

    @GET("launches/{id}")
    suspend fun getLaunchById(@Path("id") id: String) : LaunchResponse


    @GET("rockets")
    suspend fun getRockets() : List<RocketResponse>

    @GET("rockets/{id}")
    suspend fun getRocketById(@Path("id") id: String) : RocketResponse
}
