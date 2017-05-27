package com.swinfo.weiquan.mian;


import android.util.Log;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.swinfo.weiquan.location.LocationManager;

/**
 * Created by redbird on 2017/5/27.
 */

public class MainOnLocateResultListener implements LocationManager.OnLocateResultListener {

    private TextView tvLocation;

    public MainOnLocateResultListener(TextView tvLocation) {
        this.tvLocation = tvLocation;
    }


    @Override
    public void onLocateResult(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //可在其中解析amapLocation获取相应内容。
                // 定位
                tvLocation.setText(amapLocation.getDistrict());
                //Toast.makeText(context, "type:" + amapLocation.getLocationType()
                //        + " " + amapLocation.getDistrict(),Toast.LENGTH_LONG).show();
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }
}
