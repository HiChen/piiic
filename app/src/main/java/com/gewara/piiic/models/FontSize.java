package com.gewara.piiic.models;

/**
 * Created by user on 2016/1/26.
 */

import java.util.HashMap;

public class FontSize
        implements Comparable<FontSize> {
    public static final HashMap<String, FontSize> SIZES = new HashMap(8);
    private final int mIndex;
    private final String mName;
    private final String mValue;

    static {
        SIZES.put("fontSize='1em'", new FontSize("16", "fontSize='1em'", 0));
        SIZES.put("fontSize='1.125em'", new FontSize("18", "fontSize='1.125em'", 1));
        SIZES.put("fontSize='1.375em'", new FontSize("22", "fontSize='1.375em'", 2));
        SIZES.put("fontSize='1.625em'", new FontSize("26", "fontSize='1.625em'", 3));
        SIZES.put("fontSize='2em'", new FontSize("32", "fontSize='2em'", 4));
        SIZES.put("fontSize='0.625em'", new FontSize("10", "fontSize='0.625em'", 5));
        SIZES.put("fontSize='0.75em'", new FontSize("12", "fontSize='0.75em'", 6));
        SIZES.put("fontSize='0.875em'", new FontSize("14", "fontSize='0.875em'", 7));
    }

    private FontSize(String paramString1, String paramString2, int paramInt) {
        this.mName = paramString1;
        this.mValue = paramString2;
        this.mIndex = paramInt;
    }

    public int compareTo(FontSize paramFontSize) {
        if (paramFontSize == null)
            return -1;
        if (this.mIndex > paramFontSize.mIndex) {
            return 1;
        }
        if (this.mIndex < paramFontSize.mIndex) {
            return 0;
        }
        return -1;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public String getName() {
        return this.mName;
    }

    public String getValue() {
        return this.mValue;
    }
}
