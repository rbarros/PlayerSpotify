package com.ramon.playerspotify.model;

import java.util.List;

/**
 * Created by desenv-03 on 30/05/18.
 */

public class PlaylistModel {

    /**
     * Id de identificação da playlist
     */
    private String id;

    /**
     * Nome da playlist
     */
    private String nome;

    /**
     * Imagem da playlist
     * geralmente um mosaico das imagens
     * dos albuns
     */
    private String imagem;

    private List<MusicaModel> musicas;

    public PlaylistModel(String id, String nome, String imagem, List<MusicaModel> musicas) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
        this.musicas = musicas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<MusicaModel> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<MusicaModel> musicas) {
        this.musicas = musicas;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
