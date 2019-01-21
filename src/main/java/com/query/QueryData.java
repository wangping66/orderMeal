package com.query;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class QueryData {
    @ApiModelProperty(value = "排序条件")
    private List<OrderData> oData; //排序条件
    @ApiModelProperty(value = "查询条件")
    private List<SearchData> sData; //查询条件(add)
    @ApiModelProperty(value = "查询条件")
    private List<SearchData> orData; //查询条件(or)


    //分页条件
    @ApiModelProperty(value = "当前页",example = "1")
    private Integer currentPage = 1; //当前页
    @ApiModelProperty(value = "条数",example = "10")
    private Integer pageSize = 10;//条数

    //是否开启分页查询 0 -不开启 1 -开启  默认开启
    @ApiModelProperty(value = "是否开启分页查询 0 -不开启 1 -开启  默认开启",example = "1")
    private Integer pageFlag = 1; //是否开启分页查询 （默认开启）

    @Data
    public static class OrderData{
        @ApiModelProperty(value = "排序字段名")
        private String N;
        @ApiModelProperty(value = "排序类型，降序 ：down ；升序 ：up")
        private String T;
    }

    @Data
    public static class SearchData{
        @ApiModelProperty(value = "查询字段名")
        private String N;
        @ApiModelProperty(value = "查询内容")
        private String V;
        @ApiModelProperty(value = "查询类型，equals ：相等；like ：模糊；or ：或者；gt ：大于等于；lt ：小于等于；")
        private String T;
    }



    /**
     * 条件构造器
     * @return EntityWrapper
     */
    public  EntityWrapper getEntityWrapper(){
        EntityWrapper entityWrapper = new EntityWrapper();
        List<SearchData> searchDataEw = getSData();
        List<OrderData> orderDataEw = getOData();
        //实体类转数据库字段
        //Map<String,String> maps = ConvertClassData.classToData(model);
        //注入查询条件
        if (searchDataEw != null && searchDataEw.size() > 0){
            for (SearchData searchDataOne:searchDataEw){
                //查询类型
                String fieldTypeEw = searchDataOne.getT();
                //查询字段
                String fieldNameEw = searchDataOne.getN();
                //查询内容
                String fieldValueEw = searchDataOne.getV();

                if (fieldTypeEw != null && fieldTypeEw != "" && fieldNameEw != null && fieldNameEw != "" && fieldValueEw != null){
                    if (fieldTypeEw.equals("equals")){
                        entityWrapper.eq(fieldNameEw,fieldValueEw);
                    }else if (fieldTypeEw.equals("like")){
                        entityWrapper.like(fieldNameEw,fieldValueEw);
                    }else if (fieldTypeEw.equals("or")){
                        entityWrapper.or(fieldNameEw,fieldValueEw);
                    }else if (fieldTypeEw.equals("gt")){
                        //大于等于
                        entityWrapper.ge(fieldNameEw,fieldValueEw);
                    }else if (fieldTypeEw.equals("lt")){
                        //小于等于
                        entityWrapper.le(fieldNameEw,fieldValueEw);
                    }else if (fieldTypeEw.equals("ne")){
                        //不等于
                        entityWrapper.ne(fieldNameEw,fieldValueEw);
                    }
                }
            }
        }
        //注入排序条件
        if (orderDataEw != null && orderDataEw.size() > 0){
            for (OrderData orderDataOne:orderDataEw){
                //排序字段
                String orderFieldNameEw = orderDataOne.getN();
                //排序类型
                String orderTypeEw = orderDataOne.getT();

                if (orderFieldNameEw != null && orderFieldNameEw != "" && orderTypeEw != null && orderTypeEw != ""){
                    if (orderTypeEw.equals("down")){
                        entityWrapper.orderBy(orderFieldNameEw,false);
                    }else if (orderTypeEw.equals("up")){
                        entityWrapper.orderBy(orderFieldNameEw);
                    }
                }

            }
        }

        return entityWrapper;
    }

}
