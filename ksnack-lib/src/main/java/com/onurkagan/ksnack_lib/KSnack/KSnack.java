package com.onurkagan.ksnack_lib.KSnack;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.onurkagan.ksnack_lib.Animations.Fade;
import com.onurkagan.ksnack_lib.R;

public class KSnack {

    public View snackView;
    private LayoutInflater linf;
    private ViewGroup insertPoint;
    private RelativeLayout rlvHost;
    private Button btnAction;
    private TextView txtMessage;
    private KSnackBarEventListener kSnackBarEventListener;
    private Animation inAnim, outAnim;

    public KSnack(Activity activity) {
        this.initializeKSnackBar(activity);
    }

    private void initializeKSnackBar(Activity activity) {
        linf = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        insertPoint = activity.findViewById(android.R.id.content);

        // Create view.
        snackView = linf.inflate(R.layout.layout_snack_normal, null);

        snackView.setVisibility(View.GONE);

        ViewCompat.setTranslationZ(snackView, 999);
        insertPoint.addView(snackView, 1, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // Initialize component view.
        rlvHost = snackView.findViewById(R.id.normal_snack_bar_rlv);

        // Initialize textview.
        txtMessage = snackView.findViewById(R.id.snack_bar_txt_message);

        // Action button.
        btnAction = snackView.findViewById(R.id.snack_bar_btn_action);

        // Set default in anim.
        inAnim = Fade.In.getAnimation();

        // Set default out anim.
        outAnim = Fade.Out.getAnimation();
    }

    // Get view.
    public View getSnackView() {
        return rlvHost;
    }

    // Message.
    public KSnack setMessage(@NonNull String message) {
        // Check null message.
        if (message == null) message = "n/a";

        txtMessage.setText(message);

        return this;
    }

    // Duration
    public KSnack setDuration(@NonNull int millisecond) {

        // Set duration.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, millisecond);

        return this;
    }

    // Set button action.
    public KSnack setAction(@NonNull String buttonText, @NonNull final View.OnClickListener clickListener) {

        // Change button visibility.
        btnAction.setVisibility(View.VISIBLE);

        // Set button text.
        btnAction.setText(buttonText);

        // Set onClickListener.
        btnAction.setOnClickListener(clickListener);

        return this;
    }

    // Background color (Color res).
    public KSnack setBackColor(@NonNull @ColorRes int colorInt) {

        // Get current background drawable.
        Drawable drawable = rlvHost.getBackground();

        // Change drawable background color.
        drawable.setColorFilter(rlvHost.getContext().getResources().getColor(colorInt), PorterDuff.Mode.SRC);

        return this;
    }

    // Background drawable (Drawable res).
    public KSnack setBackgrounDrawable(@NonNull @DrawableRes int drawableInt) {

        // Set drawable to view.
        ViewCompat.setBackground(rlvHost, ContextCompat.getDrawable(rlvHost.getContext(), drawableInt));

        return this;
    }

    // Change description text color.
    public KSnack setTextColor(@NonNull @ColorRes int colorInt) {

        // Change text color.
        txtMessage.setTextColor(txtMessage.getContext().getResources().getColor(colorInt));

        return this;
    }

    // Change button text color.
    public KSnack setButtonTextColor(@NonNull @ColorRes int colorInt) {

        // Change button text color.
        btnAction.setTextColor(btnAction.getContext().getResources().getColor(colorInt));

        return this;
    }


    public KSnack setTextTypeFace(@NonNull Typeface typeFace) {
        //Change text typeface.
        txtMessage.setTypeface(typeFace);
        return this;
    }


    public KSnack setButtonTypeFace(@NonNull Typeface typeFace) {
        //Change text typeface.
        btnAction.setTypeface(typeFace);
        return this;
    }


    // Set Listener.
    public KSnack setListener(KSnackBarEventListener listener) {
        this.kSnackBarEventListener = listener;
        return this;
    }

    // Set animation.
    public KSnack setAnimation(Animation inAnim, Animation outAnim) {
        this.inAnim = inAnim;
        this.outAnim = outAnim;
        return this;
    }

    public void show() {

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
        if (kSnackBarEventListener != null) {
            kSnackBarEventListener.showedSnackBar();
        }
    }

    public void dismiss() {

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
        if (kSnackBarEventListener != null) {
            kSnackBarEventListener.stoppedSnackBar();
        }
    }
}
