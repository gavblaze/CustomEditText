package com.gavblaze.android.customedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private ShapeCustomView shapeCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shapeCustomView= findViewById(R.id.shapeCustomView);
    }

    public void changeSqrColor(View view) {
        shapeCustomView.swapColor();
    }
}
