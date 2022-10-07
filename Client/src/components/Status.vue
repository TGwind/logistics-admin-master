<template>
  <div>
    <el-row gutter="40">
      <!--      添加学籍变动-->
      <el-col span="4">
        <el-button type="primary" @click="add">新增学籍变动</el-button>
        <el-dialog title="新增学籍变动" v-model="dialogVisible" width="50%">
          <el-form :model="form" label-width="200px">
            <el-form-item label="学号">
              <el-input v-model="form.studentId" style="width: 50%"></el-input>
            </el-form-item>
            <el-form-item label="学籍变动">
              <el-select v-model="form.transfer" style="width: 50%" placeholder="请选择变动类型">
                <el-option
                    v-for="item in transfer"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="变动时间">
              <el-input v-model="form.recTime" style="width: 50%"></el-input>
            </el-form-item>
            <el-form-item label="备注">
              <el-input v-model="form.description" style="width: 50%"></el-input>
            </el-form-item>
          </el-form>
          <span class="dialog-footer">
            <el-button @click="DialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </span>
        </el-dialog>
      </el-col>
      <!--      删除学生-->
      <el-col :span="3">
        <template #default="scope">
          <el-popconfirm
              title="确定删除吗？"
              @confirm="deleteBatch"
              confirmButtonText="确定"
              cancelButtonText="取消"
              confirmButtonType="danger">
            <template #reference>
              <el-button size="mini" type="danger">批量删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-col>
      <el-col :span="4">
        <!--    展示当前所有学生-->
        <el-button type="primary" @click="show">展示列表</el-button>
      </el-col>
      <el-col :span="4">
        <el-input v-model="search" placeholder="请输入编号"/>
      </el-col>
      <el-col :span="4">
        <el-button type="primary" @click="toSearch">按编号查询</el-button>
      </el-col>
    </el-row>


    <el-table :data="tableData" style="width: 100% ;margin-top: 30px"
              ref="multipleTableRef"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="id" label="编号"/>
      <el-table-column prop="studentId" label="学号"/>
      <el-table-column prop="transfer" label="学籍变动"/>
      <el-table-column prop="recTime" label="变动时间"/>
      <el-table-column prop="description" label="备注"/>
      <el-table-column label="操作" key="scope">
        <template #default="scope">
          <el-button type="primary" @click="alter(scope.row)">编辑</el-button>
        </template>
      </el-table-column>


    </el-table>

    <el-dialog title="修改学籍变动" v-model="alterDialogVisible" width="50%">
      <el-form :model="alterForm" label-width="200px">
        <el-form-item label="学号">
          <el-input v-model="alterForm.studentId" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="学籍变动">
          <el-select v-model="alterForm.transfer" style="width: 50%" placeholder="请选择变动类型">
            <el-option
                v-for="item in transfer"
                :key="item.label"
                :label="item.label"
                :value="item.label"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="变动时间">
          <el-input v-model="alterForm.recTime" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="alterForm.description" style="width: 50%"></el-input>
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
  name: "Status",
  data() {
    return {
      search: null,
      form: {
        id: null,
        studentId: null,
        transfer: null,
        recTime: null,
        description: null,
      },
      ids: [],//存储id序列
      alterForm: {
        id: null,
        studentId:null,
        transfer:null,
        recTime:null,
        description:null
      },
      tableData: [],
      transfer: [
        {
          value: "0",
          label: "转系"
        },
        {
          value: "1",
          label: "休学"
        },
        {
          value: "2",
          label: "复学"
        },
        {
          value: "3",
          label: "退学"
        },
        {
          value: "4",
          label: "毕业"
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
      request.get("http://120.77.179.69:8765/transfer/" + row.id).then(res => {
        console.log(res)
        this.alterForm = res.data
      })
    },
    alterConfirm(){
      request.put("http://120.77.179.69:8765/transfer/update",this.alterForm).then(res=>{
        console.log(res)
        this.$message.success("修改成功")
        this.alterDialogVisible=false
        this.show()
      })
    },
    deleteBatch() {
      if (!this.ids.length) {
        this.$message.warning("请选择数据！")
        return
      }
      request.post("http://120.77.179.69:8765/transfer/deleteBatch", this.ids).then(res => {
        if (res.code === '0') {
          this.$message.success("批量删除成功")
          this.show()  //刷新列表
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleSelectionChange(val) {
      this.ids = val.map(v => v.id)
      console.log(this.ids)// [{id,name}, {id,name}] => [id,id]
    },
    add() {
      this.dialogVisible = true
      this.student = {} //每次打开新增将表单置空
    },
    //保存操作
    save() {
      request.post("http://120.77.179.69:8765/transfer", this.form).then(res => {
        console.log(res)
        this.dialogVisible = false
        this.$message.success("添加成功")
        this.show()  //刷新列表
      })
    },
    show() {
      request.get("http://120.77.179.69:8765/transfer/show").then(res => {
        console.log(res)
        this.tableData = res.data;
      })
    },
    toSearch() {
      request.get("http://120.77.179.69:8765/transfer/" + this.search).then(res => {
        console.log(res)
        if(res.data==null)this.$message.success("暂无数据")
        else{
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
