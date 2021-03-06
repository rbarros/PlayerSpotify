package com.ramon.playerspotify.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ramon.playerspotify.PlayerActivity;
import com.ramon.playerspotify.R;
import com.ramon.playerspotify.adapter.ListaMusicasAdapter;
import com.ramon.playerspotify.model.MusicaModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicasFragment extends Fragment implements ListaMusicasAdapter.ListaMusicaDelegate {

    private List<MusicaModel> dataSource;
    private List<MusicaModel> adapterDataSource;
    private ListaMusicasAdapter adapter;
    private RecyclerView listaMusicaRecycleView;
    private AlertDialog alerta;

    public MusicasFragment() {
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
                new MusicaModel(
                        "5BODRfhgnHa4I5rDrkxs3x",
                        "Closure",
                        238160,
                        "musica_0.jpeg",
                        Arrays.asList("Asking Alexandria"),
                        "musica_0.mp3"
                )
        );
        dataSource.add(
                new MusicaModel(
                        "0vDBNWI0uXf7dNkiiX2P5z",
                        "Let Me Live My Life",
                        191453,
                        "musica_1.jpeg",
                        Arrays.asList("Saint Asonia"),
                        "musica_1.mp3"
                )
        );
        dataSource.add(
                new MusicaModel(
                        "0qp72lMqrpmpJkp4RYonO7",
                        "Rebirthing",
                        233293,
                        "musica_2.jpeg",
                        Arrays.asList("Skillet"),
                        "musica_2.mp3"
                )
        );
        dataSource.add(
                new MusicaModel(
                        "43IivQEjE7rgnUk4YL3LpI",
                        "Hollow",
                        256658,
                        "musica_3.jpeg",
                        Arrays.asList("Heart Of A Coward"),
                        "musica_3.mp3"
                )
        );
        dataSource.add(
                new MusicaModel(
                        "2wcOAWdnv22pYwRHscYfz2",
                        "Innocence",
                        195880,
                        "musica_4.jpeg",
                        Arrays.asList("Halestorm"),
                        "musica_4.mp3"
                )
        );

        adapterDataSource = new ArrayList<>();
        adapterDataSource.addAll(dataSource);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_musicas, container, false);
        listaMusicaRecycleView = view.findViewById(R.id.lista_musicas_recycler_view);

        adapter = new ListaMusicasAdapter(adapterDataSource, this);

        listaMusicaRecycleView.setAdapter(adapter);
        listaMusicaRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
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

        for (MusicaModel musica : dataSource ) {
            if (musica.getNome().toLowerCase().contains(string.toLowerCase())) {
                tempList.add(musica);
            }
        }

        adapterDataSource.clear();
        adapterDataSource.addAll(tempList);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMusicaPlaylist(View view, MusicaModel musica) {
        //Lista de itens
        final ArrayList<String> playlists = new ArrayList<String>();
        playlists.add("Playlist 1");

        //adapter utilizando um layout customizado (TextView)
        ArrayAdapter adapter = new ArrayAdapter(this.getActivity(), R.layout.item_alerta, playlists);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Playlist:");
        //define o diálogo como uma lista, passa o adapter.
        builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(MusicasFragment.this.getContext(), "Adicionado em " + playlists.get(arg1).toString(), Toast.LENGTH_SHORT).show();
                alerta.dismiss();
            }
        });

        alerta = builder.create();
        alerta.show();
    }

    @Override
    public void onMusicaSelecionada(View view, MusicaModel musica) {
        String nome = musica.getNome().toString();

        Intent intent = new Intent(getContext(), PlayerActivity.class);
        intent.putExtra(PlayerActivity.PARAM_NOME, nome);
        intent.putExtra(PlayerActivity.MUSICA, musica);

        startActivity(intent);
    }

    @Override
    public void onMusicaPlay(View view, MusicaModel musica) {

    }
}
