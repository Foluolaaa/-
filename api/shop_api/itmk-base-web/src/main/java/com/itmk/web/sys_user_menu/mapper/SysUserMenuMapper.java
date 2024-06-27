package com.itmk.web.sys_user_menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itmk.web.sys_user_menu.entity.SysUserMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMenuMapper extends BaseMapper<SysUserMenu> {
    boolean saveMenu(@Param("userId") Long userId, @Param("menuIds") List<Long> menuIds);
}
