package com.miniapp.talks.utils;

import com.alibaba.fastjson.JSON;

import java.util.Map;

public class FastJsonUtils {

    @SuppressWarnings("unchecked")
    public static Map<String,Object> json2Map(String json){
        return JSON.parseObject(json, Map.class);
    }

    public static String obj2JsonString(Object obj){
        return JSON.toJSONString(obj);
    }

    public static String map2Json(Map<String,Object> map){
        String json = JSON.toJSONString(map,true); //转成JSON数据
        return json;
    }
}
