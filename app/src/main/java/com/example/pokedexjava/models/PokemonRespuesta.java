package com.example.pokedexjava.models;

import java.util.ArrayList;

public class PokemonRespuesta {
    //atributos que nos interesan del GSON
    //array del resultado

    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
