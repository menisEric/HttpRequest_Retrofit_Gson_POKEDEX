package com.ericmenis.pokedex.API.APIService;

import com.ericmenis.pokedex.Models.PokemonRespuestas;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("pokemon")
    Call<PokemonRespuestas> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);
}
