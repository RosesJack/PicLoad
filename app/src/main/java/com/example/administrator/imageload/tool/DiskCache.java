package com.example.administrator.imageload.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.example.administrator.imageload.tool.util.MD5Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/1/3.
 */

public class DiskCache implements IImageCache {

    private File cacheFile;
    private File file;

    public DiskCache() {
    }

    @Override
    public void save(String url, Bitmap bitmap) {
        String key = MD5Util.String2MD5(url);
        try {
            //url maybe illegal as file name
            cacheFile = new File(file, key);
            OutputStream outputStream = new FileOutputStream(cacheFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setContext(Context context) {
        if (file == null) {
            file = context.getExternalCacheDir();
            Log.i(TAG, "path：" + file.getAbsolutePath());
        }
    }

    @Override
    public Bitmap get(String url) {
        String key = MD5Util.String2MD5(url);
        File file = new File(this.file, key);
        if (file.exists() && !file.isDirectory()) {
            try {
                InputStream inputStream = new FileInputStream(file);
                return BitmapFactory.decodeStream(inputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
