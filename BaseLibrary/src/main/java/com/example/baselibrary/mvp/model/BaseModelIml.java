package com.example.baselibrary.mvp.model;

import com.example.baselibrary.net.HttpUtils;
import com.google.gson.Gson;

import java.util.Map;

/**
 * 作者：$yangxiangrong
 * <p>
 * 2019/5/9 19:46
 */

public class BaseModelIml implements BaseModel {


    private Class cls;

    public BaseModelIml(Class cls) {
        this.cls = cls;
    }

    //get请求
    @Override
    public void get(int type, String url, Map<String, String> map, CallBackListener listener) {
        doHttp(0, type, url, map, listener);
    }

    //post请求
    @Override
    public void post(int type, String url, Map<String, String> map, CallBackListener listener) {
        doHttp(1, type, url, map, listener);
    }

    //put请求
    @Override
    public void put(int type, String url, Map<String, String> map, CallBackListener listener) {
        doHttp(2, type, url, map, listener);
    }

    //delete请求
    @Override
    public void delete(int type, String url, Map<String, String> map, CallBackListener listener) {
        doHttp(3, type, url, map, listener);
    }

    //执行网络请求
    private void doHttp(int state, final int type, String url, Map<String, String> map, final CallBackListener listener) {
        HttpUtils httpUtils = new HttpUtils();
        if (state == 0) {//get
            httpUtils.get(url, map);
        } else if (state == 1) {//post
            httpUtils.post(url, map);
        } else if (state == 2) {//put
            httpUtils.put(url, map);
        } else if (state == 3) {//delete
            httpUtils.delete(url, map);
        }
        httpUtils.result(new HttpUtils.HttpListener() {
            @Override
            public void success(String data) {
                if (cls == null) {
                    listener.success(type, data);
                } else {
                    Object bean = new Gson().fromJson(data, cls);
                    listener.successBean(type, bean);
                }

            }

            @Override
            public void fail(String error) {
                listener.fail(type, error);
            }
        });

    }
}
