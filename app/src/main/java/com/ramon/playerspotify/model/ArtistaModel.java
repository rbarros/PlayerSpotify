package com.ramon.playerspotify.model;

/**
 * Created by desenv-03 on 03/05/18.
 */

public class ArtistaModel {

    private String nome;
    private String foto;

    public ArtistaModel(String nome, String foto) {
        this.nome = nome;
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
