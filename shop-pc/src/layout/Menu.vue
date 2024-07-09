<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-vertical-demo"
    unique-opened
    background-color="#304156"
    router
  >
    <MenuItem :menuList="menuList"></MenuItem>
  </el-menu>
</template>
<script setup lang="ts">
import {  reactive,computed } from 'vue'
import MenuItem from "@/layout/MenuItem.vue";
import { useRoute } from 'vue-router';
const route = useRoute();
//获取激活的菜单
const activeIndex = computed(()=>{
    const {path} = route;
    return path;
})


let menuList = reactive([
  {
    path: "/dashboard",
    component: "Layout",
    name: "dashboard",
    meta: {
      title: "首页",
      icon: "HomeFilled",
      roles: ["sys:dashboard"],
    },
  },
  {
    path: "/system",
    component: "Layout",
    name: "system",
    meta: {
      title: "系统管理",
      icon: "Setting",
      roles: ["sys:manage"],
    },
    children: [
      {
        path: "/adminUser",
        component: "/system/AdminUser",
        name: "adminUser",
        meta: {
          title: "管理员管理",
          icon: "UserFilled",
          roles: ["sys:adminUser"],
        },
      },
      {
        path: "/userList",
        component: "/system/UserList",
        name: "userList",
        meta: {
          title: "用户管理",
          icon: "Wallet",
          roles: ["sys:userList"],
        },
      }
    ],
  },
  {
    path: "/goodsRoot",
    component: "Layout",
    name: "goodsRoot",
    meta: {
      title: "商品管理",
      icon: "Setting",
      roles: ["sys:goodsRoot"],
    },
    children: [
      {
        path: "/goodsType",
        component: "/goods/GoodsType",
        name: "goodsType",
        meta: {
          title: "商品分类",
          icon: "UserFilled",
          roles: ["sys:goodsType"],
        },
      },
      {
        path: "/unusedList",
        component: "/goods/UnusedList",
        name: "unusedList",
        meta: {
          title: "闲置与求购商品",
          icon: "Wallet",
          roles: ["sys:unusedList"],
        },
      },
      {
        path: "/report",
        component: "/goods/report",
        name: "report",
        meta: {
          title: "投诉处理",
          icon: "UserFilled",
          roles: ["sys:report"],
        },
      }
    ],
  }
]);
</script>
<style scoped lang="scss">
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 230px;
  min-height: 400px;
}
.el-menu {
  border-right: none;
}

:deep(.el-sub-menu .el-sub-menu__title) {
  color: #f4f4f5 !important;
}

:deep(.el-menu .el-menu-item) {
  color: #bfcbd9;
}
/* 菜单点中文字的颜色 */

:deep(.el-menu-item.is-active) {
  color: #409eff !important;
}
/* 当前打开菜单的所有子菜单颜色 */

:deep(.is-opened .el-menu-item) {
  background-color: #1f2d3d !important;
}
/* 鼠标移动菜单的颜色 */

:deep(.el-menu-item:hover) {
  background-color: #001528 !important;
}
</style>
