package com.gewara.piiic.widgets;

import android.graphics.Rect;
import android.widget.RelativeLayout;
import android.content.Context;
import android.util.AttributeSet;

import com.gewara.piiic.configs.L;


/**
 * Created by user on 2016/1/25.
 */
public class SoftKeyboardDetectRelativeLayout extends RelativeLayout {
    private static final String TAG = "SoftKeyboardDetect";
    private SoftKeyboardDetector mDetector;
    private int mMaxHeight;
    private int mMinHeight;

    public SoftKeyboardDetectRelativeLayout(Context paramContext) {
        super(paramContext);
    }

    public SoftKeyboardDetectRelativeLayout(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public SoftKeyboardDetectRelativeLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
        int i = 1;
        Object[] arrayOfObject1 = new Object[2];
        arrayOfObject1[0] = Integer.valueOf(paramInt4);
        arrayOfObject1[i] = Integer.valueOf(paramInt2);
        L.d("SoftKeyboardDetect", "OldHeight: %d, Height: %d", arrayOfObject1);
        if ((paramInt4 == 0) && (this.mMaxHeight == 0)) {
            this.mMaxHeight = paramInt2;
            Object[] arrayOfObject3 = new Object[i];
            arrayOfObject3[0] = Integer.valueOf(this.mMaxHeight);
            L.e("SoftKeyboardDetect", "mMaxHeight: %d", arrayOfObject3);
        }
        if ((paramInt4 == 0) || (paramInt2 == paramInt4)) {

            if ((paramInt4 == this.mMaxHeight) && (this.mMinHeight == 0)) {
                this.mMinHeight = paramInt2;
                Object[] arrayOfObject2 = new Object[i];
                arrayOfObject2[0] = Integer.valueOf(this.mMinHeight);
                L.e("SoftKeyboardDetect", "mMinHeight: %d", arrayOfObject2);
            }
        }
        if (this.mDetector == null) {
            return;
        }
        SoftKeyboardDetector localSoftKeyboardDetector = this.mDetector;
        int j = 0;
        if (paramInt4 > paramInt2) {
            if (i == 0)
                j = this.mMaxHeight;
        }
        for (int k = this.mMinHeight; ; k = this.mMaxHeight) {
            localSoftKeyboardDetector.handleKeyboardState(j, k);
            i = 0;
            j = this.mMaxHeight;
        }
    }


    public int getKeyboardHeight() {
        Rect r = new Rect();
        getWindowVisibleDisplayFrame(r);
        int visibleHeight = r.height();
        mMaxHeight = visibleHeight;
        return mMaxHeight;

    }

    public void setDetector(SoftKeyboardDetector paramSoftKeyboardDetector) {
        this.mDetector = paramSoftKeyboardDetector;
    }

    public static abstract interface SoftKeyboardDetector {
        public abstract void handleKeyboardState(int paramInt1, int paramInt2);
    }

}
