package com.cj.cometogether.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.cj.cometogether.database.Thing;

/**
 * 硬盘缓存
 * Created by pc on 2017/3/24.
 */

public class DiskCache
{
    public static void AddThingToDiskCache(Thing thing, Bitmap bitmap)
    {
        AsyncWorkstation.AddThingToDiskCache(thing, bitmap);
    }

    public static void LoadBitmapToShow(int thingId, int reqSize, ImageView imageView)
    {
        AsyncWorkstation.LoadBitmapFromDiskCacheToShow(thingId, reqSize, imageView);
    }

}
