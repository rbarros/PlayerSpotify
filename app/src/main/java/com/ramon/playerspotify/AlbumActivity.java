package com.ramon.playerspotify;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ramon.playerspotify.adapter.ListaAlbunsAdapter;
import com.ramon.playerspotify.model.AlbumModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ## Visualização de álbuns de artista
 *    Mostrar uma listagem de álbuns (nome e imagem no mínimo)
 */
public class AlbumActivity extends AppCompatActivity implements ListaAlbunsAdapter.ListaAlbunsDelegate, SearchView.OnQueryTextListener {

    private List<AlbumModel> dataSource;
    private List<AlbumModel> adapterDataSource;
    private ListaAlbunsAdapter adapter;
    private RecyclerView listaAlbunsRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        geraDadosTeste();

        listaAlbunsRecycleView = findViewById(R.id.lista_album_recycler_view);

        adapter = new ListaAlbunsAdapter(adapterDataSource, this);

        listaAlbunsRecycleView.setAdapter(adapter);
        listaAlbunsRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void geraDadosTeste()
    {
        dataSource = new ArrayList<>();
        dataSource.add(new AlbumModel("Black Label Society", "album_0", new Date(Long.parseLong("1343805819061"))));
        dataSource.add(new AlbumModel("Rampage - Destruição Total", "album_1", new Date(Long.parseLong("1343805819061"))));
        dataSource.add(new AlbumModel("Nada a Perder - Contra Tudo. Por Todos", "album_2", new Date(Long.parseLong("1343805819061"))));
        dataSource.add(new AlbumModel("Um Lugar Silencioso", "album_3",new Date(Long.parseLong("1343805819061"))));
        dataSource.add(new AlbumModel("Exorcismos e Demônios", "album_4", new Date(Long.parseLong("1343805819061"))));

        adapterDataSource = new ArrayList<>();
        adapterDataSource.addAll(dataSource);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // User pressed the search button
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<AlbumModel> tempList = new ArrayList<>();

        for (AlbumModel album : dataSource ) {
            if (album.getNome().toLowerCase().contains(newText.toLowerCase())) {
                tempList.add(album);
            }
        }

        adapterDataSource.clear();
        adapterDataSource.addAll(tempList);

        adapter.notifyDataSetChanged();



        return true;
    }

    @Override
    public void onAlbumSelecionado(AlbumModel album) {
        Toast.makeText(this, album.getNome(), Toast.LENGTH_LONG).show();
        /*String nome = album.getNome().toString();

        Intent intent = new Intent(this, AlbumActivity.class);
        intent.putExtra(AlbumActivity.PARAM_NOME, nome);

        startActivity(intent);*/
    }
}
