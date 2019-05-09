package com.example.baselibrary.mvp.presenter;

import java.util.Map;

/**
 * 作者：$yangxiangrong
 * <p>
 * 2019/5/9 19:47
 */
public interface BasePresenter {
    void get(int type, String url, Map<String, String> map);

    void post(int type, String url, Map<String, String> map);

    void put(int type, String url, Map<String, String> map);

    void delete(int type, String url, Map<String, String> map);
}
