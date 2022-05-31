<template>
<div>
  <el-container>
    <el-header class="homeHeader">

      <div class="title"> 云 E 办</div>

      <el-dropdown class="userInfo" @command="commandHandler">
        <span class="el-dropdown-link">
          <i>
            <img :src="user.userFace">
          </i>
          <el-divider direction="vertical"></el-divider>
          <span style="color:#fff;margin-left:10px;margin-right: 10px">
            欢迎您，{{ user.name }}
          </span>
          <el-divider direction="vertical"></el-divider>
          <span style="color:#fff;margin-left:10px;margin-right: 10px">
            退出登录
          </span>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="userInfo">个人中心</el-dropdown-item>
          <el-dropdown-item command="setting">设置</el-dropdown-item>
          <el-dropdown-item command="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>


    </el-header>


    <el-container>
      <el-aside width="200px">

        <el-menu router unique-opened>
          <el-submenu :index="index+''" v-for="(item,index) in routes"
                      :key="index"
                      v-if="!item.hidden">
            <template slot="title"><i :class="item.iconCls" style="color:#f9a647;margin-right:5px"></i>
              <span>
                {{ item.name }}
              </span>
            </template>

              <el-menu-item :index="children.path" v-for="(children,indexj) in item.children" :key="indexj">{{ children.name }}</el-menu-item>

          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>

        <el-breadcrumb separator-class="el-icon-arrow-right" v-if="this.$router.currentRoute.path!='/home'">
          <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>{{ this.$router.currentRoute.name }}</el-breadcrumb-item>
        </el-breadcrumb>
        <div class="homeWelcome" v-if="this.$router.currentRoute.path=='/home'">
          欢迎来到云E办系统
        </div>

        <router-view class="homeRouterView"/>
      </el-main>
    </el-container>
  </el-container>

</div>
</template>

<script>
import router from "@/router";

export default {
  name: "Home",
  data(){
    return{
      user:JSON.parse(window.sessionStorage.getItem('user'))
    }
  },
  computed:{
    routes(){
      return this.$store.state.routes;
    }
  },
  methods:{
    commandHandler(command){
      if(command == 'logout'){
        this.$confirm('此操作退出登录，是否继续？','提示',{
          confirmButtonText:'确定',
          confirmbuttonText:'取消',
          type:'warning'
        }).then(()=>{
          this.postRequest('/logout');
          // 清空用户信息
          window.sessionStorage.removeItem('tokenStr');
          window.sessionStorage.removeItem('user');
          this.$store.commit('initRoutes',[]);
          // 跳转到登录页
          this.$router.replace('/');
        }).catch(()=>{
          this.$message({
            type:'info',
            message:'取消'
          });
        });

      }
    }
  }
}
</script>

<style>


.homeHeader {
  background-color: #858586;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0px 15px;
  box-sizing: border-box;

}

.homeHeader .title {
  font-size: 30px;
  font-family: 华文行楷;
  color: #ffffff;
}

.homeHeader .userInfo {
  cursor: pointer;
}

.homeWelcome {
  text-align: left;
  font-size: 20px;
  font-family: 华文行楷;
  color: #154599;
  padding-top: 7px;
}

.homeRouterView {
  margin-top: 10px;
}

.el-dropdown-link img {
  width: 28px;
  height: 28px;
  border-radius: 14px;
  margin-top: 3px;
  margin-right: 15px;
}

.el-dropdown-link {
  display: flex;
  align-items: center;
}

.el-tabs__item {

  color: #EDCFAB !important;

}
/*.is-active{*/
/*  color: #f9a647 !important;*/
/*  background-color: #F0E7D5 !important;*/
/*}*/

.el-breadcrumb__item {
  font-size: 15px;
}




</style>
