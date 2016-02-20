package com.cereal_killers.hackenvsion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Body extends AppCompatActivity {

    TextView bodytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        bodytext = (TextView) findViewById(R.id.txtbody);
    }
}
