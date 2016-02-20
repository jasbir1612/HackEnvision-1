package com.cereal_killers.hackenvsion;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.andtinder.model.CardModel;
import com.andtinder.model.Orientations;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


public class MainActivity extends AppCompatActivity {

    CardContainer mCardContainer;
    Toolbar toolbar;
    SimpleCardStackAdapter adapter;

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

        Resources r = getResources();
        CardModel card = new CardModel("Title1", "Description", r.getDrawable(R.drawable.picture1));
        CardModel card2 = new CardModel("Title1", "Description", r.getDrawable(R.drawable.picture2));
        CardModel card3 = new CardModel("Title1", "Description", r.getDrawable(R.drawable.picture3));


        card.setOnCardDimissedListener(new CardModel.OnCardDimissedListener() {
            @Override
            public void onLike() {
                Toast.makeText(MainActivity.this, "I like it :)", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDislike() {

                Toast.makeText(MainActivity.this, "I don't like it :(", Toast.LENGTH_SHORT).show();

            }
        });
        card.setOnClickListener(new CardModel.OnClickListener() {
            public void OnClickListener() {

                Toast.makeText(MainActivity.this, "Click again", Toast.LENGTH_SHORT).show();
            }
        });


        adapter = new SimpleCardStackAdapter(this);
        adapter.add(card);
        adapter.add(card2);
        adapter.add(card3);
        mCardContainer.setAdapter(adapter);



//        new MaterialShowcaseView.Builder(this)
//                .setTarget(mCardContainer)
//                .setDismissText("GOT IT")
//                .setContentText("This is some amazing feature you should know about")
//                .setDelay(withDelay) // optional but starting animations immediately in onCreate can make them choppy
//                .singleUse(SHOWCASE_ID) // provide a unique ID used to ensure it is only shown once
//                .show();
//
//        ShowcaseConfig config = new ShowcaseConfig();
//        config.setDelay(500); // half second between each showcase view
//
//        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);
//
//        sequence.setConfig(config);
//
//        sequence.addSequenceItem(mCardContainer,
//                "This is button one", "GOT IT");

//        sequence.addSequenceItem(mButtonTwo,
//                "This is button two", "GOT IT");
//
//        sequence.addSequenceItem(mButtonThree,
//                "This is button three", "GOT IT");
//
//        sequence.start();




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
}
