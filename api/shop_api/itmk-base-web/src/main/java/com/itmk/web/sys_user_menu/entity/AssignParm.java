package com.itmk.web.sys_user_menu.entity;

import lombok.Data;

import java.util.List;

@Data
public class AssignParm {
    private Long assId; // 用户ID
    private List<Long> list; // 用户分配的菜单ID的集合
}
