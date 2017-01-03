package com.example.administrator.imageload.tool;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/1/3.
 */

public interface IImageCache {
    void save(String url, Bitmap bitmap);

    void setContext(Context context);

    Bitmap get(String url);
}
