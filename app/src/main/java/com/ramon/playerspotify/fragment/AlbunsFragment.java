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
public class AlbunsFragment extends Fragment {

    public TextView mTextView;

    public AlbunsFragment() {
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
        return inflater.inflate(R.layout.fragment_albuns, container, false);
    }

    public void refreshString(String string) {
        mTextView = getActivity().findViewById(R.id.text_view_fragment_albuns);
        mTextView.setText(string);
    }
}
