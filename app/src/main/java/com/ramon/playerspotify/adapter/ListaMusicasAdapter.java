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
 * Created by desenv-03 on 30/05/18.
 */

public class ListaMusicasAdapter extends RecyclerView.Adapter<ListaMusicasAdapter.MusicasViewHolder> {

    private List<MusicaModel> dataSource;
    private ListaMusicasAdapter.ListaMusicaDelegate delegate;

    public ListaMusicasAdapter(List<MusicaModel> dataSource, ListaMusicasAdapter.ListaMusicaDelegate delegate) {
        this.dataSource = dataSource;
        this.delegate = delegate;
    }

    @Override
    public ListaMusicasAdapter.MusicasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.linha_lista_musica, parent, false);
        return new ListaMusicasAdapter.MusicasViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(ListaMusicasAdapter.MusicasViewHolder holder, int position) {
        MusicaModel musica = dataSource.get(position);
        holder.nomeMusicaTextView.setText(musica.getNome());
        List<String> artistas = musica.getArtista();
        holder.nomeArtistaTextView.setText(artistas.toString());

        Context context = holder.nomeMusicaTextView.getContext();

        String resourceName = "musica_" + position;
        //String resourceName = musica.getImagem();
        int resourceId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
        Drawable imagem = context.getResources().getDrawable(resourceId, null);

        holder.capaAlbumImageView.setImageDrawable(imagem);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public static class MusicasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView capaAlbumImageView;
        private TextView nomeMusicaTextView;
        private TextView nomeArtistaTextView;
        private ListaMusicasAdapter adapter;

        public MusicasViewHolder(View itemView, ListaMusicasAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            capaAlbumImageView = itemView.findViewById(R.id.capa_album_image_view);
            nomeMusicaTextView = itemView.findViewById(R.id.nome_musica_text_view);
            nomeArtistaTextView = itemView.findViewById(R.id.nome_artista_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Aqui recupera o item que foi clicado
            // getAdpaterPosition() Retornar a linha referente ao ViewHolder
            MusicaModel musica = adapter.dataSource.get(getAdapterPosition());

            if (adapter.delegate != null) {
                adapter.delegate.onMusicaSelecionada(musica);
            }
        }
    }

    public static interface ListaMusicaDelegate {

        public void onMusicaSelecionada(MusicaModel musica);

    }
}