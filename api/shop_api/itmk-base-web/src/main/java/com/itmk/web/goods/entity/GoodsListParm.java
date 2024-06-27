package com.itmk.web.goods.entity;

import lombok.Data;

@Data
public class GoodsListParm {
    private Long currentPage;
    private Long pageSize;
    private String goodsName; // 能够根据商品名称进行查询
}
