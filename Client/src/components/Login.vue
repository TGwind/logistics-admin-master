<template>
  <div class="shopping-classify">
    <div style="width: 300px ;height: 100px; text-align: center ;margin:80px auto">
      <div style="text-align: center;margin:30px auto; font-size:30px ">欢迎登录</div>
      <el-form ref="form" :model="form" size="large">
        <el-form-item>
          <el-select v-model="form.permission" class="m-2" placeholder="选择登录身份">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item prop="name">
          <el-input v-model="form.name" autocomplete="off" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" show-password autocomplete="new-password"
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
        name: null,
        password: null,
        permission: null,
      },
      options: [
        {
          value: 0,
          label: '普通用户',
        },
        {
          value: 1,
          label: '快递员',
        },
        {
          value: 2,
          label: '管理员',
        },
      ],
    }
  },
  methods: {
    login() {
      // console.log(this.form)
      request.post("/login", this.form).then(res => {
        // console.log(res)
        if ("0" === res.code) {
          this.$message({
            type: "success",
            message: "登录成功",
          })
          this.$router.push("/Echarts")
        } else {
          this.$message.error("用户名或密码错误")
        }
      })

    }
  }
}
</script>


<style scoped>
.m-2{
  width: ;
}
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
