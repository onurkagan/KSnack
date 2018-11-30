package com.onurkagan.ksnack_lib;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by mimcrea on 29.10.2018.
 */

public class MinimalKSnack {
    public  View snackView;
    private LayoutInflater linf;
    private ViewGroup insertPoint;
    private MinimalKSnackBarEventListener minimalKSnackBarEventListener;

    public MinimalKSnack(Activity activity) {
        initializeMinimalBar(activity);
    }

    private void initializeMinimalBar(Activity activity){
        linf = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        insertPoint = activity.findViewById(android.R.id.content);
        // Create view.
        snackView = linf.inflate(R.layout.layout_snack_small, null);
        snackView.setVisibility(View.GONE);
        snackView.setZ(999);
        insertPoint.addView(snackView, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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
        // Initialize relative layout.
        RelativeLayout rlvHost = snackView.findViewById(R.id.minimal_snack_bar_rlv);

        // Check style
        switch (style){
            case MinimalKSnackStyle.STYLE_DEFAULT:
                rlvHost.setBackgroundColor(snackView.getContext().getResources().getColor(R.color.ksnack_default));
                break;
            case MinimalKSnackStyle.STYLE_INFO:
                rlvHost.setBackgroundColor(snackView.getContext().getResources().getColor(R.color.ksnack_info));
                break;
            case MinimalKSnackStyle.STYLE_SUCCESS:
                rlvHost.setBackgroundColor(snackView.getContext().getResources().getColor(R.color.ksnack_success));
                break;
            case MinimalKSnackStyle.STYLE_ERROR:
                rlvHost.setBackgroundColor(snackView.getContext().getResources().getColor(R.color.ksnack_error));
                break;
            case MinimalKSnackStyle.STYLE_WARNING:
                rlvHost.setBackgroundColor(snackView.getContext().getResources().getColor(R.color.ksnack_warning));

                break;
        }

        return this;
    }

    // Background color (Color res).
    public MinimalKSnack setBackgroundColor(@NonNull @ColorRes int colorInt){

        // Initialize relative layout.
        RelativeLayout rlvHost = snackView.findViewById(R.id.minimal_snack_bar_rlv);

        // Set color.
        rlvHost.setBackgroundColor(snackView.getContext().getResources().getColor(colorInt));

        return this;
    }

    // Set Listener.
    public MinimalKSnack setListener(MinimalKSnackBarEventListener listener){
        this.minimalKSnackBarEventListener = listener;
        return this;
    }

    public void show(){
        snackView.setVisibility(View.VISIBLE);
        if (minimalKSnackBarEventListener != null){
            minimalKSnackBarEventListener.showedMinimalSnackBar();
        }
    }

    public void dismiss(){
        snackView.setVisibility(View.GONE);
        if (minimalKSnackBarEventListener != null){
            minimalKSnackBarEventListener.stoppedMinimalSnackBar();
        }
    }
}
