<template>
<div>
  <el-container>
    <el-header class="homeHeader">

      <div style="display:flex">
        <img src="../assets/logoBlack2.png" style="width: 40px;height: 40px;margin-top:5px;margin-left: 20px" @click="goHome">
        <span class="title" v-if="!this.isCollapse"  @click="goHome" style="margin-top: 5px"> 云 E 办</span>
        <el-button v-if="!this.isCollapse" icon="el-icon-s-fold" @click="switchMenu" type="text" style="font-size: 30px;color: white;margin-left:40px"></el-button>
        <el-button v-if="this.isCollapse" icon="el-icon-s-unfold" @click="switchMenu" type="text" style="font-size: 30px;color: white;margin-left:40px"></el-button>
        <h3 class="titleWelcome">欢迎进入 云 E 办 线上办公平台</h3>
      </div>

      <div class="userInfo">
        <i class="el-dropdown-link" style="margin-bottom: 4px">
          <img :src="user.userFace">
        </i>
        <el-dropdown @command="commandHandler">
            <span class="el-dropdown-link" style="margin-top: 6px">




              <span style="color:#fff;margin-left:10px;margin-right: 10px;margin-top: 2px">
                欢迎您，{{ user.name }}
              </span>


            </span>

            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="userInfo">个人中心</el-dropdown-item>
              <el-dropdown-item command="setting">设置</el-dropdown-item>
              <el-dropdown-item command="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        <i style="margin-top: 4px">
          <el-divider direction="vertical" ></el-divider>
        </i>
        <el-button type="text" style="margin-left:8px;color:#fff;font-size: 14px" icon="el-icon-switch-button" @click="layout">
          退出登录
        </el-button>

      </div>


    </el-header>


    <el-container>
      <el-aside :width="sideWidth">

        <el-menu class="el-menu-vertical-demo" router unique-opened :collapse="isCollapse">
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

        <div>
          <el-breadcrumb separator-class="el-icon-arrow-right" v-if="this.$router.currentRoute.path!='/home'">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{ this.$router.currentRoute.name }}</el-breadcrumb-item>
          </el-breadcrumb>

          <span class="homeWelcome" v-if="this.$router.currentRoute.path=='/home'">
            欢迎来到云E办系统
          </span>

          <router-view class="homeRouterView"/>
        </div>
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
      user:JSON.parse(window.sessionStorage.getItem('user')),
      isCollapse: false,
      sideWidth:'200px'
    }
  },
  computed:{
    routes(){
      return this.$store.state.routes;
    }
  },
  methods:{
    goHome(){
      this.$router.push('/home');
    },
    switchMenu(){
      this.isCollapse = !this.isCollapse;
      if(!this.isCollapse){
        this.sideWidth = '200px';
      }else{
        this.sideWidth = '80px';
      }

    },
    layout(){
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
    },
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

.el-menu-vertical-demo:not(.el-menu--collapse) {
  min-height: 400px;
}

.homeHeader {
  background-color: #9c938a;
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
  margin-top: 4px;
  margin-left: 30px;
}
.homeHeader .titleWelcome{
  font-size: 14px;
  font-family: 华文行楷;
  color: #ffffff;
  margin-top: 15px;
  margin-left: 10px;
}
.homeHeader .userInfo {
  cursor: pointer;
  margin-top: 14px;
  display: flex;
  margin-bottom: 13px;
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


.el-breadcrumb__item {
  font-size: 15px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}



</style>
