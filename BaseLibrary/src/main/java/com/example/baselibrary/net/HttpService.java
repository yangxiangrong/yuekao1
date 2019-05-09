package com.example.baselibrary.net;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 作者：$yangxiangrong
 * <p>
 * 2019/5/9 19:49
 */
public interface HttpService {
    @GET
    Observable<ResponseBody> get(@Url String url,
                                 @HeaderMap Map<String, String> headMap,
                                 @QueryMap Map<String, String> map);

    @POST
    Observable<ResponseBody> post(@Url String url,
                                  @HeaderMap Map<String, String> headMap,
                                  @QueryMap Map<String, String> map);

    @PUT
    Observable<ResponseBody> put(@Url String url,
                                 @HeaderMap Map<String, String> headMap,
                                 @QueryMap Map<String, String> map);

    @DELETE
    Observable<ResponseBody> delete(@Url String url,
                                    @HeaderMap Map<String, String> headMap,
                                    @QueryMap Map<String, String> map);
}
