package com.example.lab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.lab2.data.FakeApi;
import com.example.lab2.data.model.Movie;
import com.example.lab2.utils.Constants;

public class MovieDetailsFragment extends Fragment {

    private final FakeApi api;

    public MovieDetailsFragment(){
        api = FakeApi.getInstance();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Movie movie = api.getMovie(getArguments().getLong(Constants.KEY_MOVIE_ID));

        ((TextView) view.findViewById(R.id.txt_desc_id)).setText(String.valueOf(movie.getId()));
        ((TextView) view.findViewById(R.id.txt_desc_title)).setText(movie.getTitle());
        ((TextView) view.findViewById(R.id.txt_desc_director)).setText(movie.getDirectorName());
        ((TextView) view.findViewById(R.id.txt_desc_description)).setText(movie.getDescription());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, movie.getActorNames());
        ((ListView) view.findViewById(R.id.list_actors)).setAdapter(adapter);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.movie_details_fragment_title);
    }

}