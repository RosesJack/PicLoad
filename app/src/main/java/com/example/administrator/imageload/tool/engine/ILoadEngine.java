package com.example.administrator.imageload.tool.engine;

import android.graphics.Bitmap;

import com.example.administrator.imageload.tool.IHttpConnect;
import com.example.administrator.imageload.tool.policy.ICachePolicy;
import com.example.administrator.imageload.tool.policy.ILoadPolicy;

/**
 * auther：wzy
 * date：2017/1/15 18 :27
 * desc:
 */

public interface ILoadEngine extends Runnable {
    void setLoadPolicy(ILoadPolicy loadPolicy);

    void setCachePolicy(ICachePolicy cachePolicy);

    void caChePic(String url, Bitmap bitmap);

    Bitmap fetchPic(String url);

    void setBitmapIHttpConnect(IHttpConnect<Bitmap> bitmapIHttpConnect);
}
