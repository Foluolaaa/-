package com.itmk.web.sys_user_menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.sys_user_menu.entity.AssignParm;
import com.itmk.web.sys_user_menu.entity.SysUserMenu;

public interface SysUserMenuService extends IService<SysUserMenu> {
    void saveMenu(AssignParm parm);
}
