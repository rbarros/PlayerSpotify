package com.ramon.playerspotify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * ## Visualização de artista
 *    Mostrar dados de um artista (https://beta.developer.spotify.com/documentation/web-api/reference/artists/get-artist/)
 *      Dados mínimos: Nome e fotos (todas que tiver disponível - listagem) do artista, popularidade.
 */
public class ArtistaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artista);
    }
}
