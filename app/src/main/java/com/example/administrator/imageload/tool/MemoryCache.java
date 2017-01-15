package com.example.administrator.imageload.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.LruCache;

import com.example.administrator.imageload.tool.util.MD5Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */

public class MemoryCache implements IImageCache {
    private int needSize;
    private LruCache<String, Bitmap> lruCache;
    private static MemoryCache memoryCache;
    /**
     * Use for save key of bitmap
     */
    private volatile List<String> urlList = new ArrayList<>();

    private MemoryCache() {
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

    /**
     * single instance
     *
     * @return
     */
    public static MemoryCache getInstance() {
        if (memoryCache == null) {
            synchronized (MemoryCache.class) {
                if (memoryCache == null) {
                    memoryCache = new MemoryCache();
                }
            }
        }
        return memoryCache;
    }


    @Override
    public synchronized void save(String url, Bitmap bitmap) {
        if (urlList.contains(url)) {
            return;
        } else {
            urlList.add(url);
        }
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
