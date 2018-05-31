package com.ramon.playerspotify.model;

import java.util.List;

/**
 * Created by desenv-03 on 10/05/18.
 */

public class MusicaModel {

    /**
     * Id de identificação da música
     * @id
     */
    private String id;

    /**
     * Nome da música
     * @name
     */
    private String nome;

    /**
     * Duração da música
     * @duration_ms
     * milisegundos
     */
    private int duracao;

    /**
     * Imagem do album
     * album {
     *     images [
     *          height
     *          url
     *          width
     *     ]
     * }
     */
    private String imagem;

    /**
     * artists [
     *  {
     *      name
     *  }
     * ]
     */
    private List<String> artista;

    /**
     * Nome do arquivo associado a música
     * @preview_url
     */
    private String arquivo;

    public MusicaModel(String id, String nome, int duracao, String imagem, List<String> artista, String arquivo) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.imagem = imagem;
        this.artista = artista;
        this.arquivo = arquivo;
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

    public float getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<String> getArtista() {
        return artista;
    }

    public void setArtista(List<String> artista) {
        this.artista = artista;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
}
