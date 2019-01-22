package com.enums;

/**
 * 枚举接口
 *
 **/
public interface Enum<V, T> {

    /**
     * 枚举对应的key
     *
     * @return
     */
    V getValue();

    /**
     * 枚举对应的value
     *
     * @return
     */
    T getLabel();

}
