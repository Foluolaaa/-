package com.itmk.web.sys_user_menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user_menu")
public class SysUserMenu {
    @TableId(type = IdType.AUTO)
    private Long userMenuId;
    private Long userId;
    private Long menuId;
}
