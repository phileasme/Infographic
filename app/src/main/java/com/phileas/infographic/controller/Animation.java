package com.phileas.infographic.controller;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

import com.phileas.infographic.R;

/**
 * Created by Hannah on 10/12/2015.
 */
public class Animation {
    ImageView image;
    public Animation(ImageView image){
        this.image = image;

    }

    public void runAnimationEase(){
        image.setBackgroundResource(R.drawable.anim2);
        image.post(new Runnable() {
            @Override
            public void run() {
                AnimationDrawable frameAnim = (AnimationDrawable) image.getBackground();
                frameAnim.start();
            }
        });
    }

}
