package com.onurkagan.ksnack_lib.Animations;


import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;

public class Fade {

    private static long DEFAULT_ANIM_TIME = 300;

    public static class Out {

        // Fade in anim without optional anim time and interpolator.
        public static Animation getAnimation(){
            Animation animation = new AlphaAnimation(1, 0);
            animation.setDuration(DEFAULT_ANIM_TIME);
            animation.setInterpolator(new AccelerateInterpolator());
            return animation;
        }

        // Fade in anim with optional anim time.
        public static Animation getAnimation(int millisecond){
            Animation animation = new AlphaAnimation(1, 0);
            animation.setDuration(millisecond);
            animation.setInterpolator(new AccelerateInterpolator());
            return animation;
        }

        // Fade in anim with optional anim time and interpolator.
        public static Animation getAnimation(int millisecond, Interpolator interpolator){
            Animation animation = new AlphaAnimation(1, 0);
            animation.setDuration(millisecond);
            animation.setInterpolator(interpolator);
            return animation;
        }
    }

    public static class In {

        // Fade out anim without optional anim time and interpolator.
        public static Animation getAnimation(){
            Animation animation = new AlphaAnimation(0, 1);
            animation.setDuration(DEFAULT_ANIM_TIME);
            animation.setInterpolator(new AccelerateInterpolator());
            return animation;
        }

        // Fade out anim with optional anim time.
        public static Animation getAnimation(long millisecond){
            Animation animation = new AlphaAnimation(0, 1);
            animation.setDuration(millisecond);
            animation.setInterpolator(new AccelerateInterpolator());
            return animation;
        }

        // Fade out anim with optional anim time and interpolator.
        public static Animation getAnimation(long millisecond, Interpolator interpolator){
            Animation animation = new AlphaAnimation(0, 1);
            animation.setDuration(millisecond);
            animation.setInterpolator(interpolator);
            return animation;
        }
    }
}
