package com.example.lab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab2.adapters.MovieAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MovieListFragment extends Fragment {

    public MovieListFragment(){}

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView moviesView = view.findViewById(R.id.rcv_movies);
        moviesView.setAdapter(new MovieAdapter(this::navigate));

        FloatingActionButton fab = view.findViewById(R.id.fbtn_add_movie);
        fab.setOnClickListener((btn) -> navigate(R.id.action_list_movies_to_add_new_movie, null));

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.movie_list_fragment_title);
    }

    public void navigate(int actionId, Bundle args){
        NavHostFragment.findNavController(this)
                .navigate(actionId, args);
    }

    @FunctionalInterface
    public interface Navigator {
        void navigate(int actionId, Bundle args);
    }

}