<template>
  <div>
    <el-input
        prefix-icon="el-icon-search"
        placeholder="请输入部门名称进行搜索..."
        v-model="filterText">
    </el-input>

    <el-tree
        :expand-on-click-node="false"
        style="width: 500px"
        class="filter-tree"
        :data="deps"
        :props="defaultProps"
        :filter-node-method="filterNode"
        ref="tree">
      <span class="custom-tree-node" slot-scope="{ node, data }" style="display: flex;justify-content: space-between;width:100% ;">
        <span>{{ node.label }}</span>
        <span>
          <el-button class="depBtn"
              type="text"
              size="mini"
              @click="() => showAddDep(data)">
            添加部门
          </el-button>
          <el-button class="depBtn"
              type="text"
              size="mini"
              @click="() => deleteDep(data)" style="color: #f86e9c">
            删除部门
          </el-button>
        </span>
      </span>
    </el-tree>
    <el-dialog
        title="添加部门"
        :visible.sync="dialogVisible"
        width="30%">
      <div>
        <table>
          <tr>
            <td>
              <el-tag>上级部门</el-tag>
            </td>
            <td>
                <el-input
                  v-model="this.pname"
                  :disabled="true">
                </el-input>
            </td>
          </tr>
          <tr>
            <td>
              <el-tag>部门名称</el-tag>
            </td>
            <td>
              <el-input v-model="dep.name" placeholder="请输入部门名称..."></el-input>
            </td>
          </tr>
        </table>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="doAddDep">确 定</el-button>
      </span>
  </el-dialog>

  </div>
</template>

<script>
export default {
  name: "DepMana",
  data(){
    return{
      filterText:'',
      deps:[],
      defaultProps:{
        children:'children',
        label:'name'
      },
      dialogVisible:false,
      dep:{
        name:'',
        parentId:-1
      },
      pname:''

    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    }
  },
  mounted() {
    this.intiDeps();
  },
  methods:{
    initDep(){
      this.dep = {
        name:'',
        parentId: -1
      };
      this.pname = '';
    },
    addDepsDeps(deps,dep){
      for(let i=0;i<deps.length;i++){
        let d = deps[i];
        if(d.id == dep.parentId){
           d.children = d.children.concat(dep);
           return;
        }else{
          this.addDepsDeps(d.children,dep);
        }
      }
    },
    doAddDep(){
      if(this.dep.name && this.dep.name !=''){
        this.postRequest('/system/basic/department/',this.dep).then(resp=>{
          if(resp){
            this.addDepsDeps(this.deps,resp.obj);
            this.dialogVisible = false;
            this.initDep();
          }
        })
      }else{
        this.$message.error('输入所有字段！');
      }

    },
    showAddDep(data){
      this.pname = data.name;
      this.dep.parentId = data.id;
      this.dialogVisible = true;
    },
    removeDepFromDeps(deps,id){
      for(let i = 0 ;i<deps.length;i++){
        let d = deps[i];
        if(d.id == id){
          deps.splice(i,i);
          return;
        }else{
          this.removeDepFromDeps(d.children,id);
        }
      }
    },
    deleteDep(data){
      if(data.isParent){
        this.$message.error('父部门删除失败，请清空子部门后重试！');
      }else{
        this.$confirm('此操作将永久删除'+data.name+'该部门，是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteRequest('/system/basic/department/'+data.id).then(resp=>{
            if(resp){
              this.removeDepFromDeps(this.deps,data.id);
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      }
    },
    intiDeps(){
      this.getRequest('/system/basic/department/').then(resp=>{
        if(resp){
          this.deps = resp;
        }
      })
    },
    filterNode(value,data){
      if(!value) return true;
      return data.name.indexOf(value) !== -1;
    }
  }
}
</script>

<style>
.depBtn{
  padding:2px;
}
</style>
