package com.codekul.fragmentsample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {


    public static final String KEY_COURSE = "course";

    public static DetailsFragment fragment(String course) {

        DetailsFragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_COURSE, course);
        fragment.setArguments(bundle);
        return fragment;
    }

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        return rootView;
    }

}
