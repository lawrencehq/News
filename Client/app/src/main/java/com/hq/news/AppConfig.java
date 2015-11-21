package com.hq.news;

/**
 * The constant values of app.
 * @author hq
 * @date 20/11/2015
 * @since 1.0
 */
public class AppConfig {
    public static final String SERVER_URL = "http://192.168.23.1/news/rest.php";

    public static final String saveFolder = "News";
    public static final String httpCachePath = saveFolder + "/httpCache";
    public static final String imgCachePath = saveFolder + "/imageCache";
    public static final String audioPath = saveFolder + "/audio";

    public static final String CACHE_TIME_KEY = "cache_time_key";

    public static final String SPLASH_HEAD_IMG_KEY = "headimage_key";
    public static final String SPLASH_BACKGROUND_KEY = "main_background_key";
    public static final String SPLASH_BOX_KEY = "main_box_key";
    public static final String SPLASH_CONTENT_KEY = "main_content_key";

    public static final String PUSH_SWITCH_FILE = "push_switch_file";
    public static final String PUSH_SWITCH_KEY = "push_switch_key";

    public static final String PUSH_BROADCAST_ACTION = "org.hq.news.hqpush_has_new_message";
}
