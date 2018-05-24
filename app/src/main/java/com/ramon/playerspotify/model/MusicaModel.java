package com.ramon.playerspotify.model;

/**
 * Created by desenv-03 on 10/05/18.
 */

public class MusicaModel {

    private String nome;
    private float duracao;
    private String foto;
    private String artista;

    public MusicaModel(String nome, float duracao, String foto, String artista) {
        this.nome = nome;
        this.duracao = duracao;
        this.foto = foto;
        this.artista = artista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getDuracao() {
        return duracao;
    }

    public void setDuracao(float duracao) {
        this.duracao = duracao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }
}
