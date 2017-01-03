package com.example.administrator.imageload.tool;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/1/3.
 */

public interface IImageLoad {
    void showBitmapFromNet(String url);

    void showImage(String url, ImageView imageView);

    Bitmap getBitmapFromCache(String url);

    void saveToCache(String url,Bitmap bitmap);
}
