package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.interfaces.CallBackFromAsyncTask;

import java.io.IOException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends AndroidTestCase implements CallBackFromAsyncTask {


    public void testVerify() {

        MainActivity activity = new MainActivity();

        // execute the AsyncTask, and pass the data
        EndpointAsyncTask s;
        s = new EndpointAsyncTask();
        s.execute(new Pair<MainActivity, String>(activity, "hello"));


    }

    @Override
    public void theData(String data) {
        assertEquals("hello", data);

    }
}