import http from "@/http";
import { ListParm } from "./ReportModel";
//投诉列表
export const getListApi = (parm:ListParm)=>{
    return http.get("/api/report/getList",parm)
}
//投诉处理
export const doReportApi = (reportId:string)=>{
    return http.post("/api/report/doReport",{reportId:reportId})
}