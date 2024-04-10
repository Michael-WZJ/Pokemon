import Vue from "vue";
import VueRouter from "vue-router";
import reserve from "@/router/reserve";

Vue.use(VueRouter)

const router = new VueRouter({
  routes: [...reserve]
})

export default router