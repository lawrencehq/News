package com.hq.news.utils;

import android.content.Context;

import com.hq.news.bean.User;

import org.kymjs.kjframe.KJDB;
import org.kymjs.kjframe.utils.SystemTool;

import java.util.List;

/**
 * Some helper functions.
 * @author hq
 * @date 21/11/2015
 * @since 1.0
 */
public class UIHelper {
    private static User user = null;

    public static void saveUser(Context ctx, User u) {
        KJDB kjdb = KJDB.create(ctx);
        kjdb.deleteById(User.class, "");
        user = u;
        kjdb.save(user);
    }

    public static User getUser(Context ctx) {
        if (user != null) {
            return user;
        }

        KJDB kjdb = KJDB.create(ctx);
        List<User> datas = kjdb.findAll(User.class);

        if (datas != null && datas.size() > 0) {
            user = datas.get(0);
        } else {
            user = new User();
            user.setUserId(1);
            user.setEmail("enthalqy@gmail.com");
            user.setPassword("123456");
            user.setUsername("Lawrence");
            user.setCreateDate(SystemTool.getDataTime("yyyy-MM-dd HH:mm:SS"));
            kjdb.save(user);
        }
        return user;
    }
}
