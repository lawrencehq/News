package com.hq.news;

import android.app.Application;

import org.kymjs.kjframe.http.HttpConfig;

/**
 * The context of app.
 * @author hq
 * @date 20/11/2015
 * @since 1.0
 */
public class AppContext extends Application {
    public static int screenW;
    public static int screenH;

    @Override
    public void onCreate() {
        super.onCreate();
        HttpConfig.CACHEPATH = AppConfig.httpCachePath;
        CrashHandler.create(this);
    }
}
