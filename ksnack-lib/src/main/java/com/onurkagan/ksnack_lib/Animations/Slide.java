package com.onurkagan.ksnack_lib.Animations;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

public class Slide {

    private static long DEFAULT_ANIM_TIME = 400;
    private static Animation animation;

    public static  class Up {
        // Slide up anim without optional anim time and interpolator.
        public static Animation getAnimation(View view){
            view.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (isMinimalKSnack(view)){
                animation = new TranslateAnimation(.0f, .0f, .0f, -(float) view.getMeasuredHeight());
            } else {
                animation = new TranslateAnimation(.0f, .0f, (float) view.getMeasuredHeight(), 0);
            }
            animation.setDuration(DEFAULT_ANIM_TIME);
            animation.setInterpolator(new AccelerateInterpolator());
            return animation;
        }

        // Slide up anim with optional anim time.
        public static Animation getAnimation(View view, int millisecond){
            view.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (isMinimalKSnack(view)){
                animation = new TranslateAnimation(.0f, .0f, .0f, -(float) view.getMeasuredHeight());
            } else {
                animation = new TranslateAnimation(.0f, .0f, (float) view.getMeasuredHeight(), 0);
            }
            animation.setDuration(millisecond);
            animation.setInterpolator(new AccelerateInterpolator());
            return animation;
        }

        // Slide up anim with optional anim time and interpolator.
        public static Animation getAnimation(View view, int millisecond, Interpolator interpolator){
            view.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (isMinimalKSnack(view)){
                animation = new TranslateAnimation(.0f, .0f, .0f, -(float) view.getMeasuredHeight());
            } else {
                animation = new TranslateAnimation(.0f, .0f, (float) view.getMeasuredHeight(), 0);
            }
            animation.setDuration(millisecond);
            animation.setInterpolator(interpolator);
            return animation;
        }

    }

    public static  class Down {
        // Slide down anim without optional anim time and interpolator.
        public static Animation getAnimation(View view){
            view.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (isMinimalKSnack(view)){
                animation = new TranslateAnimation(.0f, .0f, -(float) view.getMeasuredHeight(), .0f);
            } else {
                animation = new TranslateAnimation(0, 0,.0f, (float) view.getMeasuredHeight());
            }
            animation.setDuration(DEFAULT_ANIM_TIME);
            animation.setInterpolator(new DecelerateInterpolator());
            return animation;
        }

        // Slide down anim with optional anim time.
        public static Animation getAnimation(View view, int millisecond){
            view.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (isMinimalKSnack(view)){
                animation = new TranslateAnimation(.0f, .0f, -(float) view.getMeasuredHeight(), .0f);
            } else {
                animation = new TranslateAnimation(0, 0,.0f, (float) view.getMeasuredHeight());
            }
            animation.setDuration(millisecond);
            animation.setInterpolator(new DecelerateInterpolator());
            return animation;
        }

        // Slide down anim with optional anim time and interpolator.
        public static Animation getAnimation(View view, int millisecond, Interpolator interpolator){
            view.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (isMinimalKSnack(view)){
                animation = new TranslateAnimation(.0f, .0f, -(float) view.getMeasuredHeight(), .0f);
            } else {
                animation = new TranslateAnimation(0, 0,.0f, (float) view.getMeasuredHeight());
            }
            animation.setDuration(millisecond);
            animation.setInterpolator(interpolator);
            return animation;
        }

    }

    public static boolean isMinimalKSnack(View view){
        if (view.getResources().getResourceEntryName(view.getId()).equals("minimal_snack_bar_rlv")) return true;
        return false;
    }
}
