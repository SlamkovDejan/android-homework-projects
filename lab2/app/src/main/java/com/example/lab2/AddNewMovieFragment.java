package com.example.lab2;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.fragment.NavHostFragment;
import com.example.lab2.data.FakeApi;
import com.example.lab2.data.model.Movie;
import com.example.lab2.utils.Constants;

import java.util.Arrays;
import java.util.stream.IntStream;

public class AddNewMovieFragment extends Fragment {

    private int numActors;
    private final FakeApi api;

    public AddNewMovieFragment() {
        this.numActors = 1;
        api = FakeApi.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_save_movie).setOnClickListener(this::clickSave);
        view.findViewById(R.id.btn_more_actors).setOnClickListener(this::clickMoreActors);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.add_new_movie_fragment_title);
    }

    public void clickMoreActors(View btnMoreActors){
        EditText newActorView = new EditText(getContext());
        newActorView.setLayoutParams(getLayoutParamsOfNewActorView());
        newActorView.setHint(R.string.actor);
        newActorView.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        newActorView.setEms(10);
        newActorView.setId(getResources().getInteger(R.integer.my_id) + this.numActors);
        ((ConstraintLayout) getView().findViewById(R.id.add_movie_container)).addView(newActorView);
        moveBtnMoreActors(newActorView.getId());
        this.numActors++;
    }

    private ConstraintLayout.LayoutParams getLayoutParamsOfNewActorView(){
        EditText lastActorView = getLastActorView();
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                Constraints.LayoutParams.WRAP_CONTENT,
                Constraints.LayoutParams.WRAP_CONTENT
        );
        params.topMargin = 16;
        params.endToEnd = lastActorView.getId();
        params.startToStart = lastActorView.getId();
        params.topToBottom = lastActorView.getId();
        params.horizontalBias = 0;
        return params;
    }

    private EditText getLastActorView() {
        if(this.numActors == 1)
            return getView().findViewById(R.id.edit_actor_1);
        int lastActorId = getResources().getInteger(R.integer.my_id) + this.numActors - 1;
        return getView().findViewById(lastActorId);
    }

    private void moveBtnMoreActors(int newActorViewId) {
        View btnMore = getView().findViewById(R.id.btn_more_actors);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) btnMore.getLayoutParams();
        params.topToTop = newActorViewId;
        btnMore.setLayoutParams(params);
    }

    public void clickSave(View btnSave){
        EditText titleView = getView().findViewById(R.id.edit_title);
        EditText directorView = getView().findViewById(R.id.edit_director);
        EditText descriptionView = getView().findViewById(R.id.edit_description);
        EditText[] actorViews = getActorViews();

        if(notValid(titleView)) {
            Toast.makeText(getContext(), "Title is required", Toast.LENGTH_LONG).show();
            return;
        }
        if(notValid(directorView)) {
            Toast.makeText(getContext(), "Director name is required", Toast.LENGTH_LONG).show();
            return;
        }
        if(notValid(descriptionView)) {
            Toast.makeText(getContext(), "Description is required", Toast.LENGTH_LONG).show();
            return;
        }
        if(notValid(actorViews)) {
            Toast.makeText(getContext(), "Actor names are required", Toast.LENGTH_LONG).show();
            return;
        }

        Movie newMovie = new Movie(
                api.count(),
                titleView.getText().toString(),
                descriptionView.getText().toString(),
                directorView.getText().toString(),
                Arrays.stream(actorViews).map(actorView -> actorView.getText().toString()).toArray(String[]::new)
        );
        api.addMovie(newMovie);
        NavHostFragment.findNavController(this).popBackStack();
    }

    private EditText[] getActorViews() {
        return (EditText[]) IntStream.range(0, this.numActors).mapToObj(i -> {
            if(i == 0)
                return getView().findViewById(R.id.edit_actor_1);
            int actorViewId = getResources().getInteger(R.integer.my_id) + i;
            return getView().findViewById(actorViewId);
        }).toArray(EditText[]::new);
    }

    private boolean notValid(EditText... views) {
        return Arrays.stream(views).anyMatch(v -> v.getText() == null || v.getText().toString().trim().isEmpty());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(Constants.KEY_NUM_ACTORS, this.numActors);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null)
            this.numActors = savedInstanceState.getInt(Constants.KEY_NUM_ACTORS);
    }

}