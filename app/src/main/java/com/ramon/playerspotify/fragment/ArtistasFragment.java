package com.ramon.playerspotify.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramon.playerspotify.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistasFragment extends Fragment {

    public TextView mTextView;

    public ArtistasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artistas, container, false);
    }

    public void refreshString(String string) {
        mTextView = getActivity().findViewById(R.id.text_view_fragment_artistas);
        mTextView.setText(string);
    }
}
