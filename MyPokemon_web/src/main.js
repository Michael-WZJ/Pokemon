import Vue from 'vue'
import App from './App.vue'
import router from './router/index.js'
import MjSelect from "@/components/mj-select/index.vue";

// 使用Avue需要先安装ElementUI的依赖
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Avue from '@smallwei/avue';
import '@smallwei/avue/lib/index.css';

Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(Avue);

Vue.component('MjSelect', MjSelect);

new Vue({
  render: h => h(App),
  router
}).$mount('#app')
