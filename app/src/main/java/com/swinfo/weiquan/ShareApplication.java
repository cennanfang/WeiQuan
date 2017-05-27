package com.swinfo.weiquan;

import android.app.Application;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by redbird on 2017/5/27.
 */

public class ShareApplication extends Application {
    private static final String APP_KEY = "1e3b7d0a422bc";

    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(this, APP_KEY);
    }
}
