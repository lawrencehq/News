package com.hq.news.network;

import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.http.HttpParams;
import com.hq.news.AppConfig;

/**
 * Http request functions.
 * @author hq
 * @date 21/11/2015
 * @since 1.0
 */
public class HttpRequest {

    public static void execute(HttpConfig config, HttpParams params, final HttpTaskListener listener) {
        KJHttp kjHttp = new KJHttp(config);
        kjHttp.post(AppConfig.SERVER_URL, params, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                try {
                    if (t.charAt(0) != '{') {
                        listener.onFaileure(t);
                    } else {
                        JSONObject jsonRes = new JSONObject(t.substring(t.indexOf("{")));
                        boolean status = jsonRes.getBoolean("status");
                        if (status) {
                            listener.onSuccess(jsonRes.getString("data"));
                        } else {
                            listener.onFaileure(jsonRes.getString("errorInfo"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFaileure("Json object creation failure.");
                }

            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                listener.onFaileure(strMsg);
            }
        });
    }
}
