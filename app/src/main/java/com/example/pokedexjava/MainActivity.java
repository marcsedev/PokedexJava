package com.example.pokedexjava;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedexjava.models.Pokemon;
import com.example.pokedexjava.models.PokemonRespuesta;
import com.example.pokedexjava.pokeapi.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="POKEDEX";

    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;
    private int offset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        listaPokemonAdapter= new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        //ponemos el recycler con 3 columnas
        GridLayoutManager layoutManager= new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        //saber cuando llegamos al final del recyclerview

        //recyclerView

        retrofit= new Retrofit.Builder() //crear instancia
                .baseUrl("https://pokeapi.co/api/v2/") //url base donde se obtienen os datos
                //https://rickandmortyapi.com/api/
                .addConverterFactory(GsonConverterFactory.create()) // devuelve la info en objetos
                .build();
        offset = 0;
        obtenerDatos(offset);

    }

    private void obtenerDatos(int offset) {
        //usamos la interfaz
        PokeapiService service = retrofit.create(PokeapiService.class);
        int gen1=300;
        Call<PokemonRespuesta> pokemonRespuestaCall= service.obtenerListaPokemon(gen1,offset);

        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                if (response.isSuccessful()){
                    PokemonRespuesta pokemonRespuesta =response.body();
                    //comprobar la consulta
                    //guardamos en array list
                    ArrayList<Pokemon> listaPokemon= pokemonRespuesta.getResults();

                    //mandamos el arraylist de pokemon
                     listaPokemonAdapter.adicionarListaPokemon(listaPokemon);



              /*              //recorremos array list y mostramos la consulta por consola
                            for (int i =0; i<listaPokemon.size(); i++){
                                //recorremos cada p es un pokemon
                                Pokemon p=listaPokemon.get(i);
                                // se muestra por consola
                                Log.i(TAG," Pokemon: " + p.getName());

                            }*/

                }else{

                    Log.e(TAG," onResponse: " + response.errorBody());

                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                Log.e(TAG," onFailure: " + t.getMessage());

            }
        });
    }
}