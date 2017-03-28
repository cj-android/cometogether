package com.cj.cometogether.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.cj.cometogether.TransferDataClass.ThingClass;
import com.cj.cometogether.database.Thing;

import org.litepal.crud.DataSupport;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 异步工作站，提供异步操作的工具类
 * Created by pc on 2017/3/24.
 */

public class AsyncWorkstation
{
    //向硬盘写Bitmap
    public static void AddThingToDiskCache(final Thing thing, final Bitmap bitmap)
    {
        new Thread()
        {
            @Override
            public void run()
            {
                //保存图片
                File file = new File(GlobalPara.EXTERNAL_CACHE_DIR + "/" + DataSupport.findLast(Thing.class).getId() + ".jpg");
                if (file.exists())
                {
                    file.delete();
                }
                try
                {
                    FileOutputStream out = new FileOutputStream(file);
                    if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out))
                    {
                        out.flush();
                        out.close();
                    }
                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //从硬盘加载Bitmap
    public static void LoadBitmapFromDiskCacheToShow(final int thingId, final int reqSize, final ImageView imageView)
    {
        class MyAsyncTask extends AsyncTask<String , Void, Bitmap>
        {
            @Override
            protected Bitmap doInBackground(String... dir)
            {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(GlobalPara.EXTERNAL_CACHE_DIR + "/" + dir[0] + ".jpg", options);
                int sampleSize = 1;
                int width = options.outWidth;
                int height = options.outHeight;
                while (width > reqSize || height > reqSize)
                {
                    sampleSize *= 2;
                    width = options.outWidth / sampleSize;
                    height = options.outHeight / sampleSize;
                }
                options.inSampleSize = sampleSize;
                options.inJustDecodeBounds = false;
                return BitmapFactory.decodeFile(GlobalPara.EXTERNAL_CACHE_DIR + "/" + dir[0] + ".jpg", options);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap)
            {
                super.onPostExecute(bitmap);
                MemoryCache.addBitmap(thingId, reqSize, bitmap);
                imageView.setImageBitmap(bitmap);
            }
        }

        new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, String.valueOf(thingId));
    }

    //从网络获取Bitmap
    public static void LoadBitmapFromServerToShow(String key)
    {

    }

    //向服务器发送Thing即其Bitmap
    public static void TransferThingClassToServer(final Thing thing, final Bitmap bitmap)
    {
        new Thread()
        {
            @Override
            public void run()
            {
                ThingClass tc = new ThingClass();
                tc.setLatitude(thing.getLatitude());
                tc.setLongitude(thing.getLongtitude());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                tc.setBitmap(baos.toByteArray());
                Socket socket = null;
                while(socket == null)
                {
                    try
                    {
                        socket = new Socket("192.168.1.101", 8688);
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                        out.writeObject(tc);
                        out.flush();
                        socket.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    //向服务器提交更新的位置信息
    public static void UpdateMyLocationToServer(final Object object)
    {

    }
}
