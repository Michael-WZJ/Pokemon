import LayoutPoke from "@/views/LayoutPoke.vue";
import ParentView from "@/components/ParentView/index.vue";

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
        meta: { title: "首页", icon: "dashboard" },
      },
      {
        path: 'illustration',
        component: ParentView,
        name: "pokeBaseInfo",
        meta: { title: "宝可梦基本信息", icon: "dashboard" },
        children: [
          {
            path: "detail",
            name: "MyPokemon-pokeBaseInfo-pokeBaseInfoDetail",
            meta: { title: "宝可梦详情" },
            component: () => import("@/views/pokeBaseInfo/detail.vue")
          },
          {
            path: "add",
            name: "MyPokemon-pokeBaseInfo-pokeBaseInfoAdd",
            meta: { title: "新增宝可梦" },
            component: () => import("@/views/pokeBaseInfo/add.vue")
          },
          {
            path: "list",
            name: "MyPokemon-pokeBaseInfo-pokeBaseInfoList",
            meta: { title: "宝可梦列表" },
            component: () => import("@/views/pokeBaseInfo/index.vue")
          }
        ]
      },
      {
        path: 'my',
        component: () => import("@/views/myPoke/mc/index.vue"),
      },
      {
        path: 'setting',
        component: () => import("@/views/setting/index.vue")
      },
      {
        path: 'api',
        component: () => import("@/views/api/index.vue")
      }
    ]
  }
]