<template>
<div style="margin: 50px 50px;">
  <el-form label-position="top">
    <div style="float: left;">
      <el-form-item label="寄件人" >
        <el-input  style="width: 100px"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input  style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="发件地址">
        <el-input  style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="发件日期">
        <el-input  style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="收件人">
        <el-input  style="width: 100px"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input  style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="收件地址">
        <el-input  style="width: 200px"></el-input>
      </el-form-item>
    </div>
    <div style="float: left;margin-left: 50px">
      <el-form-item label="快递描述" >
        <el-input  style="width: 200px;"></el-input>
      </el-form-item>
      <el-form-item label="快递质量(kg)" >
        <el-input  style="width: 200px;"></el-input>
      </el-form-item>
      <el-form-item label="承运方" >
        <el-input  style="width: 100px;"></el-input>
      </el-form-item>
      <el-form-item label="备注" >
        <el-input  style="width: 200px;"></el-input>
      </el-form-item>

      <el-button type="primary" @click="dialog">
        提交订单
      </el-button>
    </div>
    <div style="float: left;margin-left: 50px">
      <img src="http://myblog.over2022.top/20230222192715.png" style="width: auto"/>
    </div>

    <el-dialog title="支付费用" v-model="dialogVisiable" width="300px">
      <img src="http://myblog.over2022.top/f5de99eba224c54f6b1da5121bd6007.jpg" style="width: 100%;height: auto"/>

      <div class="dialog-footer" style="margin: 10px 100px;">
            <el-button @click="dialogVisiable = false" type="danger" size="large">取 消</el-button>
<!--            <el-button type="primary" @click="alterConfirm">确 定</el-button>-->
      </div>
    </el-dialog>

<!--    <el-form-item label="院系">-->
<!--      <el-select v-model="student.dept" style="width: 50%" placeholder="请选择院系">-->
<!--        <el-option-->
<!--            v-for="item in dept"-->
<!--            :key="item.value"-->
<!--            :label="item.label"-->
<!--            :value="item.value"-->
<!--        />-->
<!--      </el-select>-->
<!--    </el-form-item>-->
<!--    <el-form-item label="班级">-->
<!--      <el-select v-model="student.lass" style="width: 50%" placeholder="请选择班级">-->
<!--        <el-option-->
<!--            v-for="item in lass"-->
<!--            :key="item.value"-->
<!--            :label="item.label"-->
<!--            :value="item.value"-->
<!--        />-->
<!--      </el-select>-->
<!--    </el-form-item>-->
<!--    <el-form-item label="生日">-->
<!--      <el-date-picker-->
<!--          v-model="student.birth"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择日期">-->
<!--      </el-date-picker>-->
<!--    </el-form-item>-->
<!--    <el-form-item label="籍贯">-->
<!--      <el-input v-model="student.nativePlace" style="width: 50%"></el-input>-->
<!--    </el-form-item>-->
  </el-form>
</div>
</template>

<script>
export default {
  name: "tosend",
  data () {
    return{
      dialogVisiable: false,


    }
  },
  methods: {
    dialog(){
      this.dialogVisiable=true
    },
    submit(){

    }
  }

}
</script>

<style scoped>

</style>
