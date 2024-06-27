package com.itmk.web.sys_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.sys_user.entity.LoginParm;
import com.itmk.web.sys_user.entity.PageParm;
import com.itmk.web.sys_user.entity.SysUser;
import com.itmk.web.sys_user.service.SysUserService;
import com.itmk.web.wx_user.entity.LoginVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/sysUser") // 所有以"/api/sysUser"开头的请求，都会通过SysUserController进行处理
public class SysUserController {
    // Spring的依赖注入的注解
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    // 新增
    @PostMapping // 用于将HTTP POST请求映射到这个方法上
    public ResultVo add(@RequestBody SysUser sysUser){ // ResultVo 数据返回工具类
        // 接收 Service 的结果，然后返回给前端
        if(sysUserService.save(sysUser)){
            return ResultUtils.success("新增成功！");
        }
        return ResultUtils.error("新增失败");
    }

    // 编辑
    @PutMapping
    public ResultVo edit(@RequestBody SysUser sysUser){
        if(sysUserService.updateById(sysUser)){
            return ResultUtils.success("编辑成功！");
        }
        return ResultUtils.error("编辑失败");
    }

    // 编辑
    @DeleteMapping("/{userId}")
    public ResultVo delete(@PathVariable("userId") Long userId){ // 将路径变量userId绑定到方法参数
        if(sysUserService.removeById(userId)){
            return ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("删除失败");
    }

    //列表
    @GetMapping("/getList")
    public ResultVo getList(PageParm parm){
        // 1、构造数据库分页查询条件
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        // ----通过LIKE查询进行模糊匹配 相当于查询 所有SysUser的nickName中包含parm_nickName的。
        // ----首先判断查询内容是否为空 使用Lambda表达式 如果不为空则进行 like查询
        query.lambda().like(StringUtils.isNotEmpty(parm.getNickName()),SysUser::getNickName,parm.getNickName());
        // 2、构造分页查询对象
        // ----Page<查询的实体类型>是MyBatis-Plus中的一个类，用于封装分页查询的参数和结果。
        IPage<SysUser> page = new Page<>(parm.getCurrentPage(),parm.getPageSize());
        //查询
        IPage<SysUser> list = sysUserService.page(page, query);
        return ResultUtils.success("查询成功",list);
    }

    //生成验证码
    @PostMapping("/image")
    public ResultVo imageCode(HttpServletRequest request){
        // 生成验证码 1234
        String text = defaultKaptcha.createText();
        // 存到session
        HttpSession session = request.getSession();
        session.setAttribute("code",text);
        // 将数字转换为验证码图片，转换为base64
        BufferedImage bufferedImage = defaultKaptcha.createImage(text);
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encode(outputStream.toByteArray());
            String captchaBase64 = "data:image/jpeg;base64," + base64.replaceAll("\r\n", "");
            ResultVo result = new ResultVo("生成成功",200,captchaBase64);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //登录
    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginParm parm){
        //验证用户信息
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername, parm.getUsername())
                .eq(SysUser::getPassword, parm.getPassword());
        SysUser user = sysUserService.getOne(query);
        if(user == null){
            return ResultUtils.error("用户名或密码错误");
        }
        if(user.getStatus().equals("1")){
            return ResultUtils.error("账户被停用，请联系管理员!");
        }
        //返回登录信息
        LoginVo vo = new LoginVo();
        vo.setUserId(user.getUserId());
        vo.setNickName(user.getNickName());
        return ResultUtils.success("登录成功",vo);
    }
}
