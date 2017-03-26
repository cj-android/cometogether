package com.cj.cometogether.util;

/**
 * 数据管理类，对数据在内存、硬盘的存储和提取进行管理
 * Created by pc on 2017/3/8.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.cj.cometogether.TransferDataClass.ThingClass;
import com.cj.cometogether.database.*;

import org.litepal.crud.DataSupport;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class DataManager
{
    public static List<Thing> ThingList = new ArrayList();

    public static void InitDatabaseUtil()
    {
        ThingList = DataSupport.findAll(Thing.class);
    }

    public static void AddThing(Thing thing, Bitmap bitmap)
    {
        //写数据内存
        ThingList.add(thing);

        //写内存bitmap缓存
        MemoryCache.addBitmap(thing, bitmap);

        //写硬盘缓存-写数据库+图片
        DiskCache.AddThingToDiskCache(thing, bitmap);

        //传输至服务器，其他用户发布的Thing不需要想服务器发送，故这里需要同服务器配合处理
        //NetworkTransmitter.TransferThingClassToServer(thing, bitmap);
    }
}
