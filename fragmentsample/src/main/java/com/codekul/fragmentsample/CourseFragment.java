package com.codekul.fragmentsample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends Fragment {


    public static CourseFragment fragment() {
        return new CourseFragment();
    }

    public CourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =
                inflater.inflate(R.layout.fragment_course, container, false);

        ((ListView)rootView.findViewById(R.id.listCourses)).setOnItemClickListener(this::onItemClick);
        return rootView;
    }

    private void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        String course = (String) adapterView.getAdapter().getItem(pos); // error

        ((MainActivity)getActivity()).loadFragment(DetailsFragment.fragment(course));
    }
}
