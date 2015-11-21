package com.hq.news.activity;

import android.content.Intent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.hq.news.AppContext;
import com.hq.news.R;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.utils.DensityUtils;
import org.kymjs.kjframe.utils.KJLoger;
import org.kymjs.kjframe.utils.PreferenceHelper;

/**
 * The start view of app.
 * @author hq
 * @date 20/11/2015
 */
public class StartActivity extends KJActivity {
    public static String TAG = "appstart";

    @Override
    public void setRootView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageView image = new ImageView(aty);
        image.setImageResource(R.drawable.splash_bg);
        Animation anim = AnimationUtils.loadAnimation(aty, R.anim.splash_start);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                jumpTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        image.setAnimation(anim);
        setContentView(image);
        AppContext.screenH = DensityUtils.getScreenH(aty);
        AppContext.screenW = DensityUtils.getScreenW(aty);
    }

    private void configPush() {}

    private void jumpTo() {
        configPush();
        boolean isFirst = PreferenceHelper.readBoolean(aty, TAG, "first_open", true);
        Intent jumpIntent = new Intent();
        if (!isFirst) {
            jumpIntent.setClass(aty, MainActivity.class);
        } else {
            PreferenceHelper.write(aty, TAG, "first_open", false);
            jumpIntent.setClass(aty, MainActivity.class);
        }
        startActivity(jumpIntent);
        finish();
    }
}
