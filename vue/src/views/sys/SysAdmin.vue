<template>
<div>
  <div style="display: flex;margin-right: 10%;justify-content: center">
    <el-input @keydown.enter.native="doSearch" v-model="keywords" style="width: 30%;margin-right: 10px" place-holder="通过用户名搜索用户..." prefix-icon="el-icon-search"></el-input>
    <el-button @click="doSearch" type="primary" icon="el-icon-search">搜索</el-button>
  </div>
  <div class="admin-container">
    <el-card class="admin-card" v-for="(admin,index) in admins" :key="index">
      <div slot="header" class="clearfix">
        <span>{{admin.name}}</span>
        <el-button style="float: right;font-size: 15px; color:red;padding: 3px 0" type="text" icon="el-icon-delete" @click="doDelete(admin)"></el-button>
      </div>
      <div>
        <div class="img-container">
          <img :src="admin.userFace" :alt="admin.name" :title="admin.name" class="userFaec-img">
        </div>
        <div class="userInfo-container">
          <div>姓名：{{admin.name}}</div>
          <dic>用户名：{{admin.username}}</dic>
          <div>手机号：{{admin.phone}}</div>
          <div>电话号：{{admin.telephone}}</div>
          <div>地址：{{admin.address}}</div>
          <div>
            用户状态：
            <el-switch
              v-model="admin.enabled"
              @change="enabledChange(admin)"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="启用"
              inactive-text="禁用">

            </el-switch>

          </div>
          <div>
            用户角色：
            <el-tag style="margin-right: 4px" type="success" v-for="(role,index) in admin.roles" :key="indexj">{{role.nameZh}}</el-tag>
            <el-popover
                @show="showPop(admin)"
                @hide="hidePop(admin)"
                placement="right"
                title="角色"
                width="200"
                trigger="click">
              <el-select v-model="selectedRoles" multiple placeholder="请选择">
                <el-option
                    v-for="(r,index) in allRoles"
                    :key="index"
                    :label="r.nameZh"
                    :value="r.id">
                </el-option>
              </el-select>
              <el-button slot="reference" type="text" icon="el-icon-more"></el-button>
            </el-popover>

          </div>
          <div>
            备注：{{admin.remark}}
          </div>
        </div>
      </div>

    </el-card>
  </div>
</div>
</template>

<script>
export default {
  name: "SysAdmin",
  data(){
    return{
      admins:[],
      keywords:'',
      allRoles:[],
      selectedRoles:[]
    }
  },
  mounted() {
    this.initAdmins();
  },
  methods:{
    hidePop(admin){
      let roles = [];
      Object.assign(roles,admin.roles);
      let flag= false;

      if(roles.length != this.selectedRoles.length){
        flag = true;
      }else{
        for(let i = i;i<roles.length;i++){
          let role = roles[i];
          for(let j = 0;j<this.selectedRoles.length;j++){
            let sr = this.selectedRoles[j];
            if(role.id == sr){
              roles.splice(i,1);
              i--;
              break;
            }
          }
        }
        if(roles.length !=0){
          flag = true;
        }
      }
      if(flag){
        let url = '/admin/role?adminId='+admin.id;
        this.selectedRoles.forEach(sr=>{
          url+='&rids='+sr;
        });
        this.putRequest(url).then(resp=>{
          if(resp){
            this.initAdmins();
          }
        });
      }

    },
    showPop(admin){
      this.initAllRoles();
      let roles = admin.roles;
      this.selectedRoles = [];
      roles.forEach(r=>{
        this.selectedRoles.push(r.id);
      });

    },
    initAllRoles(){
      this.getRequest('/admin/roles').then(resp=>{
        if(resp){
          this.allRoles = resp;
        }
      })
    },
    initAdmins(){
      this.getRequest('/admin/?keywords='+this.keywords).then(resp=>{
        if(resp){
          this.admins = resp;
        }
      })
    },
    doSearch(){
      this.initAdmins();
    },
    doDelete(admin){
      this.$confirm('此操作将永久删除【'+admin.name+'】, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteRequest('/admin/'+admin.id).then(resp=>{
          if(resp){
            this.initAdmins();
          }
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    enabledChange(admin){
      this.putRequest('/admin/',admin).then(resp=>{
        if(resp){
          this.initAdmins();
        }
      })
    }
  }
}
</script>

<style>
.admin-card{
  margin-left: 8px;
  width: 350px;
  margin-bottom: 20px;
}
.admin-container{
  display:flex;
  margin-top: 30px;
  justify-content: left;
  flex-wrap: wrap;
}
.userFaec-img{
  width: 50px;
  height: 50px;
  border-radius: 50px;
}

.img-container{
  width: 100%;
  display: flex;
  justify-content: center;
}
.userInfo-container{
  font-size:13px;
  color: #409eff;
}

</style>
