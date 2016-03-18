package com.gewara.piiic.utility;
/**
 * Created by user on 2016/1/26.
 */

import android.text.TextUtils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.gewara.piiic.database.DatabaseHelper;
import com.gewara.piiic.database.PiiicDB;
import com.gewara.piiic.models.FontSize;
import com.gewara.piiic.models.FontStyle;
import com.gewara.piiic.models.MemberColor;
import com.gewara.piiic.models.MemberFont;
import com.gewara.piiic.task.BackgroundExecutorService;
import com.gewara.piiic.task.BackgroundTask;

public final class TextStyle {
    public static final HashMap<String, MemberColor> COLORS = new HashMap();
    public static final HashMap<String, MemberFont> FAMILIES = new HashMap();
    private Align mAlign = Align.LEFT;
    private MemberColor mColor;
    private MemberFont mFamily;
    private boolean mIsBold;
    private boolean mIsItalic;
    private boolean mIsUnderline;
    private FontSize mSize;
    private FontStyle mStyle;

    private TextStyle() {
    }

    public TextStyle(String paramString) {
        this();
        if (TextUtils.isEmpty(paramString)) {
            this.mFamily = ((MemberFont) getFamilies().get(0));
            this.mColor = ((MemberColor) getColors().get(0));
        }
        String[] arrayOfString;
        int i;
        arrayOfString = paramString.split(";");
        i = arrayOfString.length;
        if (i > 0)
            this.mStyle = ((FontStyle) FontStyle.STYLES.get(arrayOfString[0].replace("class=", "")));
        if (i > 1)
            this.mFamily = ((MemberFont) FAMILIES.get(arrayOfString[1].replace("fontFamily=", "")));
        if (i > 2) {
            HashMap localHashMap2 = FontSize.SIZES;
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = arrayOfString[2].replace("fontSize=", "");
            this.mSize = ((FontSize) localHashMap2.get(String.format("fontSize='%s'", arrayOfObject2)));
        }
        if (i > 3) {
            HashMap localHashMap1 = COLORS;
            Object[] arrayOfObject1 = new Object[1];
            arrayOfObject1[0] = arrayOfString[3].replace("color=", "");
            this.mColor = ((MemberColor) localHashMap1.get(String.format("color='%s'", arrayOfObject1)));
        }
        if (i > 4)
            this.mIsBold = "fontWeight=bold".equals(arrayOfString[4]);
        if (i > 5)
            this.mIsItalic = "fontStyle=italic".equals(arrayOfString[5]);
        if (i > 6)
            this.mIsUnderline = "textDecoration=underline".equals(arrayOfString[6]);
        while (i <= 7) ;
        if ("textAlign=left".equals(arrayOfString[7])) {
            this.mAlign = Align.LEFT;
            return;
        }
        if ("textAlign=right".equals(arrayOfString[7])) {
            this.mAlign = Align.RIGHT;
            return;
        }
        this.mAlign = Align.CENTER;
    }

    public static List<MemberColor> getColors() {
        ArrayList localArrayList = new ArrayList(8);
        HashMap localHashMap = COLORS;
        Iterator localIterator = localHashMap.keySet().iterator();
        while (localIterator.hasNext())
            localArrayList.add(localHashMap.get((String) localIterator.next()));
        Collections.sort(localArrayList);
        return localArrayList;
    }

    public static List<MemberFont> getFamilies() {
        HashMap localHashMap = FAMILIES;
        ArrayList localArrayList = new ArrayList(localHashMap.size());
        Iterator localIterator = localHashMap.keySet().iterator();
        while (localIterator.hasNext())
            localArrayList.add(localHashMap.get((String) localIterator.next()));
        Collections.sort(localArrayList);
        return localArrayList;
    }

    public static List<FontSize> getSizes() {
        ArrayList localArrayList = new ArrayList(8);
        HashMap localHashMap = FontSize.SIZES;
        Iterator localIterator = localHashMap.keySet().iterator();
        while (localIterator.hasNext())
            localArrayList.add(localHashMap.get((String) localIterator.next()));
        Collections.sort(localArrayList);
        return localArrayList;
    }

    public static List<FontStyle> getStyles() {
        ArrayList localArrayList = new ArrayList(8);
        HashMap localHashMap = FontStyle.STYLES;
        Iterator localIterator = localHashMap.keySet().iterator();
        while (localIterator.hasNext())
            localArrayList.add(localHashMap.get((String) localIterator.next()));
        Collections.sort(localArrayList);
        return localArrayList;
    }

