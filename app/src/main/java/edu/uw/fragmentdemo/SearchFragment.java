package edu.uw.fragmentdemo;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnSearchListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    private OnSearchListener callback;

    public interface OnSearchListener {
        public void onSearchSubmitted(String searchTerm);
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);


        final EditText text = (EditText) rootView.findViewById(R.id.txtSearch);
        // Button searchButton = (Button) getView().findViewById(R.id.btnSearch);
        Button searchButton = (Button) rootView.findViewById(R.id.btnSearch);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchTerm = text.getText().toString();
                callback.onSearchSubmitted(searchTerm);
            }
        });
        return inflater.inflate(R.layout.fragment_search, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (OnSearchListener) context;
        } catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnSearchListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}
