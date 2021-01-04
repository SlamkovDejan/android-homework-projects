package com.example.lab3.ui;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import com.bumptech.glide.Glide;
import com.example.lab3.R;
import com.example.lab3.db.entity.Movie;
import com.example.lab3.viewmodel.SecondFragmentViewModel;

import java.util.List;

public class SecondFragment extends Fragment {

    private SecondFragmentViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = ViewModelProviders.of(this).get(SecondFragmentViewModel.class);

        String movieId = getArguments().getString("selectedMovie");

        this.viewModel.getMovieById(movieId).observe(getViewLifecycleOwner(), this::bindMovie);
        this.viewModel.getActorsOfMovie(movieId).observe(getViewLifecycleOwner(), this::bindActors);
        this.viewModel.getGenresOfMovie(movieId).observe(getViewLifecycleOwner(), this::bindGenres);
    }

    private void bindMovie(Movie movie){
        ((TextView) getView().findViewById(R.id.second_txt_movie_id)).setText(movie.getId());
        ((TextView) getView().findViewById(R.id.second_txt_movie_title)).setText(movie.getTitle());
        ((TextView) getView().findViewById(R.id.second_txt_movie_director)).setText(movie.getDirector());
        ((TextView) getView().findViewById(R.id.second_txt_movie_release_year)).setText(movie.getFormattedDate("dd-MM-yyyy"));
        ((TextView) getView().findViewById(R.id.second_txt_movie_plot)).setText(movie.getPlot());
        ((TextView) getView().findViewById(R.id.second_txt_movie_plot)).setMovementMethod(new ScrollingMovementMethod());
        ImageView imageView = getView().findViewById(R.id.second_img_movie_poster);
        Glide.with(this).load(movie.getPosterUrl()).into(imageView);
    }

    private void bindActors(List<String> actors){
        ListView listView = getView().findViewById(R.id.second_lv_actors);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, actors);
        listView.setAdapter(adapter);
    }

    private void bindGenres(List<String> genres){
        ListView listView = getView().findViewById(R.id.second_lv_genres);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, genres);
        listView.setAdapter(adapter);
    }

}