package com.example.administrator.imageload.tool.request;

/**
 * auther：wzy
 * date：2017/1/15 23 :24
 * desc:
 */

public interface Request {
    void addTask(String url);

    void pauseAllTask();

    void resumeAllTask();

    void startAllTask();

    void stopALlTask();
}
