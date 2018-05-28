package com.ramon.playerspotify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Play da música (integração com o player ainda está sendo avaliada)
 * Tela para realizar o play mostrando os dados da música, com botões para play, pause, próxima e anterior (não precisa ter funcionalidade)
 * Dados mínimos: Nome e tempo de duração da música, capa do álbum, nome do artista.
 */
public class PlaylistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
    }
}
