package com.example.administrator.imageload.tool.policy;

import android.graphics.Bitmap;

import com.example.administrator.imageload.tool.DiskCache;
import com.example.administrator.imageload.tool.IImageCache;
import com.example.administrator.imageload.tool.MemoryCache;

/**
 * auther：wzy
 * date：2017/1/15 18 :31
 * desc:
 */

public class CachePolicy implements ICachePolicy {
    private IImageCache mSecondCache = new DiskCache();
    private IImageCache mFirstCache = MemoryCache.getInstance();

    @Override
    public void caChePic(String url, Bitmap bitmap) {
        mFirstCache.save(url, bitmap);
        mSecondCache.save(url, bitmap);
    }
}
