package com.itmk.web.goods_category.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.goods_category.entity.GoodsCategory;
import com.itmk.web.goods_category.entity.ListParm;
import com.itmk.web.goods_category.entity.SelectType;
import com.itmk.web.goods_category.service.GoodsCategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class GoodsCategoryCotroller {
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody GoodsCategory goodsCategory){
        if(goodsCategoryService.save(goodsCategory)){
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败");
    }

    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody GoodsCategory goodsCategory) {
        if (goodsCategoryService.updateById(goodsCategory)) {
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除
    @DeleteMapping("/{categoryId}")
    public ResultVo delete(@PathVariable("categoryId") Long cataegoryId) {
        if (goodsCategoryService.removeById(cataegoryId)) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    //列表
    @GetMapping("/list")
    public ResultVo list(ListParm parm){
        //分页对象
        Page<GoodsCategory> page = new Page<>(parm.getCurrentPage(), parm.getPageSize());
        //构造查询条件
        QueryWrapper<GoodsCategory> query = new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(parm.getCategoryName()),
                GoodsCategory::getCategoryName,parm.getCategoryName());
        //查询数据
        IPage<GoodsCategory> list = goodsCategoryService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }

    //小程序分类
    @GetMapping("/getSelectList")
    public ResultVo getSelectList(){
        //查询分类列表
        QueryWrapper<GoodsCategory> query = new QueryWrapper<>();
        query.lambda().orderByAsc(GoodsCategory::getOrderNum);
        List<GoodsCategory> list = goodsCategoryService.list(query);
        //存储小程序需要的类型
        List<SelectType> selectList = new ArrayList<>();
        //构造小程序需要的类型
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .stream()
                .forEach(item -> {
                    SelectType type = new SelectType();
                    type.setLabel(item.getCategoryName());
                    type.setValue(item.getCategoryId());
                    selectList.add(type);
                }); //如果list不为空,则执行stream(),对每个item执行
        return ResultUtils.success("查询成功", selectList);
    }
}
