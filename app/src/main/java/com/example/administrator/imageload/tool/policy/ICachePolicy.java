package com.example.administrator.imageload.tool.policy;

import android.graphics.Bitmap;

/**
 * auther：wzy
 * date：2017/1/15 18 :29
 * desc: The cache policy
 */

public interface ICachePolicy {
    void caChePic(String url,Bitmap bitmap);
}
