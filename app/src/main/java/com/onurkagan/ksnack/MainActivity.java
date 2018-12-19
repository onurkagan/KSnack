package com.onurkagan.ksnack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.onurkagan.ksnack_lib.KSnack.KSnack;
import com.onurkagan.ksnack_lib.KSnack.KSnackBarEventListener;
import com.onurkagan.ksnack_lib.MinimalKSnack.MinimalKSnack;
import com.onurkagan.ksnack_lib.MinimalKSnack.MinimalKSnackStyle;

public class MainActivity extends AppCompatActivity {

    MinimalKSnack minimalKSnack;
    KSnack kSnack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minimalKSnack = new MinimalKSnack(MainActivity.this);
        kSnack = new KSnack(MainActivity.this);

        findViewById(R.id.activity_main_btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Minimal KSnack
                minimalKSnack
                        .setMessage("This is minimal KSnack !")
                        .setStyle(MinimalKSnackStyle.STYLE_SUCCESS)
                        .setBackgroundColor(R.color.colorGray)
                        .setBackgrounDrawable(R.drawable.background_ex_one)
                        .show();

                // KSnack
                kSnack
                        .setListener(new KSnackBarEventListener() {
                            @Override
                            public void showedSnackBar() {
                                System.out.println("Showed");
                            }

                            @Override
                            public void stoppedSnackBar() {
                                System.out.println("stopped");
                            }
                        })
                        .setAction("Deneme", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                System.out.println("Tıklama işi tamam !");
                            }
                        })
                        .setMessage("Deneme deneme bir iki !")
                        .show();
            }
        });

        findViewById(R.id.activity_main_btn_dissmis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minimalKSnack.dismiss();
                kSnack.dismiss();
            }
        });
    }
}
