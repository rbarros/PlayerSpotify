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
import com.ramon.playerspotify.model.MusicaModel;

import java.util.List;

/**
 * Created by desenv-03 on 27/05/18.
 */

public class ListaPlaylistAdapter extends RecyclerView.Adapter<ListaPlaylistAdapter.PlaylistViewHolder> {

    private List<MusicaModel> dataSource;
    private ListaPlaylistAdapter.ListaPlaylistDelegate delegate;

    public ListaPlaylistAdapter(List<MusicaModel> dataSource, ListaPlaylistAdapter.ListaPlaylistDelegate delegate) {
        this.dataSource = dataSource;
        this.delegate = delegate;
    }

    @Override
    public ListaPlaylistAdapter.PlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.linha_lista_playlist, parent, false);
        return new ListaPlaylistAdapter.PlaylistViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(ListaPlaylistAdapter.PlaylistViewHolder holder, int position) {
        MusicaModel musica = dataSource.get(position);
        holder.nomeAlbumTextView.setText(musica.getNome());
        float duracao = musica.getDuracao();
        //holder.dataAlbumTextView.setText(((String) duracao));

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

    public static class PlaylistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private ImageView capaAlbumImageView;
        private TextView nomeAlbumTextView;
        private TextView dataAlbumTextView;
        private ListaPlaylistAdapter adapter;

        public PlaylistViewHolder(View itemView, ListaPlaylistAdapter adapter) {
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
            MusicaModel musica = adapter.dataSource.get(getAdapterPosition());

            if (adapter.delegate != null) {
                adapter.delegate.onPlaylistSelecionado(musica);
            }
        }
    }

    public static interface ListaPlaylistDelegate {

        public void onPlaylistSelecionado(MusicaModel musica);

    }
}
