package com.stockita.jokedisplay;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class JokeDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        if (savedInstanceState == null) {

            // Intent
            Intent intent = getIntent();
            String displayJoke = intent.getStringExtra("joke");

            // Instantiate the Fragment
            JokeDisplayFragment jokeDisplayFragment = new JokeDisplayFragment();

            // Pass the data
            jokeDisplayFragment.setJoke(displayJoke);

            // Fragment manager
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.joke_display_container, jokeDisplayFragment)
                    .commit();
        }


    }
}
