package com.example.administrator.imageload.tool;

/**
 * Created by Administrator on 2017/1/3.
 */

public interface IHttpConnect<T> {
    T get(String url);

    T post(String url);
}
