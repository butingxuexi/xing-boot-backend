package cn.izoro.model.entity;

import cn.izoro.common.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户信息表
 * @TableName system_users
 */
@TableName(value ="system_users")
@Data
public class SystemUserDO extends BaseDO {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 微信开放平台id
     */
    private String unionid;

    /**
     * 公众号openId
     */
    private String mpopenid;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户性别
     */
    private Integer sex;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 用户角色:user/admin
     */
    private String userRole;

    /**
     * 账号状态（0正常 1停用）
     */
    private Integer status;

}