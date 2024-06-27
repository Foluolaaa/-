//定义用户的数据类型:interface 和 type 
export type User = {
    userId:string,
    username:string,
    password:string,
    nickName:string,
    phone:string,
    sex:string,
    status:string
}

//定义列表查询的参数
export type ListParm = {
    currentPage:number,
    pageSize:number,
    nickName:string
}

//登录参数
export type LoginParm = {
    username:string,
    password:string
}