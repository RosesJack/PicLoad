package com.example.administrator.imageload.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.LruCache;

import com.example.administrator.imageload.tool.util.MD5Util;

/**
 * Created by Administrator on 2017/1/3.
 */

public class MemoryCache implements IImageCache {
    private int needSize;
    private LruCache<String, Bitmap> lruCache;

    public MemoryCache() {
        if (needSize == 0) {
            needSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        }
        if (lruCache == null) {
            lruCache = new LruCache<String, Bitmap>(needSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight() / 1024;
                }
            };
        }
    }

    @Override
    public void save(String url, Bitmap bitmap) {
        String key = MD5Util.String2MD5(url);
        if (TextUtils.isEmpty(url) || bitmap == null) {
            return;
        }
        lruCache.put(key, bitmap);
    }

    @Override
    public void setContext(Context context) {
    }

    @Override
    public Bitmap get(String url) {
        String key = MD5Util.String2MD5(url);
        return lruCache.get(key);
    }
}
