package com.swinfo.weiquan.location;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by redbird on 2017/5/27.
 */

public class LocationManager {
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    // 定位结果监听回调
    private OnLocateResultListener onLocateResultListener;

    public LocationManager(Context context) {
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        /**
         * 初始化配置
         */
        initLocationOption();
    }

    /**
     * 进行定位
     */
    public void locate(OnLocateResultListener onLocateResultListener) {
        // 设置回调对象
        this.onLocateResultListener = onLocateResultListener;
        //启动定位
        mLocationClient.startLocation();
    }

    /**
     * 初始化配置选项
     */
    private void initLocationOption() {
        //初始化AMapLocationClientOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        /**
         * 精度和耗电模式（低功耗）
         */
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        //mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        //设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式。
        //mLocationOption.setLocationMode(AMapLocationMode.Device_Sensors);
        mLocationOption.setLocationMode(AMapLocationMode.Battery_Saving);
        /**
         * 单次或连续定位（单次定位）
         */
        //获取一次定位结果：
        //该方法默认为false。
        //mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。(连续定位)
        //mLocationOption.setInterval(1000);

        /**
         * 是否返回地址信息
         */
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);

        /**
         * 设置是否强制刷新WIFI，默认为强制刷新。
         * 每次定位主动刷新WIFI模块会提升WIFI定位精度，
         * 但相应的会多付出一些电量消耗。
         */
        //设置是否强制刷新WIFI，默认为true，强制刷新。
        mLocationOption.setWifiActiveScan(true);

        /**
         * 设置定位请求超时时间，默认为30秒。
         */
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        //mLocationOption.setHttpTimeOut(30000);

        //开启缓存机制
        //mLocationOption.setLocationCacheEnable(false);

        // 初始化监听器
        AMapLocationListener mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                onLocateResultListener.onLocateResult(amapLocation);
            }
        };

        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        // 设置配置
        mLocationClient.setLocationOption(mLocationOption);
    }

    /**
     * 定位结果回调
     */
    public interface OnLocateResultListener{
        public void onLocateResult(AMapLocation amapLocation);
    }
}
