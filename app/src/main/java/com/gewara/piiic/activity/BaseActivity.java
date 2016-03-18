package com.gewara.piiic.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.gewara.piiic.R;
import com.gewara.piiic.dialogs.DialogManager;


/**
 * Created by user on 2016/1/25.
 */
public abstract class BaseActivity extends Activity implements DialogManager.FragmentManagerProvider {
    public void finish() {
        super.finish();
        overridePendingTransition(getFinishEnterAnimId(), getFinishExitAnimId());
    }

    protected abstract int getContentLayoutId();

    protected int getEnterAnimId() {
        return R.anim.push_right_in;
    }

    protected int getExitAnimId() {
        return R.anim.push_left_out;
    }

    protected int getFinishEnterAnimId() {
        return R.anim.push_left_in;
    }

    protected int getFinishExitAnimId() {
        return R.anim.push_right_out;
    }

    protected String getStaticsName() {
        return null;
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(getContentLayoutId());
//        if (canReceiveEvents())
//            EventBus.register(this);
    }

    protected void onDestroy() {
        super.onDestroy();
//        if (canReceiveEvents())
//            EventBus.unregister(this);
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onPostResume() {
        super.onPostResume();
    }

    protected void onResume() {
        super.onResume();
    }

    public FragmentManager provideFragmentManager() {
        return getFragmentManager();
    }

    public void showToast(int paramInt) {
        showToast(paramInt);
    }

    public void showToast(String paramString) {
        Toast localToast = Toast.makeText(this, paramString, Toast.LENGTH_SHORT);
        localToast.setGravity(17, 0, 0);
        localToast.show();
    }

    public void startActivity(Intent paramIntent) {
        super.startActivity(paramIntent);
        overridePendingTransition(getEnterAnimId(), getExitAnimId());
    }

    public final void startActivityAndFinishSelf(Intent paramIntent) {
        startActivity(paramIntent);
        super.finish();
    }

    public void startActivityForResult(Intent paramIntent, int paramInt) {
        super.startActivityForResult(paramIntent, paramInt);
        overridePendingTransition(getEnterAnimId(), getExitAnimId());
    }

    public final void startActivityForResultAndFinishSelf(Intent paramIntent, int paramInt) {
        startActivityForResult(paramIntent, paramInt);
        super.finish();
    }

}
