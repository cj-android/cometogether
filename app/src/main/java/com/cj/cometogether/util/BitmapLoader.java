package com.cj.cometogether.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * 加载Bitmap的工具类
 * Created by pc on 2017/3/7.
 */

public class BitmapLoader
{
    public final static int MIN = 128;
    public final static int MAX = 512;

    //拿Bitmap的数据来显示，这里应该把任务交给DataManager更合理，DataManager的存取工作就都有了
    public static void LoadBitmapToShow(int thingId, int reqSize, ImageView imageView)
    {
        Bitmap bitmap;

        //先从内存缓存中取
        bitmap = MemoryCache.getBitmap(thingId, reqSize);
        if(bitmap != null)
        {
            imageView.setImageBitmap(bitmap);
            return;
        }

        //从硬盘加载
        //if(DiskCache.hasBitmap(key))
        //{
            DiskCache.LoadBitmapToShow(thingId, reqSize, imageView);
            //return;
        //}

        //从服务器获取
        //NetworkTransmitter.LoadBitmapToShow(key);
    }
}
