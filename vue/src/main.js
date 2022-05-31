import router from './router'
import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import 'font-awesome/css/font-awesome.css'

Vue.use(ElementUI,{size:'small'});
Vue.config.productionTip = false


import store from "./store"

import {postRequest} from "./utils/api";
import {getRequest} from "./utils/api";
import {putRequest} from "./utils/api";
import {deleteRequest} from "./utils/api";

Vue.prototype.postRequest = postRequest;
Vue.prototype.getRequest = getRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.deleteRequest = deleteRequest;

import {initMenu} from "./utils/menu";
router.beforeEach((to,from,next)=>{
  if(window.sessionStorage.getItem('tokenStr')){
    initMenu(router,store);
    if(!window.sessionStorage.getItem('user')){
      // 判断用户信息是否存在
      return getRequest('/admin/info').then(resp=>{
        if(resp){
          // 存入用户信息
          window.sessionStorage.setItem('user',JSON.stringify(resp));
          next();
        }
      })
    }
    next();
  }else{
    if(to.path == '/'){
      next();
    }else{
      next('/?redirect='+to.path);
    }
  }
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
