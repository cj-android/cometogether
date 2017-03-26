package com.cj.cometogether.util;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * 注入ApplicationContext等全局变量
 * Created by pc on 2017/3/14.
 */

public class GlobalPara extends Application
{
    //全局context
    private static Context context;

    //硬盘缓存位置
    public static String EXTERNAL_CACHE_DIR;

    public static boolean Init(Context applicationContext)
    {
        context = applicationContext;

        if(context ==null)
        {
            return false;
        }

        EXTERNAL_CACHE_DIR = context.getExternalCacheDir().getPath();

        return true;
    }

    public static Context getContext()
    {
        return context;
    }
}
