<template>
    <el-main>
      <!-- 搜索栏 -->
      <el-form :model="searchParm" :inline="true" size="default">
        <el-form-item>
          <el-input
            placeholder="请输入商品名称"
            v-model="searchParm.goodsName"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="searchBtn" icon="Search">搜索</el-button>
          <el-button @click="resetBtn" type="danger" plain icon="Close">重置</el-button>
        </el-form-item>
      </el-form>
      <!-- 表格 -->
      <el-table :height="tableHeight" :data="tableList" border stripe>
        <el-table-column prop="image" label="商品图片">
          <template #default="scope">
            <el-image
              v-if="scope.row.image"
              style="width: 100px; height: 60px"
              :src="scope.row.image.split(',')[0]"
            />
          </template>
        </el-table-column>
        <el-table-column prop="goodsName" label="商品名称"></el-table-column>
        <el-table-column prop="type" label="类型">
          <template #default="scope">
            <el-tag
              type="danger"
              v-if="scope.row.type == '0'"
              size="default"
              effect="dark"
              >闲置</el-tag
            >
            <el-tag
              type="success"
              v-if="scope.row.type == '1'"
              size="default"
              effect="dark"
              >求购</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column prop="goodsPrice" label="商品价格"></el-table-column>
        <el-table-column prop="goodsDesc" label="商品描述"></el-table-column>
        <el-table-column prop="userName" label="发布人"></el-table-column>
        <el-table-column prop="phone" label="发布人电话"></el-table-column>
        <el-table-column prop="wxNum" label="发布人微信"></el-table-column>
        <el-table-column prop="address" label="商品地址"></el-table-column>
        <el-table-column prop="status" label="状态">
          <template #default="scope">
            <el-tag
              type="success"
              v-if="scope.row.status == '0'"
              size="default"
              effect="dark"
              >上架</el-tag
            >
            <el-tag
              type="success"
              v-if="scope.row.status == '1'"
              size="default"
              effect="dark"
              >下架</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column prop="setIndex" label="推荐首页">
          <template #default="scope">
            <el-tag
              type="danger"
              v-if="scope.row.setIndex == '0'"
              size="default"
              effect="dark"
              >未推荐</el-tag
            >
            <el-tag
              type="success"
              v-if="scope.row.setIndex == '1'"
              size="default"
              effect="dark"
              >已推荐</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column prop="sellStatus" label="出售状态">
          <template #default="scope">
            <el-tag
              type="danger"
              v-if="scope.row.sellStatus == '0'"
              size="default"
              effect="dark"
              >未出售</el-tag
            >
            <el-tag
              type="success"
              v-if="scope.row.sellStatus == '1'"
              size="default"
              effect="dark"
              >已出售</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button
              type="danger"
              icon="Delete"
              size="default"
              @click="deleteBtn(scope.row.goodsId)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <el-pagination
        @size-change="sizeChange"
        @current-change="currentChange"
        :current-page.sync="searchParm.currentPage"
        :page-sizes="[10, 20, 40, 80, 100]"
        :page-size="searchParm.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="searchParm.total"
        background
      >
      </el-pagination>
    </el-main>
  </template>
  
  <script setup lang="ts">
  import { onMounted, reactive, ref, nextTick } from "vue";
  import { getGoodsListApi } from "@/api/goods/index";
  //搜索栏绑定对象
  const searchParm = reactive({
    currentPage: 1,
    pageSize: 10,
    goodsName: "",
    total: 0,
  });
  //表格数据
  const tableList = ref([]);
  //获取表格数据
  const getGoodsList = async () => {
    let res = await getGoodsListApi(searchParm);
    if (res && res.code == 200) {
      console.log(res);
      tableList.value = res.data.records;
      searchParm.total = res.data.total;
    }
  };
  //删除
  const deleteBtn = (goodsId: string) => {
    console.log(goodsId);
  };
  //页容量改变时触发
  const sizeChange = (size: number) => {
    searchParm.pageSize = size;
    getGoodsList();
  };
  //页数改变时触发
  const currentChange = (page: number) => {
    searchParm.currentPage = page;
    getGoodsList();
  };
  //重置
  const resetBtn = ()=>{
      searchParm.goodsName = '';
      getGoodsList();
  }
  //搜索
  const searchBtn = ()=>{
      getGoodsList();
  }
  //表格高度
  const tableHeight = ref(0);
  onMounted(() => {
    getGoodsList();
    nextTick(() => {
      //计算表格高度
      tableHeight.value = window.innerHeight - 200;
    });
  });
  </script>
  
  <style scoped></style>
  