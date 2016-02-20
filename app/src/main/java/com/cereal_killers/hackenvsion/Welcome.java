package com.cereal_killers.hackenvsion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.gc.materialdesign.views.ButtonFlat;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * Created by Jasbir Singh on 2/20/2016.
 */
public class Welcome extends FragmentActivity {

    private ViewPager pager;
    private SmartTabLayout highlighter;
    private ButtonFlat btnnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        pager = (ViewPager) findViewById(R.id.pager);
        highlighter = (SmartTabLayout) findViewById(R.id.highlighter);
        btnnext = (ButtonFlat) findViewById(R.id.next);
        btnnext.setVisibility(View.GONE);

        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                switch (position){
                    case 0: return new WelcFragment1();
                    case 1: return new WelcFragment2();
                    case 2: return new WelcFragment3();

                    default:return null;

                }


            }

            @Override
            public int getCount() {
                return 3;
            }
        };

        pager.setAdapter(adapter);
        highlighter.setViewPager(pager);


        highlighter.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {

                if(position==2)
                {
                    btnnext.setVisibility(View.VISIBLE);
                }

            }

        });

    }

    public void onNext(View view)
    {
        if(pager.getCurrentItem()==2)
        {
            StartMainActivity();
        }
        else{
            pager.setCurrentItem(pager.getCurrentItem()+1, true);
        }
    }

    private void StartMainActivity()
    {
        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        preferences.edit().putBoolean("welcome_complete", true).apply();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();


    }
}
