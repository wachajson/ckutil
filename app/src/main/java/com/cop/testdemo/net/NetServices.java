package com.cop.testdemo.net;


import com.cop.testdemo.bean.KContBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;



public interface NetServices {


    @POST("getKcont")
    @FormUrlEncoded
    Observable<BaseEntery<KContBean>> requestCont(@Field("packageName") String packageName);


}
