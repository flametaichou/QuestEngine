package ru.flametaichou.quest.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class QuestStringUtils {

    public static String formatUniqueCodeString(String rawString) {
        if (Objects.isNull(rawString)) {
            return null;
        }
        String result = "";
        rawString = rawString.toLowerCase();
        for (char ch : rawString.toCharArray()) {
            if (isLatinLetter(ch) || isNumber(ch) || ch == '-') {
                result = result + ch;
            }
        }
        return result;
    }

    public static boolean isLatinLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public static boolean isNumber(char c) {
        return Character.isDigit(c);
    }
}
