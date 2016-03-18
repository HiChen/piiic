package com.gewara.piiic.models;

/**
 * Created by user on 2016/1/26.
 */

import java.util.HashMap;

public class FontStyle
        implements Comparable<FontStyle>
{
    public static final HashMap<String, FontStyle> STYLES = new HashMap(6);
    private final String mImage;
    private final int mIndex;
    private final String mName;
    private final String mValue;

    static
    {
        STYLES.put("p", new FontStyle("p", "nodeName='p';className=''", "select_class_2", 0));
        STYLES.put("blockquote,blockquote-column", new FontStyle("blockquote,blockquote-column", "nodeName='blockquote';className='blockquote-column'", "select_class_3", 1));
        STYLES.put("blockquote,blockquote-quotation", new FontStyle("blockquote,blockquote-quotation", "nodeName='blockquote';className='blockquote-quotation'", "select_class_4", 2));
        STYLES.put("ul", new FontStyle("ul", "nodeName='ul';className=''", "select_class_0", 3));
        STYLES.put("ol", new FontStyle("ol", "nodeName='ol';className=''", "select_class_1", 4));
        STYLES.put("h1", new FontStyle("h1", "nodeName='h1';className=''", "select_class_5", 5));
    }

    private FontStyle(String paramString1, String paramString2, String paramString3, int paramInt)
    {
        this.mName = paramString1;
        this.mValue = paramString2;
        this.mImage = paramString3;
        this.mIndex = paramInt;
    }

    public int compareTo(FontStyle paramFontStyle)
    {
        if (paramFontStyle == null)
            return -1;
            if (this.mIndex > paramFontStyle.mIndex)
                return 1;
        if (this.mIndex < paramFontStyle.mIndex)
            return 0;
        return -1;
    }

    public String getImage()
    {
        return this.mImage;
    }

    public int getIndex()
    {
        return this.mIndex;
    }

    public String getName()
    {
        return this.mName;
    }

    public String getValue()
    {
        return this.mValue;
    }
}
