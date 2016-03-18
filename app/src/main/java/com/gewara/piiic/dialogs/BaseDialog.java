package com.gewara.piiic.dialogs;

import android.app.DialogFragment;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.gewara.piiic.R;


/**
 * Created by user on 2016/1/25.
 */
public class BaseDialog extends DialogFragment {

    private Object mDialogTag;

    protected void configDialog(Dialog paramDialog)
    {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        Window localWindow = paramDialog.getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        localLayoutParams.width = (localDisplayMetrics.widthPixels - getResources().getDimensionPixelSize(R.dimen.large_margin));
        localWindow.setAttributes(localLayoutParams);
    }

    public boolean dialogTagEquals(Object paramObject)
    {
        return (paramObject != null) && (this.mDialogTag != null) && (this.mDialogTag.equals(paramObject));
    }

    public Object getDialogTag()
    {
        return this.mDialogTag;
    }

    public void onActivityCreated(Bundle paramBundle)
    {
        super.onActivityCreated(paramBundle);
        configDialog(getDialog());
    }

    public Dialog onCreateDialog(Bundle paramBundle)
    {
        Dialog localDialog = super.onCreateDialog(paramBundle);
        localDialog.getWindow().requestFeature(1);
        return localDialog;
    }

    protected void onDialogDismiss(DialogInterface paramDialogInterface)
    {
        super.onDismiss(paramDialogInterface);
    }

    public final void onDismiss(DialogInterface paramDialogInterface)
    {
        onDialogDismiss(paramDialogInterface);
        com.gewara.piiic.dialogs.DialogManager.getInstance().onDialogDismiss(this);
    }

    public void setDialogTag(Object paramObject)
    {
        this.mDialogTag = paramObject;
    }

}
