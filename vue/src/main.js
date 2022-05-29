import router from './router'
import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import 'font-awesome/css/font-awesome.css'

Vue.use(ElementUI);
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
    next();
  }else{
    if(to.path == '/'){
      next();
    }
  }
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
