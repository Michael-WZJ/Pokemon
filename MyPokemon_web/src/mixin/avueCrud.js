/**
 * 用于crud的公共mixin
 */
import { pageConst } from "@/plugin/avue/option";

export default {
  data() {
    return {
      page: Object.assign({}, pageConst), //表格的分页数据(需要sync修饰符)
      filter: {
        //传递给后台的过滤对象
        filters: ""
      },
      params: {}, //额外查询条件（非搜索栏属性）
      dataList: [], //表格数据
      loading: false, //表格加载状态
      api: {
        list: undefined, //分页列表接口
        del: undefined //删除接口
      },

      // 表格自适应调整滚动条相关
      resizeObserver: null,
      bodyObserver: null,
      innerHeight: window.innerHeight,
      menuHeight: 0,
      searchHeight: 0,

      selectList: []
    };
  },
  mounted() {
    // 解决ERROR ResizeObserver loop completed with undelivered notifications. 问题的
    this.$nextTick(() => {
      const crud = this.$refs.crud;
      if (crud && crud.$el) {
        // 修正搜索按钮栏位置
        crud.$el.getElementsByClassName("avue-form__menu")[0] &&
        (crud.$el.getElementsByClassName("avue-form__menu")[0].className =
          "el-col el-col-6 el-col-xs-24 el-col-sm-12 el-col-md-6 avue-form__menu avue-form__menu--left no-print");
        // 查询列表表格高度自适应，添加监听
        this.bodyObserver = new ResizeObserver(entries => {
          this.innerHeight = entries[0].contentRect.height;
        });
        this.bodyObserver.observe(document.body);

        const searchElement = document.querySelector(".avue-crud__search");
        if (searchElement) {
          this.resizeObserver = new ResizeObserver(_entries => {
            // this.innerHeight = entries[0].contentRect.height;
            this.searchHeight = crud.$el.getElementsByClassName("avue-crud__search")[0]?.offsetHeight || 90;
            this.menuHeight = crud.$el.getElementsByClassName("avue-crud__menu")[0]?.offsetHeight || 32;
          });
          this.resizeObserver.observe(searchElement);
        }
      }
    });
  },
  methods: {
    //pageSize改变时会触发
    sizeChange(val) {
      this.page.pageSize = val;
      this.handleList();
    },
    //分页页码改变时会触发
    currentChange(val) {
      this.page.currentPage = val;
      this.handleList();
    },
    //点击刷新按钮触发该事件(由于page分页信息和search搜索信息是sync修饰符，可以直接通过this.page和this.search拿到)
    refreshChange() {
      this.reset();
      this.handleList();
    },
    //点击搜索后触发该事件(由于page分页信息和search搜索信息是sync修饰符，可以直接通过this.page和this.search拿到)
    searchChange(params, done) {
      let page = this.filter.page,
        rows = this.filter.rows;
      this.filter = {};
      this.filter.page = page;
      this.filter.rows = rows;
      this.page.currentPage = 1;
      this.filter = Object.assign(this.filter, params);
      this.handleList();
      done && done();
    },
    //当表格的筛选条件发生变化的时候会触发该事件，参数的值是一个对象，对象的key是column的columnKey，对应的value为用户选择的筛选条件的数组
    filterChange(result) {
      this.filter.filters = JSON.stringify(result);
      this.handleList();
    },
    //当表格的排序条件发生变化的时候会触发该事件
    sortChange(result) {
      this.filter.sidx = result.prop;
      if (result.order === "descending") {
        result.order = "desc";
      }
      if (result.order === "ascending") {
        result.order = "asc";
      }
      this.filter.sord = result.order;
      this.handleList();
    },
    //勾选操作
    selectionChange(list) {
      this.selectList = list;
    },
    reset() {
      var rows = this.filter.rows;
      this.filter = {};
      this.filter.page = 1;
      this.filter.rows = rows;
    },

    formatFilter(cancelSelect = true) {
      //取消选中
      if (cancelSelect && this.$refs.crud) this.$refs.crud.setCurrentRow(undefined);
      return {
        ...this.params,
        ...this.filter,
        pageNo: this.page.currentPage,
        pageSize: this.page.pageSize
      };
    },
    //获取列表数据
    handleList() {
      this.loading = true;
      this.api
        .listApi(this.formatFilter())
        .then(res => {
          console.log("res", res);
          if (res.data.code === 10041) {
            //dataWrapper - 数据包装器
            const data = this.dataWrapper ? this.dataWrapper(res.data.data) : res.data.data;
            // this.dataList = this.handleListByDict(data.data || []); wzj注解
            this.dataList = data.data || [];
            console.log("data", data);
            this.$set(this.page, "total", parseInt(data?.total));
            this.$set(this.page, "currentPage", data?.pageNo || 1);
            this.loading = false;
          } else {
            this.$message({
              showClose: true,
              message: res.message,
              type: "error"
            });
          }
        })
        .catch(e => {
          this.loading = false;
          this.$message.error({
            showClose: true,
            message: typeof e === "string" ? e : e.message
          });
        });
    },
  }
};