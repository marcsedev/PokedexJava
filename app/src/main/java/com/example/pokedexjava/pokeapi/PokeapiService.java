package com.example.pokedexjava.pokeapi;
//metodos de interacción con la API

//acceder a lñistado

import com.example.pokedexjava.models.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeapiService {
    //ponemos get porqué es una obtención de datos
    // y la parte de la url que falta para acceder a los datos
    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit,
                                               @Query("offset") int offset);
}
