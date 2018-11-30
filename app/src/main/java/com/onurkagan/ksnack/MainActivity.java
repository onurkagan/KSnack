package com.onurkagan.ksnack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.onurkagan.ksnack_lib.MinimalKSnack;
import com.onurkagan.ksnack_lib.MinimalKSnackStyle;

public class MainActivity extends AppCompatActivity {

    MinimalKSnack minimalKSnack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minimalKSnack = new MinimalKSnack(MainActivity.this);

        findViewById(R.id.activity_main_btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minimalKSnack
                        .setMessage("This is minimal KSnack !")
                        .setStyle(MinimalKSnackStyle.STYLE_SUCCESS)
                        .setBackgroundColor(R.color.colorGray)
                        .show();
            }
        });

        findViewById(R.id.activity_main_btn_dissmis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minimalKSnack.dismiss();
            }
        });
    }
}
