package com.desafiozup.core.util;

import android.view.View;

public class AnimationUtil {

    public static void fadeIn(View view) {
        view.setAlpha(0f);
        view.animate()
                .alpha(1f)
                .setDuration(300)
                .start();
    }

    public static void fadeInSlow(View view) {
        view.setAlpha(0f);
        view.animate()
                .alpha(1f)
                .setDuration(800)
                .start();
    }
}
