package com.hq.news.network;

import org.json.JSONException;

/**
 * Http request listener interface.
 * @author hq
 * @date 21/11/2015
 * @since 1.0
 */
public interface HttpTaskListener {
    void onSuccess(String content) throws JSONException;
    void onFaileure(String content);
}
