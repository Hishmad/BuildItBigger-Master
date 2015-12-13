package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.JokeTelling;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.interfaces.CallBackData;

import java.lang.Override;


/**
 * A placeholder fragment containing a simple view.
 */
@SuppressLint("NewApi")
public class MainActivityFragment extends Fragment {

    private View mProgressBar;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Initialize the View
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // Initialize the progress bar
        mProgressBar = root.findViewById(R.id.progress_bar);




        // The button listener
        Button button = (Button) root.findViewById(R.id.button_tell_joke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mProgressBar.setVisibility(View.VISIBLE);

                // When the user click the button the data from JokeTelling will passed to the
                // MainActivity to implement the getData() method.
                ((CallBackData) getActivity()).getData(displayTheJoke());
            }
        });


        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        mProgressBar.setVisibility(View.INVISIBLE);

    }

    /**
     * Helper method to retrieve the joke from the Java Library
     * and return String.
     */
    private String displayTheJoke(){
        JokeTelling jokeTelling = new JokeTelling();
        return "Paid: " + jokeTelling.getJoke();

    }
}
