package com.example.administrator.imageload.tool.policy;

import android.graphics.Bitmap;

import com.example.administrator.imageload.tool.DiskCache;
import com.example.administrator.imageload.tool.HttpConnect;
import com.example.administrator.imageload.tool.IHttpConnect;
import com.example.administrator.imageload.tool.IImageCache;
import com.example.administrator.imageload.tool.MemoryCache;

/**
 * auther：wzy
 * date：2017/1/15 19 :21
 * desc:
 */

public class LoadPolicy implements ILoadPolicy {
    private IImageCache mSecondCache = new DiskCache();
    private IImageCache mFirstCache = MemoryCache.getInstance();
    @Override
    public Bitmap fetchPic(String url) {
        Bitmap bitmap = mFirstCache.get(url);
        if (bitmap == null) {
            mSecondCache.get(url);
            if (bitmap != null) {
                mFirstCache.save(url, bitmap);
            }
        }
        return bitmap;
    }
}
