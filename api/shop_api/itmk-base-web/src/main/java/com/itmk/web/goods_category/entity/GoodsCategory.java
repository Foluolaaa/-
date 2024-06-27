package com.itmk.web.goods_category.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("goods_category")
public class GoodsCategory {
    @TableId(type = IdType.AUTO)
    private Long categoryId;    //商品分类ID号
    private String categoryName;    //商品分类名
    private Integer orderNum;   //商品订单号
}
