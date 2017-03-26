package com.cj.cometogether.util;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.cj.cometogether.database.Thing;

/**
 * 内存缓存
 * Created by pc on 2017/3/14.
 */

public class MemoryCache
{
    private static LruCache<String, Bitmap> bitmapCache;

    public static void Init()
    {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;

        bitmapCache = new LruCache<String, Bitmap>(cacheSize);
    }

    public static void addBitmap(int thingId, int reqSize, Bitmap bitmap)
    {
        if(reqSize <= BitmapLoader.MIN && bitmap != null)
        {
            bitmapCache.put(String.valueOf(thingId) + String.valueOf(reqSize), bitmap);
        }
    }

    public static void addBitmap(Thing thing, Bitmap bitmap)
    {
        String key = String.valueOf(thing.getId()) + String.valueOf(BitmapLoader.MIN);
        if(bitmap !=null)
        {
            bitmapCache.put(key, bitmap);
        }
    }

    public static Bitmap getBitmap(int thingId, int reqSize)
    {
        String key = String.valueOf(thingId) + String.valueOf(reqSize);
        return bitmapCache.get(key);
    }

}
