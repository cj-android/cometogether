package com.cj.cometogether.Service;

import android.app.Service;
import android.content.Intent;
import android.location.LocationProvider;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.cj.cometogether.util.LocationQueryer;
import com.cj.cometogether.util.NetworkTransmitter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

/**
 * 位置更新服务
 * Created by pc on 2017/3/20.
 */

public class UpdateLocationService extends Service implements Observer
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        LocationQueryer.GetIstance().addObserver(this);
    }

    public UpdateLocationService()
    {
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void update(Observable o, Object arg)
    {
        //实时向服务器通知位置信息
        //NetworkTransmitter.UpdateMyLocationToServer();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        LocationQueryer.GetIstance().deleteObserver(this);
    }
}
