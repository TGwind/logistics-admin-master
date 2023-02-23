<template>
<!--  <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">-->
<!--    <el-radio-button :label="false">expand</el-radio-button>-->
<!--    <el-radio-button :label="true">collapse</el-radio-button>-->
<!--  </el-radio-group>-->
  <el-menu
      router
  >
    <el-menu-item index="Echarts">首页</el-menu-item>

    <el-sub-menu index="1">
      <template #title>
        <span>运单追踪</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="sendOut">我寄的</el-menu-item>
        <el-menu-item index="receive">我收的</el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>
    <el-menu-item index="tosend">我要寄件</el-menu-item>


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
