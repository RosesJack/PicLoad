package com.example.administrator.imageload.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;

import com.example.administrator.imageload.R;

/**
 * Created by Administrator on 2017/1/3.
 * //TODO handle pic size to fit ImageView, handle get pic cache in other thread
 */

public class ImageLoad implements IImageLoad {
    //Default http connection
    private IHttpConnect<Bitmap> iHttpConnect = new HttpConnect();
    //Default cache load
    private IImageCacheControl cacheControl = new ImageCacheControl();
    //Default Image Bitmap
    private Bitmap defaultBitmap;
    private ImageView imageView;
    private Context mContext;
    //Main Thread handler
    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            if (bitmap == null) {
                //TODO　show fail image when access internet failed
            } else {
                imageView.setImageBitmap(bitmap);
            }
        }
    };

    public ImageLoad() {
    }

    @Override
    public void showBitmapFromNet(final String url) {
        new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = iHttpConnect.get(url);
                Message message = Message.obtain();
                message.obj = bitmap;
                handler.sendMessage(message);
                if (!TextUtils.isEmpty(url) && bitmap != null) {
                    saveToCache(url, bitmap);
                }
            }
        }.start();
    }

    @Override
    public void showImage(String url, final ImageView imageView) {
        this.imageView = imageView;
        mContext = imageView.getContext();
        cacheControl.setContext(mContext);
        if (defaultBitmap == null) {
            defaultBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
        }
        imageView.setImageBitmap(defaultBitmap);
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null) {
            showBitmapFromNet(url);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public Bitmap getBitmapFromCache(String url) {
        return cacheControl.getBitmapFromCache(url);
    }

    @Override
    public void saveToCache(String url, Bitmap bitmap) {
        cacheControl.saveBitmapToCache(url, bitmap);
    }

    public void setiHttpConnect(IHttpConnect<Bitmap> iHttpConnect) {
        this.iHttpConnect = iHttpConnect;
    }

    public void setCacheControl(IImageCacheControl cacheControl) {
        this.cacheControl = cacheControl;
    }
}
