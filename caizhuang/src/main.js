// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';

import router from './router';

// import iView from 'iview';
// import 'iview/dist/styles/iview.css';
//iview版本换到4.5.0了
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';

import store from './vuex/store';

import axios from 'axios';

import VueAxios from 'vue-axios';

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';


// import SIdentify from './components/identify';
// Vue.use(SIdentify)

Vue.use(VueAxios,axios);
Vue.prototype.$axios = axios;

Vue.use(ViewUI);

// Vue.use(iView);
// Vue.config.productionTip = false;

Vue.use(ElementUI);

router.beforeEach((to, from, next) => {
  ViewUI.LoadingBar.start();
  next();
});

router.afterEach(route => {
  ViewUI.LoadingBar.finish();
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  // 为了在 Vue 组件中访问 this.$store property，
  // 你需要为 Vue 实例提供创建好的 store。Vuex 提供了一个从根组件向所有子组件，以 store 选项的方式“注入”该 store 的机制：
  store: store,
   // 把 store 对象提供给 “store” 选项，这可以把 store 的实例注入所有的子组件
  //  通过在根实例中注册 store 选项，该 store 实例会注入到根组件下的所有子组件中，且子组件能通过 this.$store 访问到
});
