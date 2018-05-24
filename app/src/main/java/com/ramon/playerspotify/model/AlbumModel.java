package com.ramon.playerspotify.model;

import java.util.Date;

/**
 * Created by desenv-03 on 03/05/18.
 */

public class AlbumModel {

    private String nome;
    private String foto;
    private Date ano;

    public AlbumModel(String nome, String foto, Date ano) {
        this.nome = nome;
        this.foto = foto;
        this.ano = ano;
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

    public Date getAno() {
        return ano;
    }

    public void setAno(Date ano) {
        this.ano = ano;
    }
}
