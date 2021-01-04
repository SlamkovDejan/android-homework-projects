package com.example.lab3.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab3.R;
import com.example.lab3.db.entity.Movie;
import com.example.lab3.ui.adapter.MovieAdapter;
import com.example.lab3.viewmodel.FirstFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FirstFragmentViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.viewModel = ViewModelProviders.of(this).get(FirstFragmentViewModel.class);

//        ((Lab3App) getActivity().getApplication()).nukeDatabase();

        RecyclerView rcvMovies = view.findViewById(R.id.rcv_list_movies);
        rcvMovies.setAdapter(new MovieAdapter(new ArrayList<>(), this::goToMovieDetails, this::removeMovie));

        this.viewModel.getMovies().observe(getViewLifecycleOwner(), this::updateMovieList);

        ((EditText) view.findViewById(R.id.edit_query_name)).setOnEditorActionListener(this::clickSend);
        ((EditText) view.findViewById(R.id.edit_query_year)).setOnEditorActionListener(this::clickSend);

        super.onViewCreated(view, savedInstanceState);
    }

    public boolean clickSend(TextView view, int actionId, KeyEvent event){
        if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT){
            EditText titleView = getView().findViewById(R.id.edit_query_name);
            String title = titleView.getText().toString();
            EditText yearView = getView().findViewById(R.id.edit_query_year);
            String year = yearView.getText().toString();

            titleView.clearFocus();
            yearView.clearFocus();
            titleView.setText("");
            yearView.setText("");

            if(!year.isEmpty())
                this.viewModel.search(title, Integer.parseInt(year));
            else
                this.viewModel.search(title);
            hideKeyboard();
            return true;
        }
        return false;
    }

    public void updateMovieList(List<Movie> movies){
        System.out.println(movies);
        MovieAdapter adapter = (MovieAdapter) ((RecyclerView) getView().findViewById(R.id.rcv_list_movies)).getAdapter();
        adapter.updateData(movies);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    public void goToMovieDetails(String selectedMovieId){
        Bundle args = new Bundle();
        args.putString("selectedMovie", selectedMovieId);
        NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment, args);
    }

    public void removeMovie(String movieId){
        this.viewModel.getMovieById(movieId).observe(getViewLifecycleOwner(), movie -> this.viewModel.deleteMovie(movie));
    }

}