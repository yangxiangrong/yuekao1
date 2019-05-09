package com.example.baselibrary.mvp.view;

/**
 * 作者：$yangxiangrong
 * <p>
 * 2019/5/9 19:43
 */
public interface BaseView<T> {
    //请求成功
    void success(int type, String data);

    void successBean(int type, T t);

    //请求失败
    void fail(int type, String error);
}