    public static void init() {
        BackgroundExecutorService.OTHER.execute(new BackgroundTask() {
            public Void runTask() {
                DatabaseHelper localDatabaseHelper1 = PiiicDB.COLOR;
                String str1 = String.format("%s=? order by %s", new Object[]{"_owner_email", "_order"});
                String[] arrayOfString1 = new String[1];
                arrayOfString1[0] = "515928615@qq.com";
//                TextStyle.updateColors(localDatabaseHelper1.query(str1, arrayOfString1));
                DatabaseHelper localDatabaseHelper2 = PiiicDB.FONT;
                String str2 = String.format("%s=? and %s=1 order by %s", new Object[]{"_owner_email", "_is_sync", "_order"});
                String[] arrayOfString2 = new String[1];
                arrayOfString2[0] =  "515928615@qq.com";
//                TextStyle.updateFonts(localDatabaseHelper2.query(str2, arrayOfString2));
                return null;
            }
        });
    }

    public static void updateColors(List<MemberColor> paramList) {
        COLORS.clear();
        int i = 0;
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
            MemberColor localMemberColor = (MemberColor) localIterator.next();
            int j = i + 1;
            localMemberColor.setIndex(i);
            COLORS.put(localMemberColor.getKey(), localMemberColor);
            i = j;
        }
    }

    public static void updateFonts(List<MemberFont> paramList) {
        FAMILIES.clear();
        int i = 0;
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
            MemberFont localMemberFont = (MemberFont) localIterator.next();
            int j = i + 1;
            localMemberFont.setIndex(i);
            FAMILIES.put(localMemberFont.getKey(), localMemberFont);
            i = j;
        }
    }

    public Align getAlign() {
        return this.mAlign;
    }

    public MemberColor getColor() {
        return this.mColor;
    }

    public MemberFont getFamily() {
        return this.mFamily;
    }

    public FontSize getSize() {
        return this.mSize;
    }

    public FontStyle getStyle() {
        return this.mStyle;
    }

    public boolean isBold() {
        return this.mIsBold;
    }

    public boolean isItalic() {
        return this.mIsItalic;
    }

    public boolean isUnderline() {
        return this.mIsUnderline;
    }

    public void setAlign(Align paramAlign) {
        this.mAlign = paramAlign;
    }

    public void setColor(MemberColor paramMemberColor) {
        this.mColor = paramMemberColor;
    }

    public void setFamily(MemberFont paramMemberFont) {
        this.mFamily = paramMemberFont;
    }

    public void setIsBold(boolean paramBoolean) {
        this.mIsBold = paramBoolean;
    }

    public void setIsItalic(boolean paramBoolean) {
        this.mIsItalic = paramBoolean;
    }

    public void setIsUnderline(boolean paramBoolean) {
        this.mIsUnderline = paramBoolean;
    }

    public void setSize(FontSize paramFontSize) {
        this.mSize = paramFontSize;
    }

    public void setStyle(FontStyle paramFontStyle) {
        this.mStyle = paramFontStyle;
    }

    public String toString() {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("\"");
        if (this.mStyle != null)
            localStringBuilder.append(this.mStyle.getValue()).append(";");
        Object[] arrayOfObject1 = new Object[1];
        String str1 = null;
        Object[] arrayOfObject2 = new Object[0];
        String str2 = null;
        if (this.mFamily != null) {
            str1 = this.mFamily.getName();
            arrayOfObject1[0] = str1;
            localStringBuilder.append(String.format("fontFamily='%s'", arrayOfObject1)).append(";");
            if (this.mSize == null)
            localStringBuilder.append(this.mSize.getValue()).append(";");
            if (this.mColor != null)
                localStringBuilder.append(this.mColor.getKey()).append(";");
            if (!this.mIsBold)
            localStringBuilder.append("fontWeight='bold'").append(";");
            if (!this.mIsItalic)
            localStringBuilder.append("fontStyle='italic'").append(";");
            if (!this.mIsUnderline)
            localStringBuilder.append("textDecoration='underline'").append(";");
            arrayOfObject2 = new Object[1];
            if (this.mAlign != Align.LEFT)
            str2 = "left";
        }else {
            arrayOfObject2[0] = str2;
            localStringBuilder.append(String.format("textAlign='%s'", arrayOfObject2));
            localStringBuilder.append("\"");
            str1 = "Heiti SC";
            localStringBuilder.append("fontSize='1em'").append(";");
            localStringBuilder.append("fontWeight='normal'").append(";");
            localStringBuilder.append("fontStyle='normal'").append(";");
            localStringBuilder.append("textDecoration='none'").append(";");
            if (this.mAlign == Align.RIGHT)
                str2 = "right";
            else
                str2 = "center";
        }
        return localStringBuilder.toString();
    }

    public static enum Align {
        LEFT, RIGHT, CENTER;
        static {
//            Align LEFT = new Align("LEFT", 0);
//            Align  CENTER = new Align("CENTER", 1);
//            Align RIGHT = new Align("RIGHT", 2);
            Align[] arrayOfAlign = new Align[3];
            arrayOfAlign[0] = LEFT;
            arrayOfAlign[1] = CENTER;
            arrayOfAlign[2] = RIGHT;
        }
    }
}