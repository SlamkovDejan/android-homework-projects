package com.example.lab3.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.lab3.R;
import com.example.lab3.db.entity.Movie;

import java.util.List;
import java.util.function.Consumer;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;
    private final Consumer<String> showMovieDetailsListener;
    private final Consumer<String> removeMovieListener;

    public MovieAdapter(List<Movie> movies, Consumer<String> showMovieDetailsListener, Consumer<String> removeMovieListener) {
        this.movies = movies;
        this.showMovieDetailsListener = showMovieDetailsListener;
        this.removeMovieListener = removeMovieListener;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        Movie curr = this.movies.get(position);
        holder.bindData(curr, this.removeMovieListener);
        holder.setOnClickListener((view) -> this.showMovieDetailsListener.accept(curr.getId()));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void updateData(List<Movie> movies){
        this.movies = movies;
        this.notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindData(Movie movie, Consumer<String> removeMovieListener){
            ((TextView) itemView.findViewById(R.id.rcv_txt_movie_title)).setText(movie.getTitle());
            ((TextView) itemView.findViewById(R.id.rcv_txt_movie_release_year)).setText(movie.getFormattedDate("dd-MM-yyyy"));
            ImageView imageView = itemView.findViewById(R.id.rcv_img_movie_poster);
            Glide.with(itemView).load(movie.getPosterUrl()).into(imageView);

            itemView.findViewById(R.id.row_btn_remove_movie).setOnClickListener(v -> {
                removeMovieListener.accept(movie.getId());
            });
        }

        public void setOnClickListener(View.OnClickListener listener){
            itemView.setOnClickListener(listener);
        }

    }

}
