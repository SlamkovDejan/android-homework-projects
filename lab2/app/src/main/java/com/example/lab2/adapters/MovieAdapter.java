package com.example.lab2.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab2.MovieListFragment;
import com.example.lab2.R;
import com.example.lab2.data.FakeApi;
import com.example.lab2.data.model.Movie;
import com.example.lab2.utils.Constants;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final FakeApi api;
    private final MovieListFragment.Navigator navigator;

    public MovieAdapter(MovieListFragment.Navigator navigator) {
        this.api = FakeApi.getInstance();
        this.navigator = navigator;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie, null);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.setData(this.api.getMovie(position));
    }

    @Override
    public int getItemCount() {
        return this.api.count();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        public MovieViewHolder(View itemView) {
            super(itemView);
        }

        public void setData(Movie movie){
            ((TextView) super.itemView.findViewById(R.id.txt_movie_id)).setText(String.valueOf(movie.getId()));
            ((TextView) super.itemView.findViewById(R.id.txt_movie_title)).setText(movie.getTitle());
            ((TextView) super.itemView.findViewById(R.id.txt_movie_director)).setText(movie.getDirectorName());
            super.itemView.setOnClickListener((row) -> {
                Bundle args = new Bundle();
                args.putLong(Constants.KEY_MOVIE_ID, movie.getId());
                navigator.navigate(R.id.action_list_movies_to_movie_details, args);
            });
        }

    }

}
