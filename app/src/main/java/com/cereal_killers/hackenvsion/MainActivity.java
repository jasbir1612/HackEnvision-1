package com.cereal_killers.hackenvsion;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Color;
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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements CardModel.OnCardDimissedListener, CardModel.OnClickListener{

    CardContainer mCardContainer;
    Toolbar toolbar;
    SimpleCardStackAdapter adapter;
    ProgressDialog mProgressDialog;
    String[] titles={"title1","title2","title3","title4"};
    String[] description= {"description1","description2","description3","description4"};
    String[] img;
    Button like, dislike;
    CardModel card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCardContainer = (CardContainer) findViewById(R.id.layoutview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        like = (Button) findViewById(R.id.like);
        dislike = (Button) findViewById(R.id.dislike);
        mCardContainer.setOrientation(Orientations.Orientation.Ordered);
        mCardContainer.setOrientation(Orientations.Orientation.Disordered);

        setSupportActionBar(toolbar);
        toolbar.setTitle("HackEnvision");
        toolbar.setTitleTextColor(Color.WHITE);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLike();
            }
        });
        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDislike();
            }
        });

        //picasso


//        Picasso.with(this)
//                .load("YOUR IMAGE URL HERE")
//                .into(imageView);




//        CardModel card = new CardModel("Title1", "Description", r.getDrawable(R.drawable.picture1));
//        CardModel card2 = new CardModel("Title1", "Description", r.getDrawable(R.drawable.picture2));
//        CardModel card3 = new CardModel("Title1", "Description", r.getDrawable(R.drawable.picture3));


        adapter = new SimpleCardStackAdapter(this);

        for(int i=0;i<4;i++)
        {
            Resources r = getResources();
            card = new CardModel(titles[i], description[i],r.getDrawable(R.drawable.picture1));
            adapter.add(card);
            card.setOnCardDimissedListener(this);
            card.setOnClickListener(this);
        }


        mCardContainer.setAdapter(adapter);

        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setTitle("Downloading...");
        mProgressDialog.setMessage("Please wait.");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setIndeterminate(true);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLike() {

        Toast.makeText(MainActivity.this, "I like it :)", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDislike() {

        Toast.makeText(MainActivity.this, "I don't like it :(", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void OnClickListener() {

    }

    public class AsyncHttpTask extends AsyncTask<String, String, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream = null;
            Integer result = 0; //unsuccessfull initially
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
                    //      Toaster.toast(MainActivity.this, "Check internet connection");//failed to fetch data
                }
            } catch (MalformedURLException e) {
                //    Toaster.toast(MainActivity.this, "Error in url");
            } catch (IOException e) {
                //  Toaster.toast(MainActivity.this, "Error in opening connection");
            }
            return result;
        }


        @Override
        protected void onPreExecute() {
            mProgressDialog.show();
        }
    }
    private void parseJson(String data) {
    }

    private String convertToString(InputStream is) {
        return null;
    }
}
