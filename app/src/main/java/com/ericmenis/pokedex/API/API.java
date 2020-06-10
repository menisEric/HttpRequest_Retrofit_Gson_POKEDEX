package com.ericmenis.pokedex.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    public static String BASE_URL = "https://pokeapi.co/api/v2/";
    public static String URL_IMAGE = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    public static String EXTENSION_ICONS = ".png";
    public static String TAG = "POKEDEX";

    private static Retrofit retrofit = null;

    public static Retrofit getApi(){
        if(retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
