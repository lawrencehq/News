package com.hq.news.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Some input check functions.
 * @author hq
 * @date 21/11/2015
 * @since 1.0
 */
public class InputCheck {

    public static boolean checkEmail(String email) {
        String check = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }
}
