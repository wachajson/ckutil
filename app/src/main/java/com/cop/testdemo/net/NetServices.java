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

/**
 * author:      林福君
 * date:        2018/8/25 16:07
 * description:
 */

public interface NetServices {


    @POST("getKcont")
    @FormUrlEncoded
    Observable<BaseEntery<KContBean>> requestCont(@Field("packageName") String packageName);
    /**
     *
     * @param url          (KApi.baiDuPlant)
     * @return
     */
    @POST
    @FormUrlEncoded
    Observable<ResponseBody> requestPlant(@Url String url, @Field("userId") String userId, @Field("name") String name,@Field("portraitUri")String portraitUri);



    /**
     *
     * @param type   1, home 分类  2，点击文件
     * @param classifyType
     * @param fileName
     * @return
     */
    @FormUrlEncoded
    @POST("lyuploadClickDataInterface")
    Observable<ResponseBody> uploadClickData(@Field("type") int type,
                                             @Field("classifyType") int classifyType,
                                             @Field("fileName") String fileName);




}
