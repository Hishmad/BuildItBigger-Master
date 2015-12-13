package com.stockita.jokedisplay;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by hishmadabubakaralamudi on 12/6/15.
 */
public class JokeDisplayFragment extends Fragment {

    private String mJoke;

    // Empty constructor
    public JokeDisplayFragment() {}

    public void setJoke(String joke) {
        this.mJoke = joke;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mJoke = savedInstanceState.getString("one");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("one", mJoke);
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Initialize the view
        View rootView = inflater.inflate(R.layout.fragment_joke_display, container, false);

        // Display the joke
        TextView displayJoke = (TextView) rootView.findViewById(R.id.joke_display_text_view);
        displayJoke.setText("Joke Display: " + mJoke);


        return rootView;
    }
}
