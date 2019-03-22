package com.onurkagan.ksnack_lib.MinimalKSnack;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onurkagan.ksnack_lib.Animations.Fade;
import com.onurkagan.ksnack_lib.R;


/**
 * Created by mimcrea on 29.10.2018.
 */

public class MinimalKSnack {
    public  View                            snackView;
    private LayoutInflater                  linf;
    private ViewGroup                       insertPoint;
    private LinearLayout                    lnrHost;
    private MinimalKSnackBarEventListener   minimalKSnackBarEventListener;
    private Animation                       inAnim, outAnim;

    public MinimalKSnack(Activity activity) {
        this.initializeMinimalBar(activity);
    }

    private void initializeMinimalBar(Activity activity){
        linf = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        insertPoint = activity.findViewById(android.R.id.content);

        // Create view.
        snackView = linf.inflate(R.layout.layout_snack_small, null);
        snackView.setVisibility(View.GONE);
        ViewCompat.setTranslationZ(snackView, 999);
        insertPoint.addView(snackView, 1, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // Initialize component view.
        lnrHost = snackView.findViewById(R.id.minimal_snack_bar_rlv);

        // Set default in anim.
        inAnim = Fade.In.getAnimation();

        // Set default out anim.
        outAnim = Fade.Out.getAnimation();
    }

    // Message.
    public MinimalKSnack setMessage(@NonNull String message){
        // Check null message.
        if (message == null) message = "n/a";

        // Initialize textview.
        TextView txtMessage = snackView.findViewById(R.id.minimal_snack_bar_txt_message);
        txtMessage.setText(message);

        return this;
    }

    // Duration
    public MinimalKSnack setDuration(@NonNull int millisecond){

        // Set duration.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, millisecond);
        return this;
    }

    // Style
    public MinimalKSnack setStyle(@NonNull int style){

        // Check style
        switch (style){
            case MinimalKSnackStyle.STYLE_DEFAULT:
                lnrHost.setBackgroundColor(snackView.getContext().getResources().getColor(R.color.ksnack_default));
                break;
            case MinimalKSnackStyle.STYLE_INFO:
                lnrHost.setBackgroundColor(snackView.getContext().getResources().getColor(R.color.ksnack_info));
                break;
            case MinimalKSnackStyle.STYLE_SUCCESS:
                lnrHost.setBackgroundColor(snackView.getContext().getResources().getColor(R.color.ksnack_success));
                break;
            case MinimalKSnackStyle.STYLE_ERROR:
                lnrHost.setBackgroundColor(snackView.getContext().getResources().getColor(R.color.ksnack_error));
                break;
            case MinimalKSnackStyle.STYLE_WARNING:
                lnrHost.setBackgroundColor(snackView.getContext().getResources().getColor(R.color.ksnack_warning));

                break;
        }

        return this;
    }

    // Background color (Color res).
    public MinimalKSnack setBackgroundColor(@NonNull @ColorRes int colorInt){

        // Set color.
        lnrHost.setBackgroundColor(snackView.getContext().getResources().getColor(colorInt));

        return this;
    }

    // Background drawable (Drawable res).
    public MinimalKSnack setBackgrounDrawable(@NonNull @DrawableRes int drawableInt){

        // Set drawable to view.
        ViewCompat.setBackground(lnrHost, ContextCompat.getDrawable(lnrHost.getContext(), drawableInt));
        //lnrHost.setBackground(snackView.getContext().getResources().getDrawable(drawableInt));

        return this;
    }

    // Set Listener.
    public MinimalKSnack setListener(MinimalKSnackBarEventListener listener){
        this.minimalKSnackBarEventListener = listener;
        return this;
    }

    // Set animation.
    public  MinimalKSnack setAnimation(Animation inAnim, Animation outAnim){
        this.inAnim = inAnim;
        this.outAnim = outAnim;

        return this;
    }

    public void show(){

        // Animation listener.
        inAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                snackView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                snackView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // Set animation to view.
        snackView.startAnimation(inAnim);

        // Start callback.
        if (minimalKSnackBarEventListener != null){
            minimalKSnackBarEventListener.showedMinimalSnackBar();
        }
    }

    public void dismiss(){

        // Animation listener.
        outAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                snackView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                snackView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // Set animation to view.
        snackView.startAnimation(outAnim);

        // Stop callback.
        if (minimalKSnackBarEventListener != null){
            minimalKSnackBarEventListener.stoppedMinimalSnackBar();
        }
    }
}
