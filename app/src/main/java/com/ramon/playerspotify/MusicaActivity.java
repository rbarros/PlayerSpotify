package com.ramon.playerspotify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * ## Visualização de músicas de um álbum
 *     Mostrar uma listagem de músicas
 * https://beta.developer.spotify.com/documentation/web-api/reference/albums/get-albums-tracks/
 */
public class MusicaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musica);
    }
}
