package com.example.administrator.imageload.tool.policy;

import android.graphics.Bitmap;

/**
 * auther：wzy
 * date：2017/1/15 18 :29
 * desc: The picture load policy
 */

public interface ILoadPolicy {
    Bitmap fetchPic(String url);
}
