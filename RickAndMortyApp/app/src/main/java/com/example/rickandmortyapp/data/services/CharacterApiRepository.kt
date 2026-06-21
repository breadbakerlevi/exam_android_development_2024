package com.example.rickandmortyapp.data.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.rickandmortyapp.data.data_classes.CharacterList
import com.example.rickandmortyapp.data.data_classes.Info

object CharacterApiRepository {
    private val _okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()

    private val _retrofit = Retrofit.Builder()
        .client(_okHttpClient)
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build()

    private val _characterService = _retrofit.create(CharacterService::class.java)

    suspend fun getAllCharacters(s: String): CharacterList {
        try {
            val response = if (s.isNotBlank()) {
                _characterService.getAllCharacters(s)
            } else {
                _characterService.getAllCharacters("character")
            }

            if (response.isSuccessful) {
                val body = response.body()

                val info = body?.info ?: Info(count = 0, pages = 0, next =null, prev = null)
                val results = body?.results ?: emptyList()

                return CharacterList(info = info, results = results)
            } else {
                return CharacterList(info = Info(count = 0, pages = 0, next = null, prev = null), results = emptyList())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return CharacterList(info = Info(count = 0, pages = 0, next = null, prev = null), results = emptyList())
        }
    }
}

