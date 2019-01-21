package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.common.base.BaseEntity;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 订餐用户表
 * </p>
 *
 * @author wp
 * @since 2019-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("order_user")
public class OrderUser extends BaseEntity<OrderUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户uuid
     */
    @TableId("user_id")
    private String userId;
    /**
     * 用户名称
     */
    @TableField("user_name")
    private String userName;
    /**
     * 用户头像
     */
    private String face;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号（登陆用）
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 人员状态 0-启用，1-禁用
     */
    private String state;
    /**
     * 备注
     */
    private String remarks;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
