<template>
<div>
  <div>
    <el-input
        size="small"
        class="addPosInput"
        placeholder="添加职位..."
        @keydown.enter.native="addPosition"
        v-model="pos.name">
    </el-input>
    <el-button size="small" icon="el-icon-plus" type="primary" @click="addPosition">添加</el-button>
  </div>
  <div class="posManaMain">
    <el-table
        size="small"
        stripe
        border
        :data="positions"
        style="width: 70%">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          prop="id"
          label="编号"
          width="55">
      </el-table-column>
      <el-table-column
          prop="name"
          label="职位"
          width="120">
      </el-table-column>
      <el-table-column
          prop="createDate"
          label="创建时间"
          width="200">
      </el-table-column>
      <el-table-column
          label="操作">
        <template slot-scope="scope">
          <el-button @click="showEditView(scope.row)" type="text" size="small">编辑</el-button>
          <el-button  @click="handleDelete(scope.$index,scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>

    </el-table>
  </div>
  <el-dialog
      title="编辑职位"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
    <div>
      <el-tag style="margin-left:8px">职位名称</el-tag>
      <el-input v-model="updatePos.name" size="small" class="updatePosInput"></el-input>
    </div>
    <span slot="footer" class="dialog-footer">
    <el-button size="small" @click="dialogVisible = false">取 消</el-button>
    <el-button size="small" type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
  </el-dialog>
</div>
</template>

<script>
export default {
  name: "PosMana",
  data(){
    return{
      pos:{
        name:''
      },
      positions: [],
      dialogVisible:false,
      updatePos:{
        name:''
      }
    }
  },
  mounted(){
    this.initPositions();
  },
  methods:{
    addPosition(){
      if(this.pos.name){
        this.postRequest('/position/',this.pos).then(resp=>{
          if(resp){
            this.initPositions();
            this.pos.name='';
          }
        })
      }else{
        this.$message.error('职位名称不能为空');
      }
    },
    initPositions(){
      this.getRequest('/position/').then(resp=>{
        if(resp){
          this.positions = resp;
        }
      })
    },
    handleDelete(index,data){
      this.$confirm('此操作将永久删除['+data.name+'], 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        // 调用接口后更新
        this.deleteRequest('/position/'+data.id).then(resp=>{
          if(resp){
            this.initPositions();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    showEditView(index,data){
      this.dialogVisible = true;
    }
  }
}
</script>

<style>
  .addPosInput{
    width: 300px;
    margin-right: 8px;
  }
  .posManaMain{
    margin-top: 10px;
  }

  .updatePosInput{
    width: 350px;
    margin-left:8px;
  }

</style>
