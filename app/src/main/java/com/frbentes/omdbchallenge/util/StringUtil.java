package com.frbentes.omdbchallenge.util;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;

/**
 * Created by frbentes on 13/01/17.
 */
public class StringUtil {

    private static final String BLANK = " ";

    public static SpannableString formatText(String label, String description) {
        SpannableString textResult = new SpannableString(label + BLANK + description);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        textResult.setSpan(boldSpan,
                0,
                String.valueOf(label).length() + 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return textResult;
    }

}
