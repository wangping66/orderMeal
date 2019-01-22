package com.constant;

import java.io.File;

/**
 * Title: cloud-server--com.facemeng.server.common.constant.HttpResponseConst
 * Description: http响应状态码
 * Copyright: Copyright (c) 2017/12
 * Create DateTime: 2017/12/22 上午2:14
 *
 * @author suanmilk
 * @version v2
 */
public class HttpResponseCode {
    public final static String SC_CONTINUE = "100";
    public final static String SC_SWITCHING_PROTOCOLS = "101";
    public final static String SC_OK = "200";
    public final static String SC_CREATED = "201";
    public final static String SC_ACCEPTED = "202";
    public final static String SC_NON_AUTHORITATIVE_INFORMATION = "203";
    public final static String SC_NO_CONTENT = "204";
    public final static String SC_RESET_CONTENT = "205";
    public final static String SC_PARTIAL_CONTENT = "206";
    public final static String SC_MULTIPLE_CHOICES = "300";
    public final static String SC_MOVED_PERMANENTLY = "301";
    public final static String SC_MOVED_TEMPORARILY = "302";
    public final static String SC_FOUND = "302";
    public final static String SC_SEE_OTHER = "303";
    public final static String SC_NOT_MODIFIED = "304";
    public final static String SC_USE_PROXY = "305";
    public final static String SC_TEMPORARY_REDIRECT = "307";
    public final static String SC_BAD_REQUEST = "400";
    public final static String SC_UNAUTHORIZED = "401";
    public final static String SC_PAYMENT_REQUIRED = "402";
    public final static String SC_FORBIDDEN = "403";
    public final static String SC_NOT_FOUND = "404";
    public final static String SC_METHOD_NOT_ALLOWED = "405";
    public final static String SC_NOT_ACCEPTABLE = "406";
    public final static String SC_PROXY_AUTHENTICATION_REQUIRED = "407";
    public final static String SC_REQUEST_TIMEOUT = "408";
    public final static String SC_CONFLICT = "409";
    public final static String SC_GONE = "410";
    public final static String SC_LENGTH_REQUIRED = "411";
    public final static String SC_PRECONDITION_FAILED = "412";
    public final static String SC_REQUEST_ENTITY_TOO_LARGE = "413";
    public final static String SC_REQUEST_URI_TOO_LONG = "414";
    public final static String SC_UNSUPPORTED_MEDIA_TYPE = "415";
    public final static String SC_REQUESTED_RANGE_NOT_SATISFIABLE = "416";
    public final static String SC_EXPECTATION_FAILED = "417";
    public final static String SC_INTERNAL_SERVER_ERROR = "500";
    public final static String SC_NOT_IMPLEMENTED = "501";
    public final static String SC_BAD_GATEWAY = "502";
    public final static String SC_SERVICE_UNAVAILABLE = "503";
    public final static String SC_GATEWAY_TIMEOUT = "504";
    public final static String SC_HTTP_VERSION_NOT_SUPPORTED = "505";



    /**
     * 下载模版存放项目路径
     */
    public static final String FILE_TEMPLATE_PATH = "template";

    public static final String SLASH = File.separator;

    public static final  String FORMAT_DATE = "yyyy-MM-dd";

    public static final String FORMAT_DATETIME_DEFAULT = "dd/MM/yyyy HH:mm";
}
