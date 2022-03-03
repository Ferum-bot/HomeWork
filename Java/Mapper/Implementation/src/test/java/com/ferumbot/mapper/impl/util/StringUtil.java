package com.ferumbot.mapper.impl.util;

import static java.lang.Math.min;

public class StringUtil {

    public static Integer countEntries(String pattern, String content) {
        var answer = 0;
        var contentLength = content.length();

        for (int i = 0; i < contentLength; i++) {
            var endIndex = min(i + pattern.length(), contentLength);
            var substring = content.substring(i, endIndex);
            if (substring.equals(pattern)) {
                answer++;
            }
        }

        return answer;
    }
}
