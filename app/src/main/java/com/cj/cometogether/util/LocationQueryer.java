package com.cj.cometogether.util;

import android.database.Observable;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 用于查询位置的工具类
 * Created by pc on 2017/3/2.
 */

public class LocationQueryer extends java.util.Observable
{
    private static LocationQueryer singleLocationQueryer;

    private  LocationClient mLocationClient;

    private  LocationClientOption mLocationClientOption;
    //区县
    private  String mDistrict;
    //街道
    private  String mStreet;
    //经度
    private  double mLongitude;
    //纬度
    private  double mLatitude;

    public String getmDistrict()
    {
        return mDistrict;
    }

    public String getmStreet()
    {
        return mStreet;
    }

    public double getmLongitude()
    {
        return mLongitude;
    }

    public double getmLatitude()
    {
        return mLatitude;
    }

    private LocationQueryer()
    {
        //定制定位的option
        mLocationClient = new LocationClient(GlobalPara.getContext());
        mLocationClientOption = new LocationClientOption();
        mLocationClientOption.setCoorType("bd09ll");
        mLocationClientOption.setScanSpan(600000);
        mLocationClientOption.setIsNeedAddress(true);
        mLocationClient.setLocOption(mLocationClientOption);

        mLocationClient.registerLocationListener(new BDLocationListener()
        {
            @Override
            public void onReceiveLocation(BDLocation bdLocation)
            {
                //拿经纬度
                mLatitude = bdLocation.getLatitude();
                mLongitude = bdLocation.getLongitude();

                //拿位置信息
                mDistrict = bdLocation.getDistrict();
                mStreet = bdLocation.getStreet();

                //通知观察者数据改变
                setChanged();
                notifyObservers();
            }

            @Override
            public void onConnectHotSpotMessage(String s, int i)
            {

            }
        });

        StartAlarmLocate();
    }

    public static LocationQueryer GetIstance()
    {
        if(singleLocationQueryer == null)
        {
            singleLocationQueryer = new LocationQueryer();
        }

        return singleLocationQueryer;
    }

    public void StartAlarmLocate()
    {
        mLocationClient.start();
    }

    public void GetCurrentLocation()
    {
        mLocationClient.requestLocation();
    }

    public void StopLocate()
    {
        mLocationClient.stop();
    }

    public String GetAddress()
    {
        return "#" + mDistrict + mStreet;
    }
}
