package info.anisuzzaman.mvvmarchitecture.repositories.api

import info.anisuzzaman.mvvmarchitecture.repositories.database.entity.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by anisuzzaman on 24/9/20.
 */

interface UserWebservice {
    @GET("/users/{user}")
    fun getUser(@Path("user") userId: String): Call<User>
}