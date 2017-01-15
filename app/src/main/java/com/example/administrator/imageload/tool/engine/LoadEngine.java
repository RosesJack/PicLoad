package com.example.administrator.imageload.tool.engine;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.example.administrator.imageload.tool.HttpConnect;
import com.example.administrator.imageload.tool.IHttpConnect;
import com.example.administrator.imageload.tool.policy.CachePolicy;
import com.example.administrator.imageload.tool.policy.ICachePolicy;
import com.example.administrator.imageload.tool.policy.ILoadPolicy;
import com.example.administrator.imageload.tool.policy.LoadPolicy;

/**
 * auther：wzy
 * date：2017/1/15 19 :17
 * desc:
 */

public class LoadEngine implements ILoadEngine {
    private ICachePolicy mICachePolicy = new CachePolicy();
    private ILoadPolicy mILoadPolicy = new LoadPolicy();
    private IHttpConnect<Bitmap> mBitmapIHttpConnect = new HttpConnect();
    private String url;
    private ResultCallBack mCallBack;

    public LoadEngine(String url, ResultCallBack mCallBack) {
        this.url = url;
        this.mCallBack = mCallBack;
    }

    @Override
    public void setLoadPolicy(ILoadPolicy loadPolicy) {
        mILoadPolicy = loadPolicy;
    }

    @Override
    public void setCachePolicy(ICachePolicy cachePolicy) {
        mICachePolicy = cachePolicy;
    }

    @Override
    public void caChePic(String url, Bitmap bitmap) {
        mICachePolicy.caChePic(url, bitmap);
    }

    @Override
    public Bitmap fetchPic(String url) {
        return mILoadPolicy.fetchPic(url);
    }

    @Override
    public void run() {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Bitmap bitmap = mILoadPolicy.fetchPic(url);
        if (bitmap == null) {
            bitmap = mBitmapIHttpConnect.get(url);
        }
        if (mCallBack != null) {
            mCallBack.callBack(bitmap);
        }
    }

    public IHttpConnect<Bitmap> getBitmapIHttpConnect() {
        return mBitmapIHttpConnect;
    }

    @Override
    public void setBitmapIHttpConnect(IHttpConnect<Bitmap> bitmapIHttpConnect) {
        mBitmapIHttpConnect = bitmapIHttpConnect;
    }

    public interface ResultCallBack {
        void callBack(Bitmap bitmap);
    }
}
