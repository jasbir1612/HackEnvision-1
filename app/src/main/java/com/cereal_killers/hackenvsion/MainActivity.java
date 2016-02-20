package com.cereal_killers.hackenvsion;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.andtinder.model.CardModel;
import com.andtinder.model.Orientations;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;
import com.gc.materialdesign.views.Button;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements CardModel.OnCardDimissedListener, CardModel.OnClickListener{

    CardContainer mCardContainer;
    Toolbar toolbar;
    SimpleCardStackAdapter adapter;
    ProgressDialog mProgressDialog;
    String[] titles, body, imageUrl;
    CardModel card;
    public static int position = 0;
    public static final String link = "https://api.myjson.com/bins/47iub";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCardContainer = (CardContainer) findViewById(R.id.layoutview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mCardContainer.setOrientation(Orientations.Orientation.Ordered);
        mCardContainer.setOrientation(Orientations.Orientation.Disordered);

        setSupportActionBar(toolbar);
        toolbar.setTitle("HackEnvision");
        toolbar.setTitleTextColor(Color.WHITE);
        adapter = new SimpleCardStackAdapter(this);

        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setTitle("Downloading...");
        mProgressDialog.setMessage("Please wait.");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setIndeterminate(true);

        new AsyncHttpTask().execute(link);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onLike() {
        position++;
    }

    @Override
    public void onDislike() {
        position++;
    }

    @Override
    public void OnClickListener() {

//        Intent intent = new Intent(MainActivity.this, Body.class);
//        intent.putExtra("body", body[position]);
//        startActivity(intent);

    }

    public class AsyncHttpTask extends AsyncTask<String, String, Integer> {

        @Override
        protected Integer doInBackground(String... params) {

            InputStream inputStream = null;
            Integer result = 0;
            HttpURLConnection httpURLConnection = null;
            int statusCode;

            try {
                URL url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                statusCode = httpURLConnection.getResponseCode();

                if (statusCode == 200) {
                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    String response = convertToString(inputStream);
                    parseJson(response);
                    onProgressUpdate(response);
                    result = 1;
                } else {
                    result = 0;
                }
            } catch (MalformedURLException e) {

            } catch (IOException e) {
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {

            if (result == 1) {

                for(int i=titles.length-1; i>0 ;i--) {
                    mProgressDialog.dismiss();
                    Resources r = getResources();
//                    Bitmap bitmap =  Picasso.with(MainActivity.this).load(imageUrl[i]).placeholder(R.drawable.icon);
                    card = new CardModel(titles[i], body[i],r.getDrawable(R.drawable.picture1));
                    card.setOnCardDimissedListener(MainActivity.this);
                    card.setOnClickListener(MainActivity.this);
                    adapter.add(card);
                }

            } else {
                Toaster.toast(MainActivity.this, "downloading failed");
            }
            mCardContainer.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute() {
            mProgressDialog.show();
        }
    }

    public String convertToString(InputStream is) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String line = "";
        String result = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            Toaster.toast(MainActivity.this, "Error in converting to string");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    Toaster.toast(MainActivity.this, "error in closing inputstream");
                }
            }
        }
        return result;
    }

    public void parseJson(String data) {

        try {
            JSONArray response = new JSONArray(data);

            titles = new String[response.length()];
            body = new String[response.length()];
            imageUrl = new String[response.length()];


            for(int i = 0; i < response.length(); i++)
            {
                JSONObject jsonObject = response.optJSONObject(i);
                titles[i] = jsonObject.optString("title");
                body[i] = jsonObject.optString("body");
                imageUrl[i] = jsonObject.optString("image_url");
            }

        } catch (JSONException e) {
            Toaster.toast(MainActivity.this, "Error in json parsing");
        }
    }
}
