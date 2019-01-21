package com.common.base;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.common.exception.CommonException;
import com.common.PageResult;
import com.query.ConvertClassData;
import com.query.QueryData;
import com.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

@Slf4j
public class BaseServiceImpl<M extends SuperMapper<T>, T, V> extends ServiceImpl<M, T> implements BaseService<V> {

    private Class<V> vClass;

    private Class<T> tClass;

    /**
     * 参数默认分页页码
     */
    int DEFAULT_PAGE_NO = 1;

    /**
     * 参数默认分页条数
     */
    int DEFAULT_PAGE_LIMIT = 10;

    // 前端定义分页排序参数名称
    /**
     * 当前页码
     */
    String PARAM_PAGE_CURRENT_NAME = "no";

    /**
     * 每页数据量
     */
    String PARAM_PAGE_SIZE_NAME = "limit";

    /**
     * 排序字段
     */
    String PARAM_PAGE_ORDER_BY_FIELD_NAME = "orderBy";

    /**
     * 是否正序
     */
    String PARAM_PAGE_IS_ASC_NAME = "isAsc";

    public BaseServiceImpl() {
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            this.tClass = (Class<T>) pt.getActualTypeArguments()[1];
            this.vClass = (Class<V>) pt.getActualTypeArguments()[2];
        } else {
            log.debug("type instanceof : {}", type.getClass().getName());
        }
    }


    @Override
    public V selectRecord(Serializable id) {
        T t = super.selectById(id);
        return b2m(t);
    }

    @Override
    public V selectFirst(Map<String, Object> cnd) {
        T t = super.selectOne(wrapper(cnd));
        return b2m(t);
    }

    /**
     * 查询条件，全部相等
     *
     * @param cnd
     * @return
     */
    protected Wrapper<T> wrapper(Map<String, Object> cnd) {
        T t = JSONObject.parseObject(JSONObject.toJSONString(cnd), tClass);
        return new EntityWrapper<>(t);
    }


    @Override
    public V insertRecord(V v) {
        T t = m2b(v);
        boolean success = super.insert(t);
        if (success) {
            return b2m(t);
        } else {
            throw new CommonException("插入记录失败");
        }
    }

    @Override
    public List<V> insertRecords(List<V> vs) {
        List<T> ts = ms2bs(vs);
        boolean success = super.insertBatch(ts);
        if (success) {
            return bs2ms(ts);
        } else {
            throw new CommonException("插入记录失败");
        }
    }

    @Override
    public void updateRecord(V v, Boolean updateAll) {
        T t = m2b(v);
        boolean success;
        if (updateAll) {
            success = super.updateAllColumnById(t);
        } else {
            success = super.updateById(t);
        }
        if (!success) {
            throw new CommonException("修改记录失败");
        }
    }

    /**
     * 默认更新非空字段
     *
     * @param v
     * @return
     */
    public void updateRecord(V v) {
        updateRecord(v, false);
    }

    @Override
    public void updateRecords(List<V> vs, Boolean updateAll) {
        List<T> ts = ms2bs(vs);
        boolean success;
        if (updateAll) {
            success = super.updateAllColumnBatchById(ts);
        } else {
            success = super.updateBatchById(ts);
        }
        if (!success) {
            throw new CommonException("修改记录失败");
        }
    }

    @Override
    public void deleteRecord(Serializable id) {
        boolean success = super.deleteById(id);
        if (!success) {
            throw new CommonException("删除记录失败");
        }
    }

    @Override
    public void deleteRecords(List<? extends Serializable> ids) {
        boolean success = super.deleteBatchIds(ids);
        if (!success) {
            throw new CommonException("删除记录失败");
        }
    }





    // ----模型属性拷贝方法-----------------------------------------------------------

    /**
     * 复制属性
     *
     * @param source
     * @param targetClass
     * @return
     */
    public <O> O copyProp(Object source, Class<O> targetClass) {
        O target = null;
        if (source != null) {
            try {
                target = targetClass.newInstance();
            } catch (Exception e) {
                log.error("对象属性复制出错  [{}]", e);
                throw new CommonException("BaseServiceImpl 对象属性复制出错 ", e);
            }
            BeanUtils.copyProperties(source, target);
        }
        return target;
    }

    public T m2b(V v) {
        return copyProp(v, tClass);
    }

    public V b2m(T t) {
        return copyProp(t, vClass);
    }

    public List<V> bs2ms(List<T> ts) {
        List<V> vs = null;
        if (ts != null && ts.size() > 0) {
            vs = new ArrayList<>();
            for (T t : ts) {
                V v = b2m(t);
                vs.add(v);
            }
        }
        return vs;
    }

    public List<T> ms2bs(List<V> vs) {
        List<T> ts = null;
        if (vs != null && vs.size() > 0) {
            ts = new ArrayList<>();
            for (V v : vs) {
                T t = m2b(v);
                ts.add(t);
            }
        }
        return ts;
    }

    @Override
    public List<V> selectAll(Map<String, Object> map) {
        return bs2ms(super.selectByMap(map));
    }


    // 条件查询（针对自定义查询方法）
    /**
     * 分页条件查询
     *
     * @return PageDto<PlatformDept>
     */
    public <O> Page<V> findSelfList(O vo) {
        T temp = null;
        try {
            temp = tClass.newInstance();
        }catch (Exception e){
            throw new CommonException("查询失败");
        }
        //自定义查询方法
        QueryData queryData = ConvertClassData.voToQueryData(vo,temp);
        //定义一个空的分页
        Page page = new Page();
        //判断是否开启分页 0 - 不开启 1开启
        if (queryData.getPageFlag() == 1){
            page = new Page(queryData.getCurrentPage(), queryData.getPageSize());
            page.setRecords(bs2ms(baseMapper.findSelfList(page, queryData)));
        }else{
            List<T> findList = baseMapper.findSelfList(queryData);
            if (findList != null){
                page.setRecords(bs2ms(findList));
                page.setTotal(findList.size());
            }
        }
        return page;
    }

    /**
     * 数据库查询出的map转换成程序能用的map
     */
    public Map<String,Object> changeDbMap(List<Map<String,Object>> dbMap){
        //数据库查出的map需要转换
        Map<String,Object> tempMap = new HashMap<>();
        for (Map<String,Object> tempEnum:dbMap){
            String customerSort = null;
            Object sortCount = null;
            for(Map.Entry<String,Object> entry:tempEnum.entrySet()){
                if ("db_key".equals(entry.getKey())) {
                    customerSort =  String.valueOf(entry.getValue());
                }else if ("db_value".equals(entry.getKey())) {
                    sortCount = entry.getValue();
                }
            }
            tempMap.put(customerSort,sortCount);
        }
        return tempMap;
    }



    /**
     * 各模块根据客户类型统计数量
     *//*
    public <O> Map<String,Integer> findCustomerTypeSelfList(O vo){
        T temp = null;
        try {
            temp = tClass.newInstance();
        }catch (Exception e){
            throw new CommonException("查询失败");
        }
        //自定义查询方法
        QueryData queryData = ConvertClassData.voToQueryData(vo,temp);

        //获取系统内所有客户类型
        Map<String,String> allMap= CustomerType.getCustomerTypeList();
        //查询数据库现有的客户类型及其数量
        List<Map<String,Object>> dbMap = baseMapper.findCustomerTypeSelfList(queryData);
        //数据库查出的map需要转换
        Map<String,Object> tempMap = changeDbMap(dbMap);

        Map<String,Integer> resultMap = new HashMap<>();
        for (Map.Entry<String,String> all:allMap.entrySet()){
            Integer count = tempMap.get(all.getKey()) == null ? 0:Integer.parseInt(tempMap.get(all.getKey()).toString());
            resultMap.put(all.getKey(),count);
        }
        return resultMap;
    }*/


    /**------------------基础增删改查---------------------**/

    public <A> V insertService(A addVo){
        V model = copyProp(addVo, vClass);
        return insertRecord(model);
    }

    public <U> V updateService(U updateVo){
        V model = copyProp(updateVo, vClass);
        updateRecord(model);
        return model;
    }

    public void deleteService(String[] ids){
        deleteRecords(Arrays.asList(ids));
    }

    public V findByIdService(String id){
        return selectRecord(id);
    }
    //ID批量查询
    public List<V> findByIdsService(String[] ids){
        if (ids != null && ids.length >0){
            return bs2ms(selectBatchIds(Arrays.asList(ids)));
        }
        return null;
    }

    @Override
    public PageResult<V> selectRecords(Map<String, Object> cnd) {
        if (Integer.parseInt(cnd.get("isPage").toString()) == 0){
            //不需要分页
            List<T> list = super.selectList(wrapper(cnd));
            return new PageResult<>(bs2ms(list), list.size());
        }else {
            //需要分页
            Page<T> page = super.selectPage(page(cnd), wrapper(cnd));
            return new PageResult<>(bs2ms(page.getRecords()), (int)page.getTotal());
        }

    }

    /**
     * 分页条件
     *
     * @param cnd
     * @return
     */
    protected Page<T> page(Map<String, Object> cnd) {
        // 分页
        Page page = new Page<>(Integer.parseInt(cnd.getOrDefault(PARAM_PAGE_CURRENT_NAME, DEFAULT_PAGE_NO).toString()),
                Integer.parseInt(cnd.getOrDefault(PARAM_PAGE_SIZE_NAME, DEFAULT_PAGE_LIMIT).toString()));
        // 排序
        String orderByField = cnd.getOrDefault(PARAM_PAGE_ORDER_BY_FIELD_NAME, "").toString();
        // 拥有排序字段，排序类型才有效
        if (StringUtils.isNotEmpty(orderByField)) {
            Map<String, String> mapping = CommonUtil.entityResultMap(new HashMap<>(), tClass);
            // 排序字段是否合法
            if (mapping.containsKey(orderByField)) {
                orderByField = mapping.get(orderByField);
            } else if (!mapping.containsValue(orderByField)) {
                return page;
            }
            page.setOrderByField(orderByField);
            page.setAsc(Boolean.parseBoolean(cnd.getOrDefault(PARAM_PAGE_IS_ASC_NAME, Boolean.TRUE).toString()));
        }
        return page;
    }


}
