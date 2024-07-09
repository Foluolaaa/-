// 可选参数：？

//列表查询数据类型
export type GoodsListParm = {
    currentPage:number,
    pageSize:number,
    goodsName:string,
    total?:number
}

//列表查询数据类型
export type UpdateParm = {
    goodsId:string,
    status?:string,
    setIndex?:string
}