package com.example.baselibrary.net;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 作者：$yangxiangrong
 * <p>
 * 2019/5/9 19:51
 */
public class HttpUtils {


    private  String base_url = "https://code.aliyun.com";


    //更改baseurl
    public HttpUtils setBaseUrl(String base_url){
        this.base_url=base_url;
        return this;
    }

    //传递头参
    private Map<String, String> headMap = new HashMap<>();

    public HttpUtils setHead(Map<String, String> headMap) {
        this.headMap = headMap;
        return this;
    }

    //get请求
    public HttpUtils get(String url, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        HttpService service = getHttpService();
        Observable<ResponseBody> ob = service.get(url, headMap, map);
        send(ob);
        return this;

    }


    //post请求
    public HttpUtils post(String url, Map<String, String> map) {
        HttpService service = getHttpService();
        Observable<ResponseBody> ob = service.post(url, headMap, map);
        send(ob);
        return this;
    }

    //put请求
    public HttpUtils put(String url, Map<String, String> map) {
        HttpService service = getHttpService();
        Observable<ResponseBody> ob = service.put(url, headMap, map);
        send(ob);
        return this;
    }

    //delete请求
    public HttpUtils delete(String url, Map<String, String> map) {
        HttpService service = getHttpService();
        Observable<ResponseBody> ob = service.delete(url, headMap, map);
        send(ob);
        return this;
    }


    //产生订阅
    private void send(Observable<ResponseBody> ob) {
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverIml<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {

                            if (cls == null) {//返回字符串
                                mHttpListener.success(responseBody.string());
                            } else {
                                //返回JavaBean
                                Object bean = new Gson().fromJson(responseBody.string(), cls);
                                mHttpBeanListener.success(bean);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (cls == null) {
                            mHttpListener.fail(e.getMessage());
                        } else {
                            mHttpBeanListener.fail(e.getMessage());
                        }

                    }
                });
    }


    //传递javabean接口
    private HttpBeanListener mHttpBeanListener;
    private Class cls;
    public void resultBean(Class cls, HttpBeanListener mHttpBeanListener) {
        this.cls = cls;
        this.mHttpBeanListener = mHttpBeanListener;
    }


    //返回JavaBean
    public interface HttpBeanListener<T> {
        void success(T t);

        void fail(String error);
    }


    //传递接口
    private HttpListener mHttpListener;

    public void result(HttpListener mHttpListener) {
        this.mHttpListener = mHttpListener;
    }

    //返回字符串
    public interface HttpListener {
        void success(String data);

        void fail(String error);
    }

    //获取请求接口
    private HttpService getHttpService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(base_url).build();
        return retrofit.create(HttpService.class);
    }
}
