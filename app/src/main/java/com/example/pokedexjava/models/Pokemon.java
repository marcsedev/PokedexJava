package com.example.pokedexjava.models;

public class Pokemon {

    private int number;
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //public Int getNumber() {
    public String getNumber() {
        //la api no devuelve el numero por lo que tenemos que extraerlo del aurl
        // dividimos la url
        String[]urlPartes=url.split("/");
        //accedemos a la última posición que es el número
        //return Integer.parseInt(urlPartes[urlPartes.length -1]);
        return urlPartes[urlPartes.length -1];
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
