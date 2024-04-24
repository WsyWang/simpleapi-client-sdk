package com.wsy.simpleapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.wsy.simpleapiclientsdk.model.User;
import com.wsy.simpleapiclientsdk.utils.SignUtils;
import com.wsy.simpleapicommon.model.entity.InterfaceInfo;

import java.util.HashMap;
import java.util.Map;


public class SimpleApiClient {

    private final String accessKey;

    private final String secretKey;

    public static final String REQUEST_ADDRESS = "http://127.0.0.1:8090";

    public SimpleApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public Object webInvoke(InterfaceInfo interfaceInfo, String params) {
        String url = interfaceInfo.getUrl();
        String method = interfaceInfo.getMethod();
        if (method.equals("POST")) {
            return HttpRequest.post(REQUEST_ADDRESS + url)
                    .body(params)
                    .addHeaders(getHeaderMap(params))
                    .execute().body();
        } else {
            HashMap<String, Object> map = new HashMap();
            map.put(params, params);
            return HttpRequest.get(REQUEST_ADDRESS + url)
                    .addHeaders(getHeaderMap(params))
                    .form(map)
                    .execute().body();
        }
    }

    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap();
        paramMap.put("name", name);

        String result = HttpUtil.get(REQUEST_ADDRESS + "/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    public String getNameByPost(String name) {
        HashMap<String, Object> paramMap = new HashMap();
        paramMap.put("name", name);

        String result = HttpUtil.post(REQUEST_ADDRESS + "/api/name/post", paramMap);
        System.out.println(result);
        return result;
    }

    public String getUserNameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        String result = HttpRequest.post(REQUEST_ADDRESS + "/api/name/user")
                .body(json)
                .addHeaders(getHeaderMap(json))
                .execute().body();
        System.out.println(result);
        return result;
    }

    private Map<String, String> getHeaderMap(String body) {

        Map<String, String> hashMap = new HashMap();
        hashMap.put("accessKey", accessKey);
        //hashMap.put("secretKey", secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtils.genSign(body, secretKey));
        return hashMap;
    }
}
