package com.codekul.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {

    public ImageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        int imgId = R.mipmap.ic_launcher;
        if(bundle != null) imgId = bundle.getInt("imgId", R.mipmap.ic_launcher);

        final View rootView = inflater.inflate(R.layout.fragment_image, container, false);
        ((ImageView)rootView.findViewById(R.id.imageView)).setImageResource(imgId);
        return rootView;
    }

}
