package edu.uw.fragmentdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private static final String IMDB_ARG = "imdbId";
    private static final String TITLE_ARG = "title";

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Movie movie) {

        Bundle args = new Bundle();
        args.putString(IMDB_ARG, movie.imdbId);
        args.putString(TITLE_ARG, movie.title);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = this.getArguments();

        if(bundle != null) {
            TextView txtTitle = (TextView) rootView.findViewById(R.id.txtMovieTitle);
            txtTitle.setText(bundle.getString(TITLE_ARG));

            TextView txtImdb = (TextView) rootView.findViewById(R.id.txtMovieIMDB);
            txtImdb.append(bundle.getString(IMDB_ARG));
            Linkify.addLinks(txtImdb, Linkify.WEB_URLS);
        }

        return rootView;
    }

}
