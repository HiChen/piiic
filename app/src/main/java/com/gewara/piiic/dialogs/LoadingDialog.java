package com.gewara.piiic.dialogs;

/**
 * Created by user on 2016/1/27.
 */

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gewara.piiic.R;


public class LoadingDialog extends BaseDialog
{
    private static final String KEY_TEXT = "LoadingDialog.text";
    private static final int WHAT_DISMISS = 1;
    private Handler mHandler = new Handler(Looper.getMainLooper())
    {
        public void handleMessage(Message paramAnonymousMessage)
        {
            super.handleMessage(paramAnonymousMessage);
            if (paramAnonymousMessage.what == 1)
                LoadingDialog.this.setCancelable(true);
        }
    };
    private int mTextResId;

    public static void dismiss(Object paramObject)
    {
        DialogManager.getInstance().dismiss(paramObject);
    }

    private static LoadingDialog newInstance(int paramInt)
    {
        LoadingDialog localLoadingDialog = new LoadingDialog();
        Bundle localBundle = new Bundle(1);
        localBundle.putInt("LoadingDialog.text", paramInt);
        localLoadingDialog.setArguments(localBundle);
        return localLoadingDialog;
    }

    public static void show(int paramInt, Object paramObject)
    {
        LoadingDialog localLoadingDialog = newInstance(paramInt);
        localLoadingDialog.setDialogTag(paramObject);
        DialogManager.getInstance().show(localLoadingDialog);
    }

    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        this.mTextResId = getArguments().getInt("LoadingDialog.text", R.string.loading);
        setCancelable(false);
        this.mHandler.sendEmptyMessageDelayed(1, 5000L);
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        return paramLayoutInflater.inflate(R.layout.dialog_loading, paramViewGroup, false);
    }

    protected void onDialogDismiss(DialogInterface paramDialogInterface)
    {
        super.onDialogDismiss(paramDialogInterface);
        this.mHandler.removeMessages(1);
    }

    public void onViewCreated(View paramView, Bundle paramBundle)
    {
        super.onViewCreated(paramView, paramBundle);
        ((TextView)paramView.findViewById(R.id.dialog_loading_text)).setText(this.mTextResId);
    }
}
