package com.gewara.piiic.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.webkit.WebView;

/**
 * Created by user on 2016/1/25.
 */
public class CustomWebView extends WebView {
    public OnKeyMultipleListener mListener;

    public CustomWebView(Context paramContext) {
        super(paramContext);
    }

    public CustomWebView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public CustomWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo) {
        return new CustomInputConnection(this, false);
    }

    public boolean onKeyMultiple(int paramInt1, int paramInt2, KeyEvent paramKeyEvent) {
        if (this.mListener != null)
            this.mListener.onKeyMultiple(this, paramKeyEvent.getCharacters());
        return true;
    }

    public void setOnKeyMultipleListener(OnKeyMultipleListener paramOnKeyMultipleListener) {
        this.mListener = paramOnKeyMultipleListener;
    }

    private static final class CustomInputConnection extends BaseInputConnection {
        private CustomInputConnection(View paramView, boolean paramBoolean) {
            super(paramView,paramBoolean);
        }

        public boolean deleteSurroundingText(int paramInt1, int paramInt2) {
            if ((paramInt1 == 1) && (paramInt2 == 0))
                return (super.sendKeyEvent(new KeyEvent(0, 67))) && (super.sendKeyEvent(new KeyEvent(1, 67)));
            return super.deleteSurroundingText(paramInt1, paramInt2);
        }
    }

    public static abstract interface OnKeyMultipleListener {
        public abstract void onKeyMultiple(WebView paramWebView, String paramString);
    }
}
