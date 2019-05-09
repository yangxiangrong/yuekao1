package com.example.baselibrary.mvp.presenter;

import com.example.baselibrary.mvp.model.BaseModel;
import com.example.baselibrary.mvp.view.BaseView;

import java.util.Map;

/**
 * 作者：$yangxiangrong
 * <p>
 * 2019/5/9 19:48
 */
public class BasePresenterIml implements BasePresenter, BaseModel.CallBackListener {

    private BaseModel baseModel;
    private BaseView baseView;

    public BasePresenterIml(BaseModel baseModel, BaseView baseView) {
        this.baseModel = baseModel;
        this.baseView = baseView;
    }

    @Override
    public void get(int type, String url, Map<String, String> map) {
        baseModel.get(type, url, map, this);
    }

    @Override
    public void post(int type, String url, Map<String, String> map) {
        baseModel.post(type, url, map, this);
    }

    @Override
    public void put(int type, String url, Map<String, String> map) {
        baseModel.put(type, url, map, this);
    }

    @Override
    public void delete(int type, String url, Map<String, String> map) {
        baseModel.delete(type, url, map, this);
    }

    //返回成功
    @Override
    public void success(int type, String data) {
        baseView.success(type, data);
    }

    //返回成功  返回JavaBean
    @Override
    public void successBean(int type, Object o) {
        baseView.successBean(type, o);
    }


    //返回失败
    @Override
    public void fail(int type, String error) {
        baseView.success(type, error);
    }
}

