package com.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

/**
 * Title: cloud-server--com.facemeng.sun.common.support.PageResult
 * Description:
 * Copyright: Copyright (c) 2018/01
 * Create DateTime: 2018/1/10 上午11:59
 *
 * @author suanmilk
 * @version v2
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<V> {

    private List<V> records = Collections.emptyList();

    private int total;
}
