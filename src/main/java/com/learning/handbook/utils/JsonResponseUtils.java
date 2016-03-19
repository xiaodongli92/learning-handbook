package com.learning.handbook.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class JsonResponseUtils {
    private static final String JSON_ERRORCODE_NAME = "errorCode";
    private static final String JSON_ERRORMSG_NAME = "errorMessage";
    private static final String JSON_DATA_NAME = "data";
    private static final String JSON_OVER_COUNT = "overcount";

    private JsonResponseUtils() {
    }

    public static String badResult(String cause) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(1));
        result.put(JSON_ERRORMSG_NAME, cause);
        return result.toJSONString();
    }

    public static String badResult(int errorCode, String cause) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(errorCode));
        result.put(JSON_ERRORMSG_NAME, cause);
        return result.toJSONString();
    }

    public static String badResult(Integer code, String cause, int overcount) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, code);
        result.put(JSON_ERRORMSG_NAME, cause);
        result.put(JSON_OVER_COUNT, Integer.valueOf(overcount));
        return result.toJSONString();
    }

    public static String emptyResult(String cause) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(2));
        result.put(JSON_ERRORMSG_NAME, cause);
        return result.toJSONString();
    }

    public static String badResult(String cause, String callback) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(1));
        result.put(JSON_ERRORMSG_NAME, cause);
        return callback + "(" + result.toJSONString() + ")";
    }

    public static String badResult(Map<String, String> cause) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(1));
        result.put(JSON_ERRORMSG_NAME, cause);
        return result.toJSONString();
    }

    public static String badResult(int errorCode, Object cause) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(errorCode));
        result.put(JSON_ERRORMSG_NAME, cause);
        return result.toJSONString();
    }

    public static String ok() {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(0));
        result.put(JSON_DATA_NAME, "成功");
        return result.toString();
    }

    public static String okWithEmpty() {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(0));
        result.put(JSON_DATA_NAME, Collections.emptyList());
        return result.toString();
    }

    public static String ok(String key, Object value) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(0));
        result.put(JSON_DATA_NAME, ImmutableMap.of(key, value));
        return result.toJSONString();
    }

    public static String ok(Object object) {
        HashMap result = new HashMap();
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(0));
        result.put(JSON_DATA_NAME, object);
        return JSON.toJSONString(result, new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect});
    }

    public static String ok(Object object, Map<String, Object> params) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(0));
        result.put(JSON_DATA_NAME, object);
        if(params != null && params.size() > 0) {
            Iterator keys = params.entrySet().iterator();

            while(keys.hasNext()) {
                Map.Entry entry = (Map.Entry)keys.next();
                result.put(String.valueOf(entry.getKey()), entry.getValue());
            }
        }

        return result.toJSONString();
    }

    public static String okWithPaginate(Object object, int pageTotal, int pageSize, int pageNo) {
        JSONObject result = new JSONObject();
        result.put("pageTotal", Integer.valueOf(pageTotal));
        result.put("pageSize", Integer.valueOf(pageSize));
        result.put("pageNo", Integer.valueOf(pageNo));
        result.put(JSON_DATA_NAME, object);
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(0));
        return result.toJSONString();
    }

    public static String ok(List list) {
        JSONObject result = new JSONObject();
        result.put(JSON_DATA_NAME, list);
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(0));
        return result.toJSONString();
    }

    public static String jsonp(Object object, String callback) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(0));
        result.put(JSON_DATA_NAME, object);
        return callback + "(" + result.toJSONString() + ")";
    }

    public static String ok(Map<String, Object> params) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(0));
        result.put(JSON_DATA_NAME, params);
        return result.toJSONString();
    }

    public static String ok(ImmutableMap<String, Object> params) {
        JSONObject result = new JSONObject();
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(0));
        result.put(JSON_DATA_NAME, params);
        return result.toJSONString();
    }

    public static <T> String okWithPaginate(List<T> list, int pageTotal, int pageSize, int pageNo) {
        JSONObject result = new JSONObject();
        result.put("pageTotal", Integer.valueOf(pageTotal));
        result.put("pageSize", Integer.valueOf(pageSize));
        result.put("pageNo", Integer.valueOf(pageNo));
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(0));
        result.put(JSON_DATA_NAME, list);
        return result.toJSONString();
    }

    public static String okWithPaginate(Map<String, Object> params, int pageTotal, int pageSize, int pageNo) {
        JSONObject result = new JSONObject();
        result.put("pageTotal", Integer.valueOf(pageTotal));
        result.put("pageSize", Integer.valueOf(pageSize));
        result.put("pageNo", Integer.valueOf(pageNo));
        result.put(JSON_ERRORCODE_NAME, Integer.valueOf(0));
        result.put(JSON_DATA_NAME, params);
        return result.toJSONString();
    }

    public static String okSupportJSONP(Object object, String fun) {
        if(StringUtils.isBlank(fun)) {
            return ok((Object)object);
        } else {
            JSONObject result = new JSONObject();
            result.put(JSON_ERRORCODE_NAME, Integer.valueOf(0));
            result.put(JSON_DATA_NAME, object);
            return fun + "(" + result.toJSONString() + ")";
        }
    }

    public static <T> String okSupportJSONPWithPaginate(List<T> list, String callback, Map<String, Object> params) {
        if(StringUtils.isBlank(callback)) {
            return ok((List)list);
        } else {
            JSONObject result = new JSONObject();
            result.put(JSON_ERRORCODE_NAME, Integer.valueOf(0));
            result.put(JSON_DATA_NAME, list);
            if(params != null && params.size() > 0) {
                Iterator keys = params.entrySet().iterator();

                while(keys.hasNext()) {
                    Map.Entry entry = (Map.Entry)keys.next();
                    result.put(String.valueOf(entry.getKey()), entry.getValue());
                }
            }

            return callback + "(" + result.toJSONString() + ")";
        }
    }

    public static String badResultSupportJSONP(String cause, String fun) {
        if(StringUtils.isBlank(fun)) {
            return badResult((String)cause);
        } else {
            JSONObject result = new JSONObject();
            result.put(JSON_ERRORCODE_NAME, Integer.valueOf(1));
            result.put(JSON_ERRORMSG_NAME, cause);
            return fun + "(" + result.toJSONString() + ")";
        }
    }

    public static String okSupportEmptyWithJSONP(String fun) {
        if(StringUtils.isBlank(fun)) {
            return okWithEmpty();
        } else {
            JSONObject result = new JSONObject();
            result.put(JSON_ERRORCODE_NAME, Integer.valueOf(0));
            result.put(JSON_ERRORMSG_NAME, "success");
            return fun + "(" + result.toString() + ")";
        }
    }
}
