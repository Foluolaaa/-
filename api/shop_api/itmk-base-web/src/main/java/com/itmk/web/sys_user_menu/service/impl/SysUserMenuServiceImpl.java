package com.itmk.web.sys_user_menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.sys_user_menu.entity.AssignParm;
import com.itmk.web.sys_user_menu.entity.SysUserMenu;
import com.itmk.web.sys_user_menu.mapper.SysUserMenuMapper;
import com.itmk.web.sys_user_menu.service.SysUserMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysUserMenuServiceImpl extends ServiceImpl<SysUserMenuMapper,SysUserMenu> implements SysUserMenuService{
    @Override
    @Transactional
    public void saveMenu(AssignParm parm) {
        // 先删除，再插入
        //1、删除
        QueryWrapper<SysUserMenu> query = new QueryWrapper<>();
        query.lambda().eq(SysUserMenu::getUserMenuId, parm.getAssId());
        int delete = this.baseMapper.delete(query);
        //2、删除成功，再插入
        if(delete > 0){
            this.baseMapper.saveMenu(parm.getAssId(), parm.getList());
        }
    }
}
