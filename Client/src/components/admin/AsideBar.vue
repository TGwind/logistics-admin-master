<template>
  <!--  <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">-->
  <!--    <el-radio-button :label="false">expand</el-radio-button>-->
  <!--    <el-radio-button :label="true">collapse</el-radio-button>-->
  <!--  </el-radio-group>-->
  <el-menu
      router
  >
    <el-menu-item index="/admin/adminEcharts">首页</el-menu-item>
    <el-menu-item index="/admin/deliverMessage">快递员信息管理</el-menu-item>
    <el-menu-item index="/admin/checkBill">查看账单</el-menu-item>


  </el-menu>
</template>

<script>
export default {
  name:"AsideBar",
  data(){
    return{
      isCollapse: false
    }

  }
}


</script>

<style scoped>
</style>
