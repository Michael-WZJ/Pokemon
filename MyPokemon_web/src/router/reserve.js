import LayoutPoke from "@/views/LayoutPoke.vue";
import Api from "@/views/api/index.vue";

export default [
  {
    path: "/",
    component: LayoutPoke,
    children: [
      {
        path: "",
        redirect: { name: "homepage" },
        hidden: true
      },
      {
        path: "homepage",
        name: "homepage",
        component: () => import("@/views/HomePage.vue"),
        meta: { title: "首页", icon: "dashboard" }
      },
      {
        path: 'my',
        component: () => import("@/views/myPoke/mc/index.vue"),
      },
      { path: 'api', component: Api }
    ]
  }
]