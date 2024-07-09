<template>
    <el-container class="mycontainer">
        <el-aside width="auto" class="asside">
            <Menu></Menu>
        </el-aside>
        <el-container>
          <el-header class="header">
            <div class="header-left">
                <Collapse></Collapse>
                <BreadCrumb></BreadCrumb>
            </div>
            <LoginOut></LoginOut>
          </el-header>
          <el-main class="mymain">
            <router-view></router-view>
          </el-main>
        </el-container>
    </el-container>
 </template>
 
 <script setup lang="ts">
    import Menu from './Menu.vue'
    import LoginOut from "./LoginOut.vue";
    import BreadCrumb from "./BreadCrumb.vue";
 </script>
 
 <style scoped lang="scss">
 .mycontainer{
     height: 100%;
    .asside{
        background-color: #304156;
     }
    .header{
        background-color: #011a29;
        display: flex;
        align-items: center;
        color: #FFF;

        justify-content: space-between;
        .header-left{
            display: flex;
            align-items: center;
        }
    } 
    .mymain {
        padding: 0px;
    } 
 }
 </style>