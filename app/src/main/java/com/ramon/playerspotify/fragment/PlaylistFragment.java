package com.ramon.playerspotify.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ramon.playerspotify.PlayerActivity;
import com.ramon.playerspotify.R;
import com.ramon.playerspotify.adapter.ListaPlaylistAdapter;
import com.ramon.playerspotify.model.MusicaModel;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends Fragment implements ListaPlaylistAdapter.ListaPlaylistDelegate {

    private List<MusicaModel> dataSource;
    private List<MusicaModel> adapterDataSource;
    private ListaPlaylistAdapter adapter;
    private RecyclerView listaPlaylistRecycleView;
    public TextView mTextView;

    public PlaylistFragment() {
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
        dataSource.add(new MusicaModel("Black Label Society", (float) 1.50, "album_0", "Artista 1"));
        dataSource.add(new MusicaModel("Black Label Society", (float) 1.51, "album_1", "Artista 2"));
        dataSource.add(new MusicaModel("Black Label Society", (float) 1.52, "album_2", "Artista 3"));
        dataSource.add(new MusicaModel("Black Label Society", (float) 1.53, "album_3", "Artista 4"));
        dataSource.add(new MusicaModel("Black Label Society", (float) 1.54, "album_4", "Artista 5"));

        adapterDataSource = new ArrayList<>();
        adapterDataSource.addAll(dataSource);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        listaPlaylistRecycleView = view.findViewById(R.id.lista_playlist_recycler_view);

        adapter = new ListaPlaylistAdapter(adapterDataSource, this);

        listaPlaylistRecycleView.setAdapter(adapter);
        listaPlaylistRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void refreshString(String string) {
        List<MusicaModel> tempList = new ArrayList<>();
        mTextView = getActivity().findViewById(R.id.text_view_fragment_playlist);
        mTextView.setText(string);

        for (MusicaModel musica : dataSource ) {
            if (musica.getNome().toLowerCase().contains(string)) {
                tempList.add(musica);
            }
        }

        adapterDataSource.clear();
        adapterDataSource.addAll(tempList);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPlaylistSelecionado(MusicaModel musica) {
        //Toast.makeText(this, musica.getNome(), Toast.LENGTH_LONG).show();
        String nome = musica.getNome().toString();

        Intent intent = new Intent(getContext(), PlayerActivity.class);
        intent.putExtra(PlayerActivity.PARAM_NOME, nome);

        startActivity(intent);
    }
}
