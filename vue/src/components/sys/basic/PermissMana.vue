<template>
  <div>
    <div class="permissManaTool">
      <el-input size="small" placeholder="请输入角色英文名" v-model="role.name">
        <template slot="prepend">ROLE_</template>
      </el-input>
      <el-input size="small" v-model="role.nameZh" placeholder="请输入角色中文名" ></el-input>
      <el-button size="small" type="primary" icon="el-icon-plus">添加角色</el-button>
    </div>
    <div class="permissManaMain">
      <el-collapse accordion>
        <el-collapse-item :title="r.nameZh" :name="r.id" v-for="(r,index) in roles" :key="index">
          <div>与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；</div>
          <div>在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。</div>
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
      roles:[]
    }
  },
  mounted() {
    this.initRoles();
  },
  methods:{
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
    width: 80%;
  }


</style>
