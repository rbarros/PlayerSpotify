package com.ramon.playerspotify.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramon.playerspotify.R;
import com.ramon.playerspotify.model.AlbumModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by desenv-03 on 10/05/18.
 */

public class ListaAlbunsAdapter extends RecyclerView.Adapter<ListaAlbunsAdapter.AlbumViewHolder> {

    private List<AlbumModel> dataSource;
    private ListaAlbunsDelegate delegate;

    public ListaAlbunsAdapter(List<AlbumModel> dataSource, ListaAlbunsDelegate delegate) {
        this.dataSource = dataSource;
        this.delegate = delegate;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.linha_lista_algum, parent, false);
        return new AlbumViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        AlbumModel album = dataSource.get(position);
        holder.nomeAlbumTextView.setText(album.getNome());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String ano = formatter.format(album.getAno());
        holder.dataAlbumTextView.setText(ano);

        Context context = holder.nomeAlbumTextView.getContext();

        String resourceName = "album_" + position;
        int resourceId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
        Drawable imagem = context.getResources().getDrawable(resourceId, null);

        holder.capaAlbumImageView.setImageDrawable(imagem);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private ImageView capaAlbumImageView;
        private TextView nomeAlbumTextView;
        private TextView dataAlbumTextView;
        private ListaAlbunsAdapter adapter;

        public AlbumViewHolder(View itemView, ListaAlbunsAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            capaAlbumImageView = itemView.findViewById(R.id.capa_album_image_view);
            nomeAlbumTextView = itemView.findViewById(R.id.nome_album_text_view);
            dataAlbumTextView = itemView.findViewById(R.id.data_album_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Aqui recupera o item que foi clicado
            // getAdpaterPosition() Retornar a linha referente ao ViewHolder
            AlbumModel filme = adapter.dataSource.get(getAdapterPosition());

            if (adapter.delegate != null) {
                adapter.delegate.onAlbumSelecionado(filme);
            }
        }
    }

    public static interface ListaAlbunsDelegate {

        public void onAlbumSelecionado(AlbumModel album);

    }
}
