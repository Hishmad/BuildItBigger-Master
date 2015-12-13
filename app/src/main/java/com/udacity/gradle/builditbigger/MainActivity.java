package com.udacity.gradle.builditbigger;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.stockita.jokedisplay.JokeDisplayActivity;
import com.udacity.gradle.builditbigger.interfaces.CallBackData;
import com.udacity.gradle.builditbigger.interfaces.CallBackFromAsyncTask;


public class MainActivity extends AppCompatActivity implements CallBackData, CallBackFromAsyncTask{



    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Fragment
        FragmentManager fragmentManager = getFragmentManager();
        MainActivityFragment mainActivityFragment = new MainActivityFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.container, mainActivityFragment)
                .commit();

    }

    /**
     * When the user click the button
     */
    @Override
    public void getData(String data) {

        // execute the AsyncTask, and pass the data
        EndpointAsyncTask s;
        s = new EndpointAsyncTask();
        s.execute(new Pair<MainActivity, String>(this, data));



    }

    /**
     * Result retrieved from the EndpointAsyncTask
     */
    @Override
    public void theData(String data) {

        // Use intent then putExtra to pass the data.
        Intent intent = new Intent(this, JokeDisplayActivity.class);
        intent.putExtra("joke", data);
        startActivity(intent);
    }
}
