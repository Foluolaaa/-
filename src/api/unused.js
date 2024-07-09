import http from '../common/http.js'
//收藏
export const collectApi = (parm)=>{
	return http.post('/api/collect/collect',parm)
}
//是否收藏
export const hasCollectApi = (parm)=>{
	return http.get('/api/collect/hasCollect',parm)
}
//举报
export const reportApi = (parm)=>{
	return http.post('/api/report/report',parm)
}
//是否举报
export const hasReportApi = (parm)=>{
	return http.get('/api/report/hasReport',parm)
}