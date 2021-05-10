package com.zolax.chuckjokes.api

import com.zolax.chuckjokes.models.Root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChuckApiInterface {
    @GET("jokes/random/{count}")
    fun getJokes( @Path("count") count:Int) : Call<Root>

}