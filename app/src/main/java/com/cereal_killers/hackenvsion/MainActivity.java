package com.cereal_killers.hackenvsion;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
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

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


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

//    public class AsyncHttpTask extends AsyncTask<String, String, Integer> {
//
//        @Override
//        protected Integer doInBackground(String... params) {
//
//            //checkConnectivity(MainActivity.this);
//            InputStream inputStream = null;
//            Integer result = 0; //unsuccessfull initially
//            HttpURLConnection httpURLConnection = null;
//            int statusCode;
//
//            try {
//                URL url = new URL(params[0]);
//                httpURLConnection = (HttpURLConnection) url.openConnection();
//                statusCode = httpURLConnection.getResponseCode();
//
//                if (statusCode == 200) {
//                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
//                    String response = convertToString(inputStream);
//                    parseJson(response);
//                    onProgressUpdate(response);
//                    result = 1;
//                } else {
//                    result = 0;
//                    //      Toaster.toast(MainActivity.this, "Check internet connection");//failed to fetch data
//                }
//            } catch (MalformedURLException e) {
//                //    Toaster.toast(MainActivity.this, "Error in url");
//            } catch (IOException e) {
//                //  Toaster.toast(MainActivity.this, "Error in opening connection");
//            }
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(Integer result) {
//            if (result == 1) {
////                Toaster.toast(MainActivity.this, "downloading completed");
//                // setting MainAdapter and linking with fragments is pending
//                mBandFragment.setArrayAdapter(bandsName, bandsContact, bandsAddress, bandsRate, bandsNear);
//                mBanquetFragment.setArrayAdapter(banquetsName, banquetsContact, banquetsAddress, banquetsRate, banquetsNear);
//                djFragment.setArrayAdapter(djName, djContact, djAddress, djRate, djNear);
//                travelFragment.setArrayAdapter(travelName, travelContact, travelAddress, travelRate, travelNear);
//                mBandFragment.creationComplete();
//                mProgressDialog.dismiss();
////                fakeSplash.setVisibility(View.GONE);
////                toolbar.setVisibility(View.VISIBLE);
////                mBanquetFragment.creationComplete();
////                djFragment.creationComplete();
////                travelFragment.creationComplete();
//            } else {
//                Toaster.toast(MainActivity.this, "downloading failed");
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            mProgressDialog.show();
//        }
//    }
//
//    public String convertToString(InputStream is) {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
//        String line = "";
//        String result = "";
//        try {
//            while ((line = bufferedReader.readLine()) != null) {
//                result += line;
//            }
//        } catch (IOException e) {
//            Toaster.toast(MainActivity.this, "Error in converting to string");
//        } finally {
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    Toaster.toast(MainActivity.this, "error in closing inputstream");
//                }
//            }
//        }
//        return result;
//    }
//
//    public void parseJson(String data) {
//        // temporary working done
//        try {
//
//
//        } catch (JSONException e) {
//            Toaster.toast(MainActivity.this, "Error in json parsing");
//        }
//    }
//
//    public void jsonLoop(JSONArray data, String[] name, String[] contact, String[] address, String[] rate, String[] near) {
//
//        for (int i = 0; i < data.length(); i++) {
//            JSONObject band = data.optJSONObject(i);
//            String mname = band.optString("name");
//            String mcontact = band.optString("contact");
//            String maddress = band.optString("address");
//            String mrate = band.optString("rate");
//            String mnear = band.optString("near");
//            name[i] = mname;
//            contact[i] = mcontact;
//            address[i] = maddress;
//            rate[i] = mrate;
//            near[i] = mnear;
//        }
//    }
}
