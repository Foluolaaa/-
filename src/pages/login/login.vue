<template>
	<view class="u-p-t-40 u-p-r-60 u-p-b-30 u-p-l-60 logincontainer">
		<u-avatar size="140" src="/static/user.jpg" mode="circle"></u-avatar>
		<u-form class="forms" :model="loginModel" ref="form1">
			<u-form-item left-icon="account-fill" left-icon-style="font-size:24px;color:#FF7670;">
				<u-input placeholder="请输入账户" v-model="loginModel.username" />
			</u-form-item>
			<u-form-item left-icon="lock" left-icon-style="font-size:24px;color:#FF7670;">
				<u-input placeholder="请输入密码" v-model="loginModel.password" />
			</u-form-item>
			<u-button @click="toCommit" :custom-style="customStyle1">登录</u-button>
			<u-button @click="toRegister" type="success" :custom-style="customStyle1">注册</u-button>
			<u-button @click="toForgetPassword" :custom-style="customStyle1">忘记密码</u-button>
		</u-form>
	</view>
</template>
<script setup>
	import {
		reactive
	} from 'vue';
	import {
		loginApi
	} from '../../api/user.js'
	const loginModel = reactive({
		username: '',
		password: ''
	})
	const customStyle1 = reactive({
		marginTop: '40px',
		background: '#FF7670',
		color: '#FFF',
		width: '100%'
	})
	const customStyle2 = reactive({
		marginTop: '35px',
		// background:'#FF7670',
		color: '#FFF',
		width: '100%'
	})
	//去注册
	const toRegister = () => {
		uni.navigateTo({
			url: '../register/register'
		})
	}
	//去忘记密码
	const toForgetPassword = () => {
		uni.navigateTo({
			url: '../forget_password/forget_password'
		})
	}
	//登录
	const toCommit = async () => {
		if (!loginModel.username) {
			uni.showToast({
				title: '请输入账号',
				icon: 'none',
				duration: 2000
			})
			return;
		}
		if (!loginModel.password) {
			uni.showToast({
				title: '请输入密码',
				icon: 'none',
				duration: 2000
			})
			return;
		}
		let res = await loginApi(loginModel)
		if (res && res.code == 200) {
			//存储用户id
			console.log(res)
			uni.setStorageSync('userId', res.data.userId)
			//跳转首页
			uni.switchTab({
				url: '../unused/unused'
			})
		}
	}
</script>
<style lang="scss">
	.logincontainer {
		height: 100%;
		display: flex;
		align-items: center;
		flex-direction: column;

		.forms {
			width: 100%;
		}
	}

	.passtext {
		display: flex;
		justify-content: flex-end;
		color: #FF7670;
		margin-top: 15px;
	}
</style>