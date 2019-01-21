package com.query;


public enum QueryType {

    EQUALS("equals","相等"),
    LIKE("like","模糊"),
    IN("in","数组查询"),
    GT("gt","大于等于"),
    LT("lt","小于等于"),
    OR("or","或");
    //防止字段值被修改，增加的字段也统一final表示常量
    private final String key;
    private final String value;

    QueryType(String key,String value){
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static QueryType getEnumByKey(String key){
        if(null == key){
            return null;
        }
        for(QueryType temp:QueryType.values()){
            if(temp.getKey().equals(key)){
                return temp;
            }
        }
        return null;
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
}
