<template>

  <el-menu
      style="width: 200px; min-height: calc(100vh - 50px)"
      router
  >
    <el-menu-item index="Message"><el-icon><User /></el-icon>学生信息录入</el-menu-item>
    <el-menu-item index="Status"><el-icon><Stamp /></el-icon>学籍管理</el-menu-item>
    <el-sub-menu>
      <template #title>
        <el-icon><Document /></el-icon>
        <span>奖惩管理</span>
      </template>
      <el-menu-item index="Award" title="奖励管理"><el-icon><Check /></el-icon>奖励管理</el-menu-item>
      <el-menu-item index="Punish" title="处罚管理"><el-icon><Close /></el-icon>处罚管理</el-menu-item>
    </el-sub-menu>

  </el-menu>
</template>

<script >
export default {
  name:"AsideBar",
  data(){

  }
}

</script>

<style scoped>

</style>
