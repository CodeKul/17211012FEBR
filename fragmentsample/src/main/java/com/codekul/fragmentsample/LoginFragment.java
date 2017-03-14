package com.codekul.fragmentsample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public static LoginFragment fragment() {

        LoginFragment fragment = new LoginFragment();

        return fragment;
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        rootView.findViewById(R.id.btnLogin).setOnClickListener(v -> login(rootView));
        return rootView;
    }

    private void login(View rootView) {
        if (areEqual(rootView))
            ((MainActivity) getActivity()).loadFragment(CourseFragment.fragment());
    }

    private String userName(View rootView) {
        return ((EditText) rootView.findViewById(R.id.edtUserName))
                .getText()
                .toString();
    }

    private String password(View rootView) {
        return ((EditText) rootView.findViewById(R.id.edtPassword))
                .getText()
                .toString();
    }

    private boolean areEqual(View rootView) {
        return userName(rootView).equals("android")
                && password(rootView).equals("android");
    }
}
