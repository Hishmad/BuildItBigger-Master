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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.interfaces.CallBackData;


/**
 * A placeholder fragment containing a simple view.
 */
@SuppressLint("NewApi")
public class MainActivityFragment extends Fragment {

    private View mProgressBar;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;


    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Initialize the View
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mProgressBar = root.findViewById(R.id.progress_bar);

        // Admob
        mAdView = (AdView) root.findViewById(R.id.adView);
        requestNewAdMob();

        // Interstitial
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        requestNewInterstitial();

        // When the user close the interstitial
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {

                mProgressBar.setVisibility(View.VISIBLE);
                requestNewInterstitial();
                ((CallBackData) getActivity()).getData(displayTheJoke());

            }
        });

        // The button listener
        Button button = (Button) root.findViewById(R.id.button_tell_joke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    ((CallBackData) getActivity()).getData(displayTheJoke());
                }
            }
        });


        return root;
    }

    /**
     * Interstitial pre loading
     */
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void requestNewAdMob() {
        // The AdView
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("611E89290394F0C0E833DC7543E25B27") //AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Remove the progress bar.
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        requestNewAdMob();
        requestNewInterstitial();

    }

    /**
     * Helper method to retrieve the joke from the Java Library
     * and return String.
     */
    private String displayTheJoke(){
        JokeTelling jokeTelling = new JokeTelling();
        return "Free: " + jokeTelling.getJoke();

    }
}
