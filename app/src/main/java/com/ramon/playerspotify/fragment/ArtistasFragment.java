package com.ramon.playerspotify.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramon.playerspotify.ArtistaActivity;
import com.ramon.playerspotify.PlayerActivity;
import com.ramon.playerspotify.R;
import com.ramon.playerspotify.adapter.ListaArtistasAdapter;
import com.ramon.playerspotify.model.ArtistaModel;
import com.ramon.playerspotify.model.MusicaModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistasFragment extends Fragment implements ListaArtistasAdapter.ListaArtistasDelegate {

    private List<ArtistaModel> dataSource;
    private List<ArtistaModel> adapterDataSource;
    private ListaArtistasAdapter adapter;
    private RecyclerView listaArtistaRecycleView;
    public TextView mTextView;

    public ArtistasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        geraDadosTeste();
    }

    private void geraDadosTeste()
    {
        dataSource = new ArrayList<>();
        dataSource.add(
                new ArtistaModel(
                        "1",
                        "Artista 1",
                        "artista_0.jpeg"
                )
        );
        dataSource.add(
                new ArtistaModel(
                        "2",
                        "Artista 2",
                        "artista_1.jpeg"
                )
        );
        dataSource.add(
                new ArtistaModel(
                        "3",
                        "Artista 3",
                        "artista_2.jpeg"
                )
        );
        dataSource.add(
                new ArtistaModel(
                        "4",
                        "Artista 4",
                        "artista_3.jpeg"
                )
        );
        dataSource.add(
                new ArtistaModel(
                        "5",
                        "Artista 5",
                        "artista_4.jpeg"
                )
        );

        adapterDataSource = new ArrayList<>();
        adapterDataSource.addAll(dataSource);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artistas, container, false);
        listaArtistaRecycleView = view.findViewById(R.id.lista_artistas_recycler_view);

        adapter = new ListaArtistasAdapter(adapterDataSource, this);

        listaArtistaRecycleView.setAdapter(adapter);
        listaArtistaRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    public void refreshString(String string) {
        List<ArtistaModel> tempList = new ArrayList<>();

        for (ArtistaModel artista : dataSource ) {
            if (artista.getNome().toLowerCase().contains(string.toLowerCase())) {
                tempList.add(artista);
            }
        }

        adapterDataSource.clear();
        adapterDataSource.addAll(tempList);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onArtistaSelecionado(ArtistaModel artista) {
        String nome = artista.getNome().toString();

        Intent intent = new Intent(getContext(), ArtistaActivity.class);
        intent.putExtra(PlayerActivity.PARAM_NOME, nome);

        startActivity(intent);
    }
}
