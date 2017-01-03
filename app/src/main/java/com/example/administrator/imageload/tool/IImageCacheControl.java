package com.example.administrator.imageload.tool;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/1/3.
 * Picture cache policy
 */

public interface IImageCacheControl {
    void saveBitmapToCache(String url, Bitmap bitmap);

    void setContext(Context context);

    Bitmap getBitmapFromCache(String url);
}
