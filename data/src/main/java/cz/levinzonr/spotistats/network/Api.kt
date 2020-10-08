package cz.levinzonr.spotistats.network

import cz.levinzonr.spotistats.domain.models.Post
import cz.levinzonr.spotistats.network.models.LaunchResponse
import cz.levinzonr.spotistats.network.models.crew.CrewResponse
import cz.levinzonr.spotistats.network.models.launchpad.LaunchpadResponse
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

    @GET("launchpads/{id}")
    suspend fun getLaunchpadById(@Path("id") id: String) : LaunchpadResponse

    @GET("crew/{id}")
    suspend fun getCrewMemberById(@Path("id") id: String) : CrewResponse
}
