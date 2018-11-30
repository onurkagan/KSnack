package com.onurkagan.ksnack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.onurkagan.ksnack_lib.MinimalKSnack;
import com.onurkagan.ksnack_lib.MinimalKSnackBarEventListener;
import com.onurkagan.ksnack_lib.MinimalKSnackStyle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.activity_main_btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MinimalKSnack(MainActivity.this, new MinimalKSnackBarEventListener() {
                    @Override
                    public void showedMinimalSnackBar() {
                        System.out.println("fucking shit is started.");
                    }

                    @Override
                    public void stoppedMinimalSnackBar() {
                        System.out.println("fucking shit is stopped.");
                    }
                })  .setMessage("This is fucking minimal snackbar !")
                    .setStyle(MinimalKSnackStyle.STYLE_DEFAULT)
                    .setDuration(4000)
                    .show();
            }
        });

        findViewById(R.id.activity_main_btn_dissmis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
