package com.common.base;

import com.baomidou.mybatisplus.plugins.Page;
import com.common.PageResult;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService<V> {
    /**
     * 根据ID，获取一条记录
     *
     * @param id 主键
     * @return
     */
    V selectRecord(Serializable id);

    /**
     * 根据条件，获取第一条记录
     *
     * @param cnd 查询条件（包含分页信息）
     * @return
     */
    V selectFirst(Map<String, Object> cnd);

    /**
     * 根据条件，获取多条记录
     *
     * @param cnd 查询条件（包含分页信息）
     * @return
     */
    PageResult<V> selectRecords(Map<String, Object> cnd);

    /**
     * 插入一条记录
     *
     * @param v
     * @return
     */
    V insertRecord(V v);

    /**
     * 插入多条记录
     *
     * @param vs
     * @return
     */
    List<V> insertRecords(List<V> vs);

    /**
     * 根据主键更新一条记录
     *
     * @param v         记录
     * @param updateAll 是否更新全部属性
     * @return
     */
    void updateRecord(V v, Boolean updateAll);

    /**
     * 根据主键更新多条记录
     *
     * @param vs        记录集合
     * @param updateAll 是否更新全部属性
     * @return
     */
    void updateRecords(List<V> vs, Boolean updateAll);

    /**
     * 根据ID删除一条记录
     *
     * @param id
     * @return
     */
    void deleteRecord(Serializable id);

    /**
     * 根据ID删除多条记录
     *
     * @param ids
     * @return
     */
    void deleteRecords(List<? extends Serializable> ids);

    List<V> selectAll(Map<String, Object> map);

    /**------------------基础增删改查各个模块可以根据自己的需要重写---------------------**/

    <O> Page<V> findSelfList(O vo);

    <A> V insertService(A addVo);

    <U> V updateService(U updateVo);

    void deleteService(String[] Ids);

    V findByIdService(String Id);

    List<V> findByIdsService(String[] Ids); //ID批量查询
}
