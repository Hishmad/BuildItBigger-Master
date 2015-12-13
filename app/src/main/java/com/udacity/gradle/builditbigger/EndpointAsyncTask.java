package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.interfaces.CallBackFromAsyncTask;

import java.io.IOException;

/**
 * Created by hishmadabubakaralamudi on 12/6/15.
 */
public class EndpointAsyncTask extends AsyncTask<Pair<MainActivity, String>, String, String> {

    private static MyApi myApiService = null;
    private CallBackFromAsyncTask callBackFromAsyncTask;
    private boolean isDeployed = true; // make false for local host
    public String result;


    @Override
    protected String doInBackground(Pair<MainActivity, String>... pairs) {
        if (myApiService == null) {

            MyApi.Builder builder = null;
            if (!isDeployed) {
                builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
            }

            if (isDeployed) {
                builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://makeitbigger-1157.appspot.com/_ah/api/");
            }

            myApiService = builder.build();
        }

        callBackFromAsyncTask = pairs[0].first;
        String joke = pairs[0].second;

        try {
            return myApiService.tellJoke(joke).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String data) {
        // Pass data to Android Library.
        callBackFromAsyncTask.theData(data);
        result = data;
    }




}
