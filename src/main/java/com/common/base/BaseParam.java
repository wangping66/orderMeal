package com.common.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class BaseParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否开启分页, 0 -不开启 1 -开启  默认开启
     */
    @ApiModelProperty(value = "是否开启分页查询 0 -不开启 1 -开启  默认不开启",example = "0")
    private Integer isPage = 0;

    @ApiModelProperty(value = "当前页码")
    private int no = 1;

    @ApiModelProperty(value = "每页数据量")
    private int limit = 10;

    @ApiModelProperty(value = "排序字段")
    private String orderBy;

    /**
     * 是否开启升序 false-降序 true-升序 默认降序
     */
    @ApiModelProperty(value = "排序方式")
    private Boolean isAsc;



    /**
     * 参数转MAP
     *
     * @return
     */
    public Map<String, Object> map() {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(this);
        Set<Map.Entry<String, Object>> entrySet = jsonObject.entrySet();
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : entrySet) {
            if (entry.getKey() == null || entry.getValue() == null)
                continue;
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
}
