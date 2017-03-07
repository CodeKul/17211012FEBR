package com.codekul.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    public static final String TAG = MenuFragment.class.getCanonicalName();

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        final ListView listView = ((ListView) rootView.findViewById(R.id.listView));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "CLicked Item "+listView.getAdapter().getItem(position));
                loadFragment(""+listView.getAdapter().getItem(position));
            }
        });

        return rootView;
    }

    private void loadFragment(String item) {

        MainActivity hostActivity = (MainActivity) getActivity();

        if(item.equals("Boy")) hostActivity.loadFragment(R.drawable.ic_boy);
        else if(item.equals("Bus")) hostActivity.loadFragment(R.drawable.ic_buses);
        else if(item.equals("Chopper")) hostActivity.loadFragment(R.drawable.ic_chopper);
        else if(item.equals("Pill")) hostActivity.loadFragment(R.drawable.ic_pill);
        else hostActivity.loadFragment(R.drawable.ic_doctor);
    }

}
