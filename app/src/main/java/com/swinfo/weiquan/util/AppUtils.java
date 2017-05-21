package com.swinfo.weiquan.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by redbird on 2017/5/20.
 */

public class AppUtils {

    public static void startActivity(Context context, Class toActivity) {
        Intent intent = new Intent();
        intent.setClass(context, toActivity);
        context.startActivity(intent);
    }
}
