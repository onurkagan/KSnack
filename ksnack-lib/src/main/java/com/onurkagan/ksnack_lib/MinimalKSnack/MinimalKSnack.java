package com.onurkagan.ksnack_lib.MinimalKSnack;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    private LinearLayout                    lnrSnack;
    private MinimalKSnackBarEventListener   minimalKSnackBarEventListener;
    private Animation                       inAnim, outAnim;

    public MinimalKSnack(Activity activity) {
        this.initializeMinimalBar(activity.findViewById(android.R.id.content));
    }

    /**
     * You can add MinimalKSnack to fragment but root view of fragment should not be LinearLayout.
     * @param fragment
     */
    @Deprecated
    public MinimalKSnack(Fragment fragment) {
        this.initializeMinimalBar(fragment.getView());
    }

    private void initializeMinimalBar(View parentView){
        linf = (LayoutInflater) parentView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        insertPoint = (ViewGroup) parentView;

        // Create view.
        snackView = linf.inflate(R.layout.layout_snack_small, null);
        snackView.setVisibility(View.GONE);
        ViewCompat.setTranslationZ(snackView, 999);
        insertPoint.addView(snackView, -1, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // Initialize component view.
        lnrSnack = snackView.findViewById(R.id.minimal_snack_bar_lnr);

        // Set default in anim.
        inAnim = Fade.In.getAnimation();

        // Set default out anim.
        outAnim = Fade.Out.getAnimation();
    }

    // Get view.
    public View getMinimalSnackView(){
        return lnrSnack;
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
                lnrSnack.setBackgroundColor(snackView.getContext().getResources().getColor(R.color.ksnack_default));
                break;
            case MinimalKSnackStyle.STYLE_INFO:
                lnrSnack.setBackgroundColor(snackView.getContext().getResources().getColor(R.color.ksnack_info));
                break;
            case MinimalKSnackStyle.STYLE_SUCCESS:
                lnrSnack.setBackgroundColor(snackView.getContext().getResources().getColor(R.color.ksnack_success));
                break;
            case MinimalKSnackStyle.STYLE_ERROR:
                lnrSnack.setBackgroundColor(snackView.getContext().getResources().getColor(R.color.ksnack_error));
                break;
            case MinimalKSnackStyle.STYLE_WARNING:
                lnrSnack.setBackgroundColor(snackView.getContext().getResources().getColor(R.color.ksnack_warning));

                break;
        }

        return this;
    }

    // Background color (Color res).
    public MinimalKSnack setBackgroundColor(@NonNull @ColorRes int colorInt){
        // Set color.
        lnrSnack.setBackgroundColor(snackView.getContext().getResources().getColor(colorInt));

        return this;
    }

    // Background drawable (Drawable res).
    public MinimalKSnack setBackgrounDrawable(@NonNull @DrawableRes int drawableInt){
        // Set drawable to view.
        ViewCompat.setBackground(lnrSnack, ContextCompat.getDrawable(lnrSnack.getContext(), drawableInt));

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

    // Set gravity.
    public MinimalKSnack alignBottom(){
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        if (lnrSnack.getRootView() != null) {
            ((ViewGroup)lnrSnack.getParent()).removeView(lnrSnack);
        }
        ((RelativeLayout) snackView.findViewById(R.id.minimal_snack_bar_rlv_holder)).addView(lnrSnack , layoutParams);

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
