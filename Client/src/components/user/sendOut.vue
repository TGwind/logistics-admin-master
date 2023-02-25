<template>

  <!--  查询单号-->
  <div style="margin-top: 20px">
    <el-row>
      <el-col span="16" :offset="1">
        <el-input v-model="search" placeholder="请输入快递单号"/>
      </el-col>
      <el-col span="2" :offset="1">
        <el-button type="primary" @click="toSearch">查询</el-button>
      </el-col>
    </el-row>
  </div>

  <!--    展示列表-->
  <el-table :data="tableData" style="width: 100% ;margin-top: 30px"
            ref="multipleTableRef"
            @selection-change="handleSelectionChange">
    <!--      <el-table-column type="selection" width="40"/>-->
    <el-table-column prop="id" label="单号" width="120px"/>
    <el-table-column prop="logistics" label="承运方"/>
    <el-table-column prop="content" label="包裹名称"/>
    <el-table-column prop="sendAddress" label="发件地址"/>
    <el-table-column prop="sendDate" label="发件日期"/>
    <el-table-column prop="receiveAddress" label="收件地址"/>
    <el-table-column prop="receiveDate" label="收件日期">
      <template #default="scope">
        <span v-if="scope.row.receiveDate==null" style="color: crimson">待收</span>
      </template>
    </el-table-column>
    <el-table-column prop="receiver" label="收件人"/>
    <el-table-column prop="receiverNumber" label="手机号"/>


    <!--      <el-table-column prop="nativePlace" label="籍贯"/>-->
    <el-table-column label="物流详情" key="scope">
      <template #default="scope">
        <el-button type="primary" @click="alter(scope.row)">查看</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog title="物流信息" v-model="alterDialogVisible" width="40%">
    <div class="div-bg bg-white">
      <!--    <div style="height: 0.5rem;width: 100%;background-color:#f7f7f7"></div>-->
      <!--物流跟踪-->
      <div style="padding-bottom: 0.5rem;">
        <div class="bg-white" style="width: 92%; margin-left: 4%;margin: auto;padding-left: 15px;padding-right: 15px;">
          <!--        <div style="font-size: 1.2rem;color: #111111;">物流跟踪&lt;!&ndash;物流跟踪&ndash;&gt;</div>-->
          <div>
            <div class="track-rcol">
              <div class="track-list">
                <ul>
                  <div v-for="(item,index) in logisticsList" :key="index">
                    <li class="first" v-if="index===0">
                      <div></div>
                      <i class="node-icon"></i>
                      <span class="txt">{{ item.progress + item.note }}</span>
                      <span class="time">{{ item.date }}</span>
                    </li>
                    <li v-if="index > 0 && index !== logisticsList.length-1">
                      <i class="node-icon"></i>
                      <span class="txt">{{ item.progress + item.note }}</span>
                      <span class="time">{{ item.date }}</span>
                    </li>
                    <li v-if="index === logisticsList.length-1" class="finall">
                      <i class="div-spilander"></i>
                      <i class="node-icon"></i>
                      <span class="txt">{{ item.progress + item.note }}</span>
                      <span class="time">{{ item.date }}</span>
                    </li>
                  </div>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--      <span class="dialog-footer">-->
    <!--            <el-button @click="alterDialogVisible = false">取 消</el-button>-->
    <!--            <el-button type="primary" @click="alterConfirm">确 定</el-button>-->
    <!--          </span>-->
  </el-dialog>


</template>

<script>
import request from "@/utils/request";
import global_val from "@/utils/global_val";

