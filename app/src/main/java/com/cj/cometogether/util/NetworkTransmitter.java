package com.cj.cometogether.util;

import android.graphics.Bitmap;
import android.util.Log;

import com.cj.cometogether.database.Thing;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 网络操作的工具类，由于均为异步操作，故具体工作在AsyncWorkstation中实现
 * Created by pc on 2017/3/23.
 */

public class NetworkTransmitter
{
    public static void TransferThingClassToServer(Thing thing, Bitmap bitmap)
    {
        AsyncWorkstation.TransferThingClassToServer(thing, bitmap);
    }
    public static void UpdateMyLocationToServer(Object object)
    {
        AsyncWorkstation.UpdateMyLocationToServer(object);
    }

    public static void LoadBitmapToShow(String key)
    {
        AsyncWorkstation.LoadBitmapFromServerToShow(key);
    }
}
