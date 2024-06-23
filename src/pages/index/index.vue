<template>
	<view class="u-wrap">
		<swiper class="swiper-container" circular :indicator-dots="indicatorDots" :autoplay="autoplay"
			:interval="interval" :duration="duration">
			<swiper-item v-for="(item, index) in swipperList">
				<image class="imgs" :src="item.image"></image>
			</swiper-item>

		</swiper>
	</view>
</template>

<script setup>
	import {
		ref
	} from 'vue';
	const indicatorDots = ref(true)
	const autoplays = ref(true)
	const intervals = ref(2000)
	const durations = ref(500)
	// 轮播图数据
	const swipperList = ref([{
		image: '/static/image/swiper1.png'
	}, {
		image: '/static/image/swiper2.jpg'
	}, {
		image: '/static/image/swiper3.jpg'
	}])
</script>

<style lang='scss'>
	.swiper-container {
		height: 180px;

		.item {
			height: 180px;
		}
	}
</style>