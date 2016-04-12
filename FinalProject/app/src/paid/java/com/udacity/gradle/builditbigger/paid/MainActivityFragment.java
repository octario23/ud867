package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.Jokes;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import app.permissions.com.displayjokes.DisplayJokeActivity;

/**
 * Created by omarin on 4/10/16.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener{

    private Button tellJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        tellJoke = (Button) root.findViewById(R.id.tellJoke);
        tellJoke.setOnClickListener(this);
        return root;
    }


    @Override
    public void onClick(View v) {
        EndpointsAsyncTask.responseListener listener = new EndpointsAsyncTask.responseListener() {
            @Override
            public void onResponse(String joke) {
                Intent intent = new Intent(getActivity(), DisplayJokeActivity.class);
                intent.putExtra(Jokes.TEXT_JOKE,joke);
                startActivity(intent);

            }
        };
        Jokes joke = new Jokes();
        new EndpointsAsyncTask(getActivity(), listener).execute(new Pair<Context, String>(getActivity(),joke.getInitialJoke()));
    }
}