<template>
	<view class="u-wrap">
		<!-- 轮播图 -->
		<swiper class="swiper-container" circular :indicatorColor="indicatorColor" :indicator-dots="indicatorDots"
			:autoplay="autoplay" :interval="interval" :duration="duration">
			<swiper-item @click="toDetail(item.goods)" v-for="(item,index) in swipperList" :key='index'>
				<image class="imgs" :src="item.images.split(',')[0]"></image>
			</swiper-item>
		</swiper>
		<!-- 搜索框 -->
		<view class="tab-strickt">
			<u-search v-model="keywords" @change="searchBtn" bg-color="#FFF" margin="8px" style="flex-grow: 1;" :show-action="true" action-text="搜索" :animation="true"></u-search>
		</view>
		<view class="">
			<u-waterfall v-model="flowList" ref="uWaterfall1">
				<template v-slot:left="{leftList}">
					<view class="demo-warter-left" v-for="(item, index) in leftList" :key="index">
						<!-- 警告：微信小程序中需要hx2.8.11版本才支持在template中结合其他组件，比如下方的lazy-load组件 -->
						<u-lazy-load @click="toDetail(item)" threshold="-450" border-radius="10" :image="item.image" :index="index">
						</u-lazy-load>
						<view class="demo-title">
							{{item.goodsName}}
						</view>
						<view class="demo-price">
							{{item.goodsPrice}}元
						</view>
						<view class="demo-tag">
							<view v-if="item.type == '0'" class="demo-tag-owner">
								闲置
							</view>
							<view style="margin-left: 0;" v-else class="demo-tag-text">
								求购
							</view>
						</view>
					</view>
				</template>
				<template v-slot:right="{rightList}">
					<view class="demo-warter-right" v-for="(item, index) in rightList" :key="index">
						<u-lazy-load @click="toDetail(item)" threshold="-450" border-radius="10" :image="item.image" :index="index">
						</u-lazy-load>
						<view class="demo-title">
							{{item.goodsName}}
						</view>
						<view class="demo-price">
							{{item.goodsPrice}}元
						</view>
						<view class="demo-tag">
							<view v-if="item.type == '0'" class="demo-tag-owner">
								闲置
							</view>
							<view style="margin-left: 0;" v-else class="demo-tag-text">
								求购
							</view>
						</view>
					</view>
				</template>
			</u-waterfall>
			<u-loadmore bg-color="rgb(240, 240, 240)" :status="loadStatus" @loadmore="loadMore"></u-loadmore>
		</view>
	</view>
</template>

<script setup>
	import { onReady,onReachBottom } from '@dcloudio/uni-app';
import {
		ref
	} from 'vue';
	import {getIndexBannerApi,getIndexListApi} from '../../api/index.js'
	const indicatorDots = ref(true)
	const indicatorColor = ref("#FFF")
	const autoplay = ref(true)
	const interval = ref(2000)
	const duration = ref(500)
	const uWaterfall1 = ref()
	//轮播图数据
	const swipperList = ref([])
	//瀑布流
	const flowList = ref([])
	//轮播图数据
	const getBannerList = async()=>{
		let res = await getIndexBannerApi()
		if(res && res.code == 200){
			console.log(res)
			swipperList.value = res.data;
		}
	}
	//热推数据
	const loadStatus = ref('loadmore')
	const currentPage = ref(1)
	const pageSize = ref(10)
	const keywords = ref('')
	const pages = ref(0)
	const getIndexList = async()=>{
		let res = await getIndexListApi({
			currentPage:currentPage.value,
			pageSize:pageSize.value,
			keywords:keywords.value
		})
		if(res && res.code == 200){
			console.log(res)
			flowList.value = flowList.value.concat(res.data.records)
			pages.value = res.data.pages;
		}
	}
	//关键字搜索
	const searchBtn = ()=>{
		currentPage.value = 1;
		//清空瀑布流的数据
		uWaterfall1.value.clear()
		//获取数据
		getIndexList()
	}
	//加载更多
	const loadMore = ()=>{
		console.log('加载更多')
		if(currentPage.value >= pages.value){
			loadStatus.value = 'nomore'
			return;
		}
		loadStatus.value = 'loading'
		//页数加1
		currentPage.value = ++currentPage.value;
		//获取数据
		getIndexList()
	}
	//触底加载
	onReachBottom(()=>{
		console.log('触底加载')
		if(currentPage.value >= pages.value){
			loadStatus.value = 'nomore'
			return;
		}
		loadStatus.value = 'loading'
		//页数加1
		currentPage.value = ++currentPage.value;
		//获取数据
		getIndexList()
	})
	//跳转详情页
	const toDetail = (item)=>{
		if(item.type == '0'){
			uni.navigateTo({
				url:"../unused_detail/unused_detail?goods=" + JSON.stringify(item)
			})
		}else{
			uni.navigateTo({
				url:"../buy_detail/buy_detail?goods=" + JSON.stringify(item)
			})
		}
	}
	onReady(()=>{
		getBannerList()
		getIndexList()
	})
</script>

<style lang='scss'>
	.swiper-container {
		height: 180px;

		.item {
			height: 180px;
		}

		.imgs {
			height: 180px;
			width: 100%;
		}
	}

	.demo-warter-left {
		border-radius: 8px;
		margin: 5px 0px 5px 5px;
		background-color: #ffffff;
		padding: 8px;
		position: relative;
	}

	.demo-warter-right {
		border-radius: 8px;
		margin: 5px 5px 5px 0px;
		background-color: #ffffff;
		padding: 8px;
		position: relative;
	}

	.u-close {
		position: absolute;
		top: 32rpx;
		right: 32rpx;
	}

	.demo-image {
		width: 100%;
		border-radius: 4px;
	}

	.demo-title {
		font-size: 30rpx;
		margin-top: 5px;
		color: $u-main-color;
	}

	.demo-tag {
		display: flex;
		margin-top: 5px;
	}

	.demo-tag-owner {
		background-color: $u-type-error;
		color: #FFFFFF;
		display: flex;
		align-items: center;
		padding: 4rpx 14rpx;
		border-radius: 50rpx;
		font-size: 20rpx;
		line-height: 1;
	}

	.demo-tag-text {
		border: 1px solid $u-type-primary;
		color: $u-type-primary;
		margin-left: 10px;
		border-radius: 50rpx;
		line-height: 1;
		padding: 4rpx 14rpx;
		display: flex;
		align-items: center;
		border-radius: 50rpx;
		font-size: 20rpx;
	}

	.demo-price {
		font-size: 30rpx;
		color: $u-type-error;
		margin-top: 5px;
	}

	.demo-shop {
		font-size: 22rpx;
		color: $u-tips-color;
		margin-top: 5px;
	}

	.tab-strickt {
		position: sticky;
		z-index: 99;
		top: 0;
		left: 0;
		display: flex;
		align-items: center;
		background-color: #f2f2f2;
	}
</style>
