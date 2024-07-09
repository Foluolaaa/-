<template>
    <el-dropdown>
    <span class="el-dropdown-link">
      <img class="userimg" src="@/assets/user.jpg" />
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item @click="loginBtn">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import useInstance from '@/hooks/useInstance';
const router = useRouter()
//获取全局属性
const {global} = useInstance()
//退出登录按钮
const loginBtn = async()=>{
    const confirm = await global.$myconfirm('确定退出登录吗?')
    if(confirm){
        //清空缓存
        sessionStorage.clear()
        //跳转登录
        router.push({name:'login'})
    }
}
</script>

<style scoped>
.userimg{
    height: 55px;
    width: 55px;
    border-radius: 50%;
}
</style>