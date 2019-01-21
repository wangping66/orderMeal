package com.common;


import com.constant.HttpResponseCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Title: cloud-server--com.facemeng.server.common.constant.HttpResponseCode
 * Description: http响应正常处理结果
 * Copyright: Copyright (c) 2017/12
 * Create DateTime: 2017/12/22 上午2:14
 *
 * @author suanmilk
 * @version v2
 */
@ApiModel
public class Result<T> implements Serializable {
    /**
     * 请求是否成功
     **/
    @ApiModelProperty(value = "请求是否成功")
    private boolean success;
    /**
     * 状态码
     **/
    @ApiModelProperty(value = "状态码")
    private String code;
    /**
     * 返回数据集
     **/
    @ApiModelProperty(value = "返回数据集")
    private T results;
    /**
     * 返回信息
     **/
    @ApiModelProperty(value = "返回信息")
    private String msg;
    /**
     * 记录总数
     **/
    @ApiModelProperty(value = "记录总数")
    private long total;

    public Result() {
    }

    private Result(JsonResultBuilder<T> builder) {
        this.success = builder.success;
        this.results = builder.results;
        this.msg = builder.msg;
        this.code = builder.code;
        this.total = builder.total;
    }

    public static <T> Result.JsonResultBuilder<T> builder() {
        return new JsonResultBuilder<>();
    }

    public static final class JsonResultBuilder<T> {

        private boolean success;

        private T results;

        private String msg;

        private String code;

        private long total;

        private JsonResultBuilder() {
        }

        public JsonResultBuilder ok() {
            return ok("操作成功");
        }

        public JsonResultBuilder ok(String msg) {
            this.msg = msg;
            this.success = true;
            this.code = HttpResponseCode.SC_OK;
            return this;
        }

        /**
         * 检查操作，即一个请求出现两个接口，需要前端手动判断
         * @param msg
         * @return
         */
        public JsonResultBuilder check(String msg) {
            this.msg = msg;
            this.success = true;
            this.code = HttpResponseCode.SC_NO_CONTENT;
            return this;
        }

        public JsonResultBuilder create(String msg) {
            this.msg = msg;
            this.success = true;
            this.code = HttpResponseCode.SC_CREATED;
            return this;
        }

        public JsonResultBuilder error(String msg) {
            this.msg = msg;
            this.success = false;
            this.code = HttpResponseCode.SC_BAD_REQUEST;
            return this;
        }

        public JsonResultBuilder conflict(String msg) {
            this.msg = msg;
            this.success = false;
            this.code = HttpResponseCode.SC_CONFLICT;
            return this;
        }

        public JsonResultBuilder notFound(String msg) {
            this.msg = msg;
            this.success = false;
            this.code = HttpResponseCode.SC_NOT_FOUND;
            return this;
        }


        public JsonResultBuilder total(long total) {
            this.total = total;
            return this;
        }

        public JsonResultBuilder data(T data) {
            this.results = data;
            return this;
        }

        public JsonResultBuilder message(String msg) {
            this.msg = msg;
            return this;
        }

        public JsonResultBuilder status(String code) {
            this.code = code;
            return this;
        }



        public Result build() {
            return new Result<>(this);
        }
    }


    public boolean isSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public T getResults() {
        return results;
    }

    public String getMsg() {
        return msg;
    }

    public long getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", results=" + results +
                ", sms='" + msg + '\'' +
                ", total=" + total +
                '}';
    }


}
