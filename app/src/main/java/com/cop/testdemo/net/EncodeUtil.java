package com.cop.testdemo.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;


/**
 * author:      林福君
 * date:        2018/8/22 17:58
 * description: 编码工具类
 * <p>
 * base64编码
 */

public class EncodeUtil {

    /**
     * 将bitmap进行base64编码
     * 图片的 Base64 编码是不包含图片头的，如（data:image/jpg;base64,）
     * 百度OCR文档中说所有图片均需要Base64编码后再进行urlencode，这里容易造成困扰，其实Base64后就够了，
     * 因为Base64包含的64个字符为 a-z, A-Z, 0-9, /, + 以及填充字符 = 都包含在了urlencode不需要进行编码的字符内。
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToString(Bitmap bitmap) {

        // 将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, bStream);//参数如果为100那么就不压缩
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

    /**
     * 将Base64编码字符串转化为Bitmap对象
     *
     * @param string
     * @return
     */
    public static Bitmap stringToBitmap(String string) {
        // 将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
