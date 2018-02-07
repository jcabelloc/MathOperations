package com.tamu.jcabelloc.mathoperations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void startGame(View view) {

        Intent intent = new Intent(getApplicationContext(), Game.class);
        intent.putExtra("operator", view.getTag().toString());
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
