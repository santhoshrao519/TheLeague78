package com.app.theleague78.util;


import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.app.theleague78.R;
import com.kaopiz.kprogresshud.KProgressHUD;


public class LoadingBox {

   public static KProgressHUD hud;
    public static void showLoadingDialog(Context context) {
        if (isDialogShowing()) {
            dismissLoadingDialog();
        }


        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isFinishing()) {
                return;
            }
        }
if(context!=null) {
    hud= KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel("Please wait")
         //   .setDetailsLabel("Please Wait")
            .setCancellable(false)
            .setBackgroundColor(R.color.black)
            .setAnimationSpeed(2)
            .setDimAmount(0.6f)
            .show();
}
    }

    public static boolean isDialogShowing() {
        try {
            if (hud == null) {
                return false;
            } else {
                return hud.isShowing();
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Dismisses above loading dialog
     */
    public static void dismissLoadingDialog() {
        try {
            if (hud != null) {
                hud.dismiss();
                hud = null;
            }
        } catch (Exception e) {
            Log.e("e", "=" + e);
        }
    }

}
