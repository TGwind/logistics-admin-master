<template>
  <div>

  </div>
  <!--  查询单号-->
  <div style="margin-top: 20px">
    <el-row>
      <el-col span="16" :offset="1" >
        <el-input v-model="search" placeholder="请输入快递单号"/>
      </el-col>
      <el-col span="2" :offset="1">
        <el-button type="primary" @click="toSearch">查询</el-button>
      </el-col>
    </el-row>
  </div>

  <div>
    <el-dialog title="新增学生" v-model="dialogVisible" width="50%">
      <el-form :model="student" label-width="200px">
        <el-form-item label="姓名">
          <el-input v-model="student.name" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="student.sex">
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="院系">
          <el-select v-model="student.dept" style="width: 50%" placeholder="请选择院系">
            <el-option
                v-for="item in dept"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="班级">
          <el-select v-model="student.lass" style="width: 50%" placeholder="请选择班级">
            <el-option
                v-for="item in lass"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="生日">
          <el-date-picker
              v-model="student.birth"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="籍贯">
          <el-input v-model="student.nativePlace" style="width: 50%"></el-input>
        </el-form-item>
      </el-form>
      <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </span>
    </el-dialog>

    <!--    展示列表-->
    <el-table :data="tableData" style="width: 100% ;margin-top: 30px"
              ref="multipleTableRef"
              @selection-change="handleSelectionChange">
      <!--      <el-table-column type="selection" width="40"/>-->
      <el-table-column prop="id" label="单号" width="120px"/>
      <el-table-column prop="shipper" label="承运方"/>
      <el-table-column prop="name" label="包裹名称"/>
      <el-table-column prop="sendAddress" label="发件地址"/>
      <el-table-column prop="sendDate" label="发件日期"/>
      <el-table-column prop="receiveAddress" label="收件地址"/>
      <el-table-column prop="receiveDate" label="收件日期"/>
      <el-table-column prop="receiver" label="收件人"/>
      <el-table-column prop="receiverNumber" label="手机号"/>


      <!--      <el-table-column prop="nativePlace" label="籍贯"/>-->
      <el-table-column label="物流详情" key="scope">
        <template #default="scope">
          <el-button type="primary" @click="alter(scope.row)">查看</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="修改信息" v-model="alterDialogVisible" width="50%">
      <el-form :model="alterForm" label-width="200px">
        <el-form-item label="姓名">
          <el-input v-model="alterForm.name" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="alterForm.sex">
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="院系">
          <el-select v-model="alterForm.dept" style="width: 50%" placeholder="请选择院系">
            <el-option
                v-for="item in dept"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="班级">
          <el-select v-model="alterForm.lass" style="width: 50%" placeholder="请选择班级">
            <el-option
                v-for="item in lass"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="生日">
          <el-input v-model="alterForm.birth" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="籍贯">
          <el-input v-model="alterForm.nativePlace" style="width: 50%"></el-input>
        </el-form-item>
      </el-form>
      <span class="dialog-footer">
            <el-button @click="alterDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="alterConfirm">确 定</el-button>
          </span>
    </el-dialog>
  </div>


</template>

<script>
import request from "@/utils/request";

export default {
  name: "Message",
  data() {
    return {

      value1: "",
      search: null,
      student: {
        id: null,
        name: null,
        sex: null,
        dept: null,
        lass: null,
        birth: "",
        nativePlace: null,
      },
      ids: [],//存储id序列
      alterForm: {
        id: null,
        name: null,
        sex: null,
        dept: null,
        lass: null,
        birth: null,
        nativePlace: null,
      },
      tableData: [
        {
          id: 1,
          shipper: '京东',
          name: '商品描述',
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
      dept: [
        {
          value: "大数据",
          label: "大数据"
        },
        {
          value: "信息安全",
          label: "信息安全"
        },
        {
          value: "软件工程",
          label: "软件工程"
        },
        {
          value: "人工智能",
          label: "人工智能"
        }
      ],
      lass: [
        {
          value: "1班",
          label: "1班"
        },
        {
          value: "2班",
          label: "2班"
        },
        {
          value: "3班",
          label: "3班"
        },
      ],
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
      console.log(row)
      request.get("http://120.77.179.69:8765/student/" + row.id).then(res => {
        console.log(res)
        this.alterForm = res.data
      })
    },
    alterConfirm() {
      request.put("http://120.77.179.69:8765/student/update", this.alterForm).then(res => {
        console.log(res)
        this.$message.success("修改成功")
        this.alterDialogVisible = false
        this.show()
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
      request.get("/package/queryAll").then(res => {
        console.log(res)
        this.tableData = res.data;
      })
    },
    toSearch() {
      request.get("http://120.77.179.69:8765/student/" + this.search).then(res => {
        console.log(res)
        if (res.data == null) this.$message.success("暂无数据")
        else {
          this.$message.success("查询成功")
        }
        this.tableData = [res.data]
      })
    }
  }
}

</script>

<style scoped>

</style>
