package com.onurkagan.ksnack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onurkagan.ksnack_lib.Animations.Fade;
import com.onurkagan.ksnack_lib.Animations.Slide;
import com.onurkagan.ksnack_lib.KSnack.KSnack;
import com.onurkagan.ksnack_lib.KSnack.KSnackBarEventListener;
import com.onurkagan.ksnack_lib.MinimalKSnack.MinimalKSnack;
import com.onurkagan.ksnack_lib.MinimalKSnack.MinimalKSnackStyle;

public class MainActivity extends AppCompatActivity {

    private Button          btnShow, btnDismiss;
    private MinimalKSnack   minimalKSnack;
    private KSnack          kSnack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize objects.
        minimalKSnack   = new MinimalKSnack(MainActivity.this);
        kSnack          = new KSnack(MainActivity.this);
        btnShow         = findViewById(R.id.activity_main_btn_show);
        btnDismiss      = findViewById(R.id.activity_main_btn_dissmis);

        // Open fragment.
        getSupportFragmentManager().beginTransaction().add(R.id.activity_main_lyt_holder, BlankFragment.newInstance()).commit();

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Minimal KSnack
                minimalKSnack
                        .setMessage("This is minimal KSnack !")
                        .setStyle(MinimalKSnackStyle.STYLE_SUCCESS)
                        .setBackgroundColor(R.color.colorGray)
                        .setBackgrounDrawable(R.drawable.background_minimal_snack)
                        .setAnimation(Fade.In.getAnimation(), Fade.Out.getAnimation())
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
                                System.out.println("Stopped");
                            }
                        })
                        .setAction("Click", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                System.out.println("Your action !");
                            }
                        })
                        .setButtonTextColor(R.color.colorAccent)
                        .setMessage("This is KSnack !")
                        .setAnimation(Slide.Up.getAnimation(kSnack.getSnackView()), Slide.Down.getAnimation(kSnack.getSnackView()))
                        .show();
            }
        });

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minimalKSnack.dismiss();
                kSnack.dismiss();
            }
        });
    }
}