export default {
  name: "Message",
  data() {
    return {

      value1: "",
      search: null,
      ids: [],//存储id序列

      tableData: [
        {
          id: 1,
          logistics: '京东',
          content: '商品描述',
          sendAdress: '浙江省杭州市XXXX县XXX',
          sendData: '2023-9-8',
          sender: "发件人姓名",
          senderNumber: 18542658898,
          receiveAddress: "湖南省湘潭市雨湖区湖南科技大学一区二栋",
          receiveDate: "2023-9-15",
          receiver: "收件人姓名",
          receiverNumber: 18570418284
        }
      ],
      logisticsList: [],
      dialogVisible: false,
      alterDialogVisible: false,

    }
  },
  created() {
    this.show(); //每次点卡页面就数据渲染
  },
  methods: {
    alter(row) {
      this.alterDialogVisible = true;
      console.log(row.id)
      request.get("/transport/" + row.id).then(res => {
        this.logisticsList = res.data
      })
    },

    deleteBatch() {
      if (!this.ids.length) {
        this.$message.warning("请选择数据！")
        return
      }
      request.post("http://120.77.179.69:8765/student/deleteBatch", this.ids).then(res => {
        if (res.code === '0') {
          this.$message.success("批量删除成功")
          this.show()  //刷新列表
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleSelectionChange(val) {
      this.ids = val.map(v => v.id)   // [{id,name}, {id,name}] => [id,id]
      console.log(this.ids)
    },
    add() {
      this.dialogVisible = true
      this.student = {} //每次打开新增将表单置空
    },
    //保存操作
    save() {
      request.post("http://120.77.179.69:8765/student", this.student).then(res => {
        console.log(res)
        this.dialogVisible = false
        this.$message.success("添加成功")
        this.show()  //刷新列表
      })
    },
    show() {
      console.log(global_val.name)
      let name = global_val.name
      request.get("/package/sendOut/" + name).then(res => {
        console.log(res)
        this.tableData = res.data;
        // this.tableData.forEach(function (item){
        //   if(item.receiveDate==null){ //如果收件日期为空设为待收
        //     item.receiveDate = "待收"
        //   }
        // })
      })
    },
    toSearch() {
      if (this.search == null) {
        this.$message.warning("请输入快递单号")
      } else {
        request.get("/package/findSendOut/" + this.search + "/" + global_val.name).then(res => {
          console.log(res)
          if (res.data == null || res.data.length == 0) this.$message.success("暂无数据")
          else {
            this.$message.success("查询成功")
          }
          this.tableData = res.data
        })
      }
    }
  }
}

</script>

<style scoped>
.message-text {
  font-family: MicrosoftYaHei;
  font-size: 1rem;
  font-weight: normal;
  font-stretch: normal;
  line-height: 3rem;
  letter-spacing: 0rem;
  color: #333333;
  width: 50%;
}

.fontblack {
  color: #999999
}

.img2 {
  width: .81rem;
  height: .8rem;
  float: right;
}

.addressshow2 {
  height: auto;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  width: 75%;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  white-space: normal;
  word-wrap: break-word;
  word-break: break-all;
  font-size: 1rem;
}

.addressshow1 {
  height: auto;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  width: 75%;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  white-space: normal;
  word-wrap: break-word;
  word-break: break-all;
  font-size: 1rem;
}

.orderTitle {
  font-size: 1rem;
  color: #333333;
  height: auto;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  white-space: normal;
  word-wrap: break-word;
  word-break: break-all;
  height: 2.5rem;
}

.orderDetail {
  font-size: 0.8rem;
  color: #666666;
  text-align: left;
}

.border-ceter {
  width: 92%;
  padding-left: 15px;
  padding-right: 15px;
}

.pay-button {
  width: 88%;
  height: 2.6rem;
  position: relative;
  background-color: red;
  color: white;

  margin-left: 6%;
}

ul li {
  list-style: none;
  font-size: 1rem;
}

ul {
  padding-left: 1.5rem
}

.track-rcol {
}

.track-list {
  position: relative;
}

.track-list li {
  position: relative;
  padding: 0px 0 1.5rem 25px;
  line-height: 1rem;
  border-left: 1px solid #d9d9d9;
  color: #999;
}

.track-list li.first {
  color: red;
  padding-top: 0;
  width: 100%;
  text-align: left;
  border-left: 1px solid #d9d9d9;
}

.track-list li .node-icon {
  position: absolute;
  left: -5.5px;
  border-radius: 0.25rem;
  width: 0.5rem;
  height: 0.5rem;
  top: 4px;
  background-color: #999999;
}

.track-list li.first .node-icon {
  background-position: 0 -72px;
  background-color: red;
  width: 1rem;
  z-index: 2;
  height: 1rem;
  position: absolute;
  left: -9px;
  top: 0;
  border-radius: 0.5rem;
}

.track-list li .time {
  margin-right: 20px;
  position: relative;
  top: 4px;
  display: inline-block;
  vertical-align: middle;
  background-color: white;
  color: #999;
  width: 100%;
  text-align: left;
}

.track-list li .txt {
  position: relative;
  display: inline-block;
  vertical-align: top;
  color: #999;
}

.track-list li.first .time {
  text-align: left;
  width: 94%;
  color: red;
}

.track-list li.first .txt {
  color: red;
  text-align: left;
  width: 94%;
}

.track-list li.finall {
  position: relative;
  padding: 0px 0 1.5rem 25px;
  line-height: 18px;
  border-color: white;
  border-left: 1px solid #ffffff;
  color: #999;
}

.track-list li.finall .div-spilander {
  width: 1px;
  position: absolute;
  position: absolute;
  left: -1.5px;
  height: 0.5rem;
  background-color: #d9d9d9;
}
</style>
