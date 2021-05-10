package com.zolax.chuckjokes.repositories

import com.zolax.chuckjokes.api.Network
import com.zolax.chuckjokes.util.Resource
import retrofit2.await
import timber.log.Timber

class ApiRepository {
    private val jokesApiService = Network.service
    private inline fun <T> safeCall(action: () -> Resource<T>): Resource<T> {
        return try {
            action()
        } catch (e: Exception) {
            Timber.d(e)
            Resource.Error(e)
        }
    }

    suspend fun getJokes(count: Int) = safeCall{
        val apiCall = jokesApiService.getJokes(count)
        Resource.Success(apiCall.await())
    }
}