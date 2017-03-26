package com.cj.cometogether.TransferDataClass;

import java.io.Serializable;

/**
 * 用于与服务器通讯的数据类
 * Created by pc on 2017/3/24.
 */

public class ThingClass implements Serializable
{
    private static final long serialVersionUID = 1L;

    private double latitude;

    private double longitude;

    private byte[] bitmap;

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public byte[] getBitmap()
    {
        return bitmap;
    }

    public void setBitmap(byte[] bitmap)
    {
        this.bitmap = bitmap;
    }
}
