<template>
  <div style="float:left;margin-top: 30px;width: 30%">
    <el-form ref="toSendForm"
             :model="toSendForm"
             :rules="rules"
             vlabel-position="top"
             label-width="120px">
        <el-form-item prop="sender" label="寄件人">
          <el-input style="width: 100px" v-model="toSendForm.sender"></el-input>
        </el-form-item>
        <el-form-item prop="senderNumber" label="手机号">
          <el-input style="width: 200px" v-model="toSendForm.senderNumber"></el-input>
        </el-form-item>
        <el-form-item prop="sendAddress" label="发件地址">
          <el-input style="width: 200px" v-model="toSendForm.sendAddress"></el-input>
        </el-form-item>
        <el-form-item prop="sendDate" label="发件日期">
          <el-date-picker
              v-model="toSendForm.sendDate"
              type="datetime"
              value-format="YYYY-MM-DD"
              format="YYYY-MM-DD"
              placeholder="选择日期"
          />
        </el-form-item>
        <el-form-item prop="receiver" label="收件人">
          <el-input style="width: 100px" v-model="toSendForm.receiver"></el-input>
        </el-form-item>
        <el-form-item prop="receiverNumber" label="手机号">
          <el-input style="width: 200px" v-model="toSendForm.receiverNumber"></el-input>
        </el-form-item>
        <el-form-item prop="receiveAddress" label="收件地址">
          <el-input style="width: 200px" v-model="toSendForm.receiveAddress"></el-input>
        </el-form-item>
        <el-form-item prop="content" label="快递描述">
          <el-input style="width: 200px;" v-model="toSendForm.content"></el-input>
        </el-form-item>
        <el-form-item prop="weight" label="快递质量(kg)">
          <el-input style="width: 200px;" v-model="toSendForm.weight"></el-input>
        </el-form-item>
        <el-form-item prop="logistics" label="承运方">
          <el-input style="width: 100px;" v-model="toSendForm.logistics"></el-input>
        </el-form-item>
        <el-form-item prop="note" label="备注">
          <el-input style="width: 200px;" v-model="toSendForm.note"></el-input>
        </el-form-item>
    </el-form>
  </div>
  <div style="float: left">


    <div style="float: left;width: 100%;margin-bottom: 100px">
      <img src="http://myblog.over2022.top/20230222192715.png" style="width: 800px;height: 400px"/>
    </div>
    <el-button  type="primary" size="large" @click="submit">
      提交订单
    </el-button>

    <el-dialog title="支付费用" v-model="dialogVisiable" width="300px">
      <img src="http://myblog.over2022.top/f5de99eba224c54f6b1da5121bd6007.jpg" style="width: 100%;height: auto"/>

      <div class="dialog-footer" style="margin: 10px 100px;">
        <el-button @click="dialogVisiable = false" type="danger" size="large">取 消</el-button>
        <!--            <el-button type="primary" @click="alterConfirm">确 定</el-button>-->
      </div>
    </el-dialog>



  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  name: "tosend",
  data() {
    return {
      dialogVisiable: false,
      toSendForm:
          {
            // id: 1,
            // logistics: '京东',
            // content: '商品描述',
            // sendAdress: '浙江省杭州市XXXX县XXX',
            // sendData: '2023-9-8',
            // sender: "发件人姓名",
            // senderNumber: 18542658898,
            // receiveAddress: "湖南省湘潭市雨湖区湖南科技大学一区二栋",
            // receiveDate: "2023-9-15",
            // receiver: "收件人姓名",
            // receiverNumber: 18570418284,
            // weight: 2,
            // note: "无"
          },
      rules: {
        sender: [{required: true, message: '请输入寄件人', trigger: 'blur'}],
        senderNumber: [{required: true, message: '请输入寄件人手机号', trigger: 'blur'}],
        sendeAddress: [{required: true, message: '请输入寄件地址', trigger: 'blur'}],
        sendDate: [{required: true, message: '请输入寄件日期', trigger: 'blur'}],
        receiver: [{required: true, message: '请输入收件人', trigger: 'blur'}],
        receiverNumber: [{required: true, message: '请输入收件人手机号', trigger: 'blur'}],
        receiveAddress: [{required: true, message: '请输入收件地址', trigger: 'blur'}],
        content: [{required: true, message: '请输包裹描述', trigger: 'blur'}],
        weight: [{required: true, message: '请输入包裹质量', trigger: 'blur'}],
        logistics: [{required: true, message: '请输入承运方', trigger: 'blur'}],
        // note:[{required:true,message:'备注',trigger:'blur'}],
      },
    }
  },
  methods: {
    submit() {
      this.$refs.toSendForm.validate((valid) => {
        if (valid) {
          request.post("/package/toSend", this.toSendForm).then(res => {
            console.log(res)
            this.$message({
              message: '提交成功',
              type: 'success'
            });
          })
          this.dialogVisiable = true
        } else {
          this.$message.error('提交失败')
          return false;
        }
      })
    }

  }

}
</script>

<style scoped>

</style>
