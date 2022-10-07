<template>
  <div class="shopping-classify">
    <div style="width: 300px ;height: 100px; text-align: center ;margin:80px auto">
      <div style="text-align: center;margin:30px auto; font-size:30px ">欢迎登录</div>
      <el-form ref="form" :model="form" size="large">
        <el-form-item prop="username">
          <el-input v-model="form.userName" autocomplete="off" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.userPassword" show-password autocomplete="new-password"
                    placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="login">登 录</el-button>
        </el-form-item>
      </el-form>
    </div>

  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  name: "Login",
  data() {
    return {
      bgImg: {
        backgroundImage: "url(" + require("../assets/Login.jpg") + ")",
        backgroundRepeat: "no-repeat",
        backgroundSize: '100% auto'
      },
      form: {
        userName: null,
        userPassword: null,
      }
    }
  },
  methods: {
    login() {
      request.post("http://localhost:8888/login", this.form).then(res => {
        if (res.data !== null) {
          this.$message({
            type: "success",
            message: "登录成功",
          })
          this.$router.push("Main")
        } else {
          this.$message.error("用户名或密码错误")
        }
      })
    }
  }
}
</script>


<style scoped>
/*在最外层的div加上这个class，可以解决背景图片只展示一半的问题*/
.shopping-classify {
  display: flex;
  justify-content: center;
  align-items: stretch;
  background-image: url('../assets/Login.jpg');
  background-size: 100% 100%;
  height: 100%;
  width: 100%;
  position: fixed;
}

</style>
