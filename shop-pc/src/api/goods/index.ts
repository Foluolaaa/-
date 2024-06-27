import http from "@/http";
import { GoodsListParm } from "./GoodsModel";
//列表
export const getGoodsListApi = (parm:GoodsListParm)=>{
    return http.get("/api/goods/list",parm)
}