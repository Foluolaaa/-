<template>
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
      <el-table-column prop="reason" label="投诉原因"></el-table-column>
      <el-table-column prop="nickName" label="投诉人"></el-table-column>
      <el-table-column prop="reportTime" label="投诉时间"></el-table-column>
      <el-table-column  label="处理状态" width="100">
        <template #default="scope">
            <el-tag v-if="scope.row.status == '1'" type="success" size="normal"  effect="dark"  @close="">已处理</el-tag>
            <el-tag v-else type="danger" size="normal"  effect="dark"  @close="">未处理</el-tag>
            <!-- <el-tag v-if="scope.row.status == '0'" type="danger" size="normal"  effect="dark"  @close="">未处理</el-tag>
            <el-tag v-else type="success" size="normal"  effect="dark"  @close="">已处理</el-tag> -->
        </template>
      </el-table-column>
      <el-table-column  label="操作" align="center" width="150">
        <template #default="scope">
            <el-button icon="Edit" type="success" size="default" @click="doBtn(scope.row.reportId)">处理</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
        @size-change="sizeChange"
        @current-change="currentChange"
        :current-page.sync="parm.currentPage"
        :page-sizes="[10,20, 40, 80, 100]"
        :page-size="parm.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="parm.total" background>
    </el-pagination>
    
</template>

<script setup lang="ts">
import { getListApi,doReportApi } from '@/api/report';
import useInstance from '@/hooks/useInstance';
import { nextTick, onMounted, reactive, ref } from 'vue';
//获取全局属性
const {global} = useInstance()
//列表参数
const parm = reactive({
    currentPage:1,
    pageSize:10,
    total:0
})
//定义表格数据
const tableList = ref([])
//获取列表
const getList = async()=>{
    let res = await getListApi(parm)
    if(res && res.code == 200){
        console.log(res)
        tableList.value = res.data.records
        parm.total = res.data.total;
    }
}
//处理
const doBtn = async(reportId:string)=>{
    console.log(reportId)
    const confirm = await global.$myconfirm('确定处理投诉吗?')
    if(confirm){
        let res = await doReportApi(reportId)
        if(res && res.code == 200){
            getList()
        }
    }
}
//页容量改变时触发
const sizeChange = (size:number)=>{
    parm.pageSize = size;
    getList()
}
//页数改变时触发
const currentChange = (page:number)=>{
    parm.currentPage = page;
    getList()
}
//表格高度
const tableHeight = ref(0)
onMounted(()=>{
    nextTick(()=>{
        tableHeight.value = window.innerHeight -120
    })
    getList()
})
</script>

<style scoped>

</style>