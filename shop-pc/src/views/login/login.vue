<template>
    <div class="logincontainer">
      <el-form
        class="loginform"
        :model="loginModel"
        ref="form"
        :rules="rules"
        :inline="false"
        size="large"
      >
        <el-form-item>
          <div class="logintitle">闲鲤|管理员登录</div>
        </el-form-item>
        <el-form-item prop="username">
          <el-input
            placeholder="请输入账户"
            v-model="loginModel.username"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
          type="password"
            placeholder="请输入密码"
            v-model="loginModel.password"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-row style="width: 100%;">
            <el-col  style="padding-right: 10px; padding-left: 0px" :span="12" :offset="0">
              <el-button style="width: 100%;" type="primary" size="large" @click="commit">登录</el-button>       
            </el-col>
            <el-col style="padding-right: 0px; padding-left: 10px" :span="12" :offset="0">
              <el-button style="width: 100%;" type="primary" size="large" @click="">重置</el-button> 
            </el-col>
          </el-row>
        </el-form-item> 
      </el-form>
    </div>
  </template>


<script setup lang="ts">
    import { FormInstance } from "element-plus";
    import { onMounted, reactive, ref } from "vue";
    import {loginApi} from '@/api/user/index'
    import { useRouter } from "vue-router";
    const router = useRouter()
    //验证码
    const imgsrc = ref('')
    //表单的ref属性
    const form = ref<FormInstance>();
    //表单绑定的对象
    const loginModel = reactive({
    username: "",
    password: ""
    });
    //表单验证规则
    const rules = reactive({
        username:[{required:true,message:'请填写账户',trigger:'change'}],
        password:[{required:true,message:'请填写密码',trigger:'change'}]
    });
    //登录提交
    const commit = ()=>{
        form.value?.validate(async(valid)=>{
            if(valid){
                let res = await loginApi(loginModel)
                console.log(res)
                router.push({path:'/dashboard'})
            }
        })
    }
</script>

<style scoped>
    .logincontainer{
        height:100%;
        background-color:white;
        background-image:url("../../assets/login.png");
        /* 组件布局 */
        display:flex;
        justify-content:center;
        align-items:center;
        .loginform{
            height: 270px;
            width: 420px;
            padding: 20px 35px;
            border-radius: 15px;
            background-color: #fff;
        }
        .logintitle {
            display: flex;
            justify-content: center;
            color: black;
            width: 100%;
            font-size: 24px;
            font-weight: 600;
        }
    }
</style>
