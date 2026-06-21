package com.example.rickandmortyapp.data.services

import com.example.rickandmortyapp.data.data_classes.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterService {
    @GET
    suspend fun getAllCharacters(@Url url: String) : Response<CharacterList>
}



