package com.example.administrator.imageload.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/1/3.
 */

public class ImageCacheControl implements IImageCacheControl {
    private IImageCache diskCache = new DiskCache();
    private IImageCache memoryCache = MemoryCache.getInstance();

    @Override
    public void saveBitmapToCache(String url, Bitmap bitmap) {
        memoryCache.save(url, bitmap);
        diskCache.save(url, bitmap);
    }

    @Override
    public void setContext(Context context) {
        memoryCache.setContext(context);
        diskCache.setContext(context);
    }

    @Override
    public Bitmap getBitmapFromCache(String url) {
        Bitmap bitmap = memoryCache.get(url);
        Log.i(TAG, "memorycache bitmap:" + bitmap);
        if (bitmap == null) {
            bitmap = diskCache.get(url);
            Log.i(TAG, "diskcache bitmap:" + bitmap);
            if (bitmap != null) {
                //Save to memory after read from disk
                memoryCache.save(url, bitmap);
            }
        }
        return bitmap;
    }
}
