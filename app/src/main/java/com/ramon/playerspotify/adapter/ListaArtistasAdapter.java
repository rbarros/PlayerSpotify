package com.ramon.playerspotify.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramon.playerspotify.R;
import com.ramon.playerspotify.model.ArtistaModel;

import java.util.List;

/**
 * Created by desenv-03 on 31/05/18.
 */

public class ListaArtistasAdapter extends RecyclerView.Adapter<ListaArtistasAdapter.ArtistaViewHolder> {

    private List<ArtistaModel> dataSource;
    private ListaArtistasAdapter.ListaArtistasDelegate delegate;

    public ListaArtistasAdapter(List<ArtistaModel> dataSource, ListaArtistasAdapter.ListaArtistasDelegate delegate) {
        this.dataSource = dataSource;
        this.delegate = delegate;
    }

    @Override
    public ListaArtistasAdapter.ArtistaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.linha_lista_artistas, parent, false);
        return new ListaArtistasAdapter.ArtistaViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(ListaArtistasAdapter.ArtistaViewHolder holder, int position) {
        ArtistaModel artista = dataSource.get(position);
        holder.nomeArtistaTextView.setText(artista.getNome());

        Context context = holder.nomeArtistaTextView.getContext();

        String resourceName = "album_" + position;
        int resourceId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
        Drawable imagem = context.getResources().getDrawable(resourceId, null);

        holder.imagemArtistaImageView.setImageDrawable(imagem);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public static class ArtistaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private ImageView imagemArtistaImageView;
        private TextView nomeArtistaTextView;
        private ListaArtistasAdapter adapter;

        public ArtistaViewHolder(View itemView, ListaArtistasAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            imagemArtistaImageView = itemView.findViewById(R.id.imagem_artista_image_view);
            nomeArtistaTextView = itemView.findViewById(R.id.nome_artista_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Aqui recupera o item que foi clicado
            // getAdpaterPosition() Retornar a linha referente ao ViewHolder
            ArtistaModel artista = adapter.dataSource.get(getAdapterPosition());

            if (adapter.delegate != null) {
                adapter.delegate.onArtistaSelecionado(artista);
            }
        }
    }

    public static interface ListaArtistasDelegate {

        public void onArtistaSelecionado(ArtistaModel artista);

    }
}
