package com.itmk.web.sys_user.entity;

import lombok.Data;

@Data
public class PageParm {
    private Long currentPage; // 当前页
    private Long pageSize; // 每页查询的条数
    private String nickName; //根据昵称查询
}
