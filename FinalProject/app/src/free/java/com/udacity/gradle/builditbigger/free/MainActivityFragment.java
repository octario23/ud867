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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import app.permissions.com.displayjokes.DisplayJokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener{

    private Button tellJoke;

    public MainActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        tellJoke = (Button) root.findViewById(R.id.tellJoke);
        tellJoke.setOnClickListener(this);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
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
