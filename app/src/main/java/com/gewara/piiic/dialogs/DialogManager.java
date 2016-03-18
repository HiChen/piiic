package com.gewara.piiic.dialogs;

import android.app.FragmentManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 2016/1/25.
 */
public class DialogManager {
    private final LinkedList<BaseDialog> mDialogs = new LinkedList();
    private final List<FragmentManagerProvider> mProviders = new ArrayList();
    private final Set<BaseDialog> mShowingDialogs = new HashSet();

    public static DialogManager getInstance() {
        return DialogManagerHolder.INSTANCE;
    }

    private void show() {
        show(false);
    }

    private void show(boolean paramBoolean) {
        if ((this.mProviders.isEmpty()) || ((!paramBoolean) && (!this.mShowingDialogs.isEmpty()))) ;
        BaseDialog localBaseDialog = (BaseDialog) this.mDialogs.poll();
        if (localBaseDialog == null) {
            return;
        }
        localBaseDialog.show(((FragmentManagerProvider) this.mProviders.get(-1 + this.mProviders.size())).provideFragmentManager(), null);
        this.mShowingDialogs.add(localBaseDialog);
    }

    void dismiss(Object paramObject) {
        if ((paramObject == null) || (this.mProviders.isEmpty())) {
            return;
        }
        Iterator localIterator = this.mShowingDialogs.iterator();
        ;
        while (!localIterator.hasNext()) {
            BaseDialog localBaseDialog = (BaseDialog) localIterator.next();
            if ((localBaseDialog == null) || (!localBaseDialog.dialogTagEquals(paramObject))) ;
            localBaseDialog.dismissAllowingStateLoss();
        }
    }

    void onDialogDismiss(BaseDialog paramBaseDialog) {
        if (!this.mShowingDialogs.remove(paramBaseDialog))
            return;
        show();
    }

    public void registerProvider(FragmentManagerProvider paramFragmentManagerProvider) {
        if (paramFragmentManagerProvider == null)
            return;
        this.mProviders.remove(paramFragmentManagerProvider);
        this.mProviders.add(paramFragmentManagerProvider);
        show();
    }

    void show(BaseDialog paramBaseDialog) {
        show(paramBaseDialog, false);
    }

    void show(BaseDialog paramBaseDialog, boolean paramBoolean) {
        show(null, paramBaseDialog, paramBoolean);
    }

    void show(Object paramObject, BaseDialog paramBaseDialog, boolean paramBoolean) {
        if (this.mDialogs.contains(paramBaseDialog))
            return;
        if (paramBoolean)
            this.mDialogs.addFirst(paramBaseDialog);
        show(paramBoolean);
        this.mDialogs.addLast(paramBaseDialog);
    }

    public void unregisterProvider(FragmentManagerProvider paramFragmentManagerProvider) {
        if (paramFragmentManagerProvider == null)
            return;
        this.mProviders.remove(paramFragmentManagerProvider);
    }

    private static final class DialogManagerHolder {
        private static DialogManager INSTANCE = new DialogManager();
    }

    public static abstract interface FragmentManagerProvider {
        public abstract FragmentManager provideFragmentManager();
    }
}
