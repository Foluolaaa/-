import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Layout from '@/layout/Index.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: "/login",
    component: () => import('@/views/login/login.vue'),
    name: "login"
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: '/dashboard',
        component: () => import('@/layout/dashboard.vue'),
        name: 'dashboard',
        meta: {
          title: '首页',
          icon: '#icondashboard'
        }
      }
    ]
  },
  {
    path: "/system",
    component: Layout,
    name: "system",
    meta: {
      title: "系统管理",
      icon: "Setting",
      roles: ["sys:manage"],
    },
    children: [
      {
        path: "/adminUser",
        component: () => import('@/views/system/AdminUser.vue'),
        name: "adminUser",
        meta: {
          title: "管理员管理",
          icon: "UserFilled",
          roles: ["sys:adminUser"],
        },
      },
      {
        path: "/userList",
        component: () => import('@/views/system/UserList.vue'),
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
    component: Layout,
    name: "goodsRoot",
    meta: {
      title: "商品管理",
      icon: "Setting",
      roles: ["sys:goodsRoot"],
    },
    children: [
      {
        path: "/goodsType",
        component: () => import('@/views/goods/GoodsType.vue'),
        name: "goodsType",
        meta: {
          title: "商品分类",
          icon: "UserFilled",
          roles: ["sys:goodsType"],
        },
      },
      {
        path: "/unusedList",
        component: () => import('@/views/goods/UnusedList.vue'),
        name: "unusedList",
        meta: {
          title: "闲置与求购商品",
          icon: "Wallet",
          roles: ["sys:unusedList"],
        },
      },
      {
        path: "/report",
        component: () => import('@/views/report/Index.vue'),
        name: "report",
        meta: {
          title: "投诉管理",
          icon: "UserFilled",
          roles: ["sys:report"],
        }
      }
    ],
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
