<template>
  <div>
    <div class="permissManaTool">
      <el-input size="small" placeholder="请输入角色英文名" @keydown.enter.native="doAddRole" v-model="role.name">
        <template slot="prepend">ROLE_</template>
      </el-input>
      <el-input size="small" v-model="role.nameZh" placeholder="请输入角色中文名" @keydown.enter.native="doAddRole"></el-input>
      <el-button size="small" type="primary" icon="el-icon-plus" @click="doAddRole">添加角色</el-button>
    </div>
    <div class="permissManaMain">
      <el-collapse v-model="activeName" accordion @change="change">
        <el-collapse-item :title="r.nameZh" :name="r.id" v-for="(r,index) in roles" :key="index">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>可访问资源</span>
              <el-button style="float: right; padding: 3px 0;color:#ff0000;font-size: 18px" type="text" icon="el-icon-delete" @click="doDeleteRole(r)"></el-button>
            </div>
            <div>
              <el-tree show-checkbox
                       ref="tree"
                       :key="index"
                       :data="allMenus"
                       :props="defaultProps"
                       :default-checked-keys="selectedMenus"
                        node-key="id">
              </el-tree>
              <div style="display: flex;justify-content:flex-end">
                <el-button size="mini" type="text" @click="cancelUpdate">关闭</el-button>
                <el-button size="mini" type="text" @click="doUpdate(r.id,index)">确认修改</el-button>
              </div>
            </div>
          </el-card>
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>

<script>
export default {
  name: "PermissMana",
  data(){
    return{
      role:{
          name:'',
          nameZh:''
      },
      roles:[],
      allMenus:[],
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      selectedMenus:[],
      activeName:-1
    }
  },
  mounted() {
    this.initRoles();
  },
  methods:{
    doDeleteRole(role){
      this.deleteRequest('/system/basic/permiss/role/'+role.id).then(resp=>{
        if(resp){
          this.initRoles();
        }
      })
    },
    cancelUpdate(){
      this.activeName = -1;
    },
    doAddRole(){
      if(this.role.name && this.role.nameZh){
        this.postRequest('/system/basic/permiss/role',this.role).then(resp=>{
          if(resp){
            this.initRoles();
            this.role.name = '';
            this.role.nameZh = '';
          }


        })
      }else{
        this.$message.error('所有字段不能为空');
      }
    },
    doUpdate(rid,index){
      let tree = this.$refs.tree[index];
      let selectedKeys = tree.getCheckedKeys(true);
      let url = '/system/basic/permiss/?rid='+rid;
      selectedKeys.forEach(key=>{
        url+= '&mids='+key;
      })
      this.putRequest(url).then(resp=>{
        if(resp){
          this.activeName = -1;
        }
      })
    },
    initSelectedMenus(rid){
      this.getRequest('/system/basic/permiss/mid/'+rid).then(resp=>{
        if(resp){
          this.selectedMenus = resp;
        }else{
          this.selectedMenus = '';
          this.$message.error('请求失败，请再次点击后重试！');
        }
      })
    },
    initAllMenus(){
      this.getRequest('/system/basic/permiss/menus').then(resp=>{
        if(resp){
          this.allMenus = resp;
        }
      })
    },
    change(rid){
      if(rid){
        this.initSelectedMenus(rid);
        this.initAllMenus();
      }
    },
    initRoles(){
      this.getRequest('/system/basic/permiss/').then(resp=>{
        if(resp){
          this.roles = resp;
        }
      })
    }
  }
}
</script>

<style>
  .permissManaTool{
    display: flex;
    justify-content:flex-start;
  }
  .permissManaTool .el-input{
    width:40%;
    margin-right: 6px;
  }
  .permissManaMain{
    margin-top: 10px;
    width: 70%;
  }


</style>
