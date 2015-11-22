package com.hq.news.network.api;

import com.hq.news.network.HttpRequest;
import com.hq.news.network.HttpTaskListener;

import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.http.HttpParams;

/**
 * User related interfaces.
 * @author hq
 * @date 21/11/2015
 * @since 1.0
 */
public class UserApi {


    public static void signUp(String email, String password, String username, HttpTaskListener listener) {
        HttpConfig config = new HttpConfig();
        config.cacheTime = 0;
        HttpParams params = new HttpParams();
        params.put("action", "signup");
        params.put("email", email);
        params.put("pass", password);
        params.put("username", username);

        HttpRequest.execute(config, params, listener);
    }

    public static void login(String email, String password, HttpTaskListener listener) {
        HttpConfig config = new HttpConfig();
        config.cacheTime = 0;
        HttpParams params = new HttpParams();
        params.put("action", "login");
        params.put("email", email);
        params.put("pass", password);

        HttpRequest.execute(config, params, listener);
    }
}
