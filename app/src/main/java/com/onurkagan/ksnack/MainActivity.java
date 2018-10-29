package com.onurkagan.ksnack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.onurkagan.ksnack_lib.KSnack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final KSnack kSnack = new KSnack(MainActivity.this);

        findViewById(R.id.activity_main_btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kSnack.show();
            }
        });

        findViewById(R.id.activity_main_btn_dissmis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kSnack.dismiss();
            }
        });
    }
}
