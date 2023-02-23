<template>
  <!--  页头-->
  <nav style=" font-size: x-large;border-bottom:1px solid #acc0d4  ">
    <el-row>
      <el-col :span="4"><img src="../assets/logo_01.png" alt="湖南科技大学" class="img1"></el-col>
      <el-col :span="16" style="text-align:center;line-height: 60px">物流管理系统</el-col>
      <el-col :span="1">
        <el-button type="danger" style="margin-top: 20px;" @click="closeAccount">退出登录</el-button>
      </el-col>
    </el-row>
  </nav>
  <!-- 总体布局-->
  <div>
    <el-container>
      <!--      侧边栏导航区域-->
      <div style="border-right: solid 1px #acc0d4;height: 800px">
        <el-aside width="200px" height="100%">
          <AsideBar/>
        </el-aside>
      </div>
      <!--      页面内容区域-->
      <el-main style="padding:0 0;">
        <router-view/>
      </el-main>
    </el-container>
  </div>


</template>

<script>
import AsideBar from "@/components/AsideBar"

export default {
  name: "main",
  components: {AsideBar},
  created() {
    // this.$router.push("Echarts")
  },
  methods: {
    closeAccount() {
      this.$router.push("/")
    }
  }
}
</script>

<style scoped>
.img1 {
  width: 200px;
  height: 60px;

}
</style>
