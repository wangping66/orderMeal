package com.common.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.query.QueryData;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;


/**
 *
 * @param <T>
 */
public interface SuperMapper<T> extends BaseMapper<T> {


    List<T> findSelfList(Pagination page, @Param("queryData") QueryData queryData);

    List<T> findSelfList(@Param("queryData") QueryData queryData);

    List<Map<String,Object>> findCustomerTypeSelfList(@Param("queryData") QueryData queryData);
}
