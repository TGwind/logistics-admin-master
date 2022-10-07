<template>
  <div>
    <el-row gutter="40">

      <el-col span="4">
        <el-button type="primary" @click="add">新增处罚</el-button>
      </el-col>

      <el-col span="4">
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
      <el-col span="4">
        <el-button type="primary" @click="show">展示列表</el-button>
      </el-col>
      <el-col span="4" >
        <el-input v-model="search" placeholder="请输入编号"/>
      </el-col>
      <el-col span="4">
        <el-button type="primary" @click="toSearch">按编号查询</el-button>
      </el-col>
    </el-row>
    <el-dialog title="新增处罚" v-model="dialogVisible" width="50%">
      <el-form :model="punish" label-width="200px">
        <el-form-item label="学号">
          <el-input v-model="punish.studentId" style="width: 50%"></el-input>
        </el-form-item>

        <el-form-item label="奖励级别">
          <el-select v-model="punish.levels" style="width: 50%" placeholder="请选择处罚级别">
            <el-option
                v-for="item in levels"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="是否生效">
          <el-input v-model="punish.effect" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="记录时间">
          <el-input v-model="punish.recTime" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="punish.description" style="width: 50%"></el-input>
        </el-form-item>
      </el-form>
      <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </span>
    </el-dialog>

    <el-table :data="tableData" style="width: 100% ;margin-top: 30px"
              ref="multipleTableRef"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="id" label="编号"/>
      <el-table-column prop="studentId" label="学号"/>
      <el-table-column prop="levels" label="处罚级别"/>
      <el-table-column prop="effect" label="是否生效"/>
      <el-table-column prop="recTime" label="奖励时间"/>
      <el-table-column prop="description" label="备注"/>
      <el-table-column label="操作" key="scope">
        <template #default="scope">
          <el-button type="primary" @click="alter(scope.row)">编辑</el-button>
        </template>
      </el-table-column>


    </el-table>

    <el-dialog title="修改信息" v-model="alterDialogVisible" width="50%">
      <el-form :model="alterForm" label-width="200px">
        <el-form-item label="学号">
          <el-input v-model="alterForm.studentId" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="处罚级别">
          <el-select v-model="alterForm.levels" style="width: 50%" placeholder="请选择奖励级别">
            <el-option
                v-for="item in levels"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="是否生效">
          <el-select v-model="alterForm.effect" style="width: 50%" placeholder="请选择奖励级别">
            <el-option
                v-for="item in effect"
                :key="item.label"
                :label="item.label"
                :value="item.label"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="处罚时间">
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
  name: "Punish",
  data() {
    return {
      search: null,
      punish: {
        id: null,
        studentId: null,
        levels:null,
        effect:null,
        recTime:null,
        description:null
      },
      ids: [],//存储id序列
      alterForm: {
        studentId: null,
        levels:null,
        effect:null,
        recTime:null,
        description:null
      },
      tableData: [],
      effect: [
        {
          label: "是"
        },
        {
          label:"否"
        }
      ],
      levels: [
        {
          value: "警告",
          label: "警告"
        },
        {
          value: "严重警告",
          label: "严重警告"
        },
        {
          value: "记过",
          label: "记过"
        },
        {
          value: "记大过",
          label: "记大过"
        },
        {
          value: "开除",
          label: "开除"
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
      request.get("http://120.77.179.69:8765/punish/" + row.id).then(res => {
        console.log(res)
        this.alterForm = res.data
      })
    },
    alterConfirm(){
      request.put("http://120.77.179.69:8765/punish/update",this.alterForm).then(res=>{
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
      request.post("http://120.77.179.69:8765/punish/deleteBatch", this.ids).then(res => {
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
      this.award = {} //每次打开新增将表单置空
    },
    //保存操作
    save() {
      request.post("http://120.77.179.69:8765/punish", this.punish).then(res => {
        console.log(res)
        this.dialogVisible = false
        this.$message.success("添加成功")
        this.show()  //刷新列表
      })
    },
    show() {
      request.get("http://120.77.179.69:8765/punish/show").then(res => {
        console.log(res)
        this.tableData = res.data;
      })
    },
    toSearch() {
      request.get("http://120.77.179.69:8765/punish/" + this.search).then(res => {
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
