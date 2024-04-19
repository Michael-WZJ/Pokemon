<template>
  <div class="base-info-main">
    <avue-crud
        class="list"
        :option="option"
        :data="dataList"
        :table-loading="loading"
        :page.sync="page"
        @on-load="onLoad"
        :search.sync="searches"
        ref="crud"
        @size-change="sizeChange"
        @current-change="currentChange"
        @refresh-change="refreshChange"
        @search-change="searchChangeBase"
        @filter-change="filterChange"
        @sort-change="sortChange"
        @selection-change="selectionChange"
        @search-reset="reset"
        @row-save="rowSave"
        @row-update="rowUpdate"
        @row-del="rowDel"
        v-model="form"
    >
      <!-- 左上操作栏 -->
      <template #menuLeft="{ size }">
        <el-button type="primary" :size="size" @click.stop="handleShowAdd"> 新增 </el-button>
        <el-button :disabled="selectCount === 0" :size="size" @click="handleDelList">
          批量删除
        </el-button>
      </template>
      <!-- 右列操作栏 -->
      <template #menu="{ row, size }">
        <el-button type="text" :size="size" @click="handleShowEdit(row)">编辑</el-button>
        <el-button type="text" :size="size" @click="rowDel(row)">删除</el-button>
        <!--        <el-button type="text" :size="size" @click="handleShowView(row)">详情</el-button>-->
      </template>

      <!-- "名称"列 插槽 -->
      <template #pokeBaseName="{ row }">
        <span class="click_link" @click="handleShowView(row)">{{ row.pokeBaseName }}</span>
      </template>
    </avue-crud>
  </div>
</template>

<script>
import { crudOptions } from "@/views/pokeBaseInfo/crudOptions";
import MixinCrud from "@/mixin/avueCrud";
import { getBaseInfoList, delBaseInfoByCode, delBaseInfoByCodes ,getAllBaseInfo } from "@/api/pokeBaseInfoApi"
import {BASE_CONSTANT} from "@/views/baseConstants";

export default {
  name: "PokeBaseInfo",
  mixins: [MixinCrud],
  data() {
    return {
      api: {
        listApi: getBaseInfoList,
        deleteApi: delBaseInfoByCode,
        batchDeleteApi: delBaseInfoByCodes
      },
      searches: {},
      form: {}
    };
  },
  computed: {
    option() {
      return crudOptions(this);
    },
    selectCount() {
      if (this.dataList.length === 0) {
        return 0;
      }
      return this.selectList.length;
    }
  },
  methods: {
    onLoad() {
      this.handleList();
    },
    //对应着@search-change  表示点击搜索后触发该事件
    searchChangeBase(params, done) {
      this.searchChange(params, done);
    },

    /** 会话相关 **/
    // 显示 详情 页
    handleShowView(row) {
      this.$router?.push({
        name: "MyPokemon-pokeBaseInfo-pokeBaseInfoDetail",
        query: { id: row?.pokeBaseCode }
      });
      console.log("详情", row);
    },
    // 显示 编辑 页
    handleShowEdit(row, type = BASE_CONSTANT.EDIT) {
      let code = row?.pokeBaseCode;
      this.$router?.push({
        name: "MyPokemon-pokeBaseInfo-pokeBaseInfoAdd",
        query: { id: code, type: type}
      });
    },
    // 显示 新增 页
    handleShowAdd() {
      this.$router.push({
        name: "MyPokemon-pokeBaseInfo-pokeBaseInfoAdd",
        query: {
          type: BASE_CONSTANT.ADD,
          reset: BASE_CONSTANT.TRUE // 是否重置表单
        }
      });
    },

    /** 增删改 **/
    // 发送新增请求
    async rowSave(submitForm) {
    },
    // 发送修改请求
    async rowUpdate(submitForm) {
    },
    // 发送删除请求
    async rowDel(row) {
      const code = row.pokeBaseCode;
      if (!code) {
        this.$message({
          message: "请传入code",
          type: "warning"
        });
        return;
      }

      if (!this.api.deleteApi) {
        this.$message({
          message: "请设置api.delete属性!",
          type: "warning"
        });
        return;
      }

      this.$confirm(`此操作将删除选中宝可梦，且不可恢复。确定删除？`, "删除", {
        confirmButtonText: "确认",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.api
            .deleteApi(code)
            .then(res => {
              if (res.data.code === 10021) {
                this.filter.page = 1;
                this.onLoad();
                this.$message({
                  showClose: true,
                  message: "删除成功",
                  type: "success"
                });
              } else {
                this.$message({
                  showClose: true,
                  message: res.data.msg,
                  type: "error"
                });
              }
            })
            .catch(err => {
              console.log(err, "接口请求失败");
            });
      }).catch(() => {});
    },
    // 批量删除
    async handleDelList() {
      // console.log(this.selectCount);
      this.$confirm("此操作将删除选中宝可梦，且不可恢复。确定删除？", "批量删除", {
        confirmButtonText: "确认",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        const deleteCodes = this.selectList.map(item => item.pokeBaseCode);
        console.log(deleteCodes);
        if (deleteCodes.length === 0) {
          return;
        }
        this.api
            .batchDeleteApi(deleteCodes)
            .then(res => {
              if (res.data.code === 10021) {
                this.onLoad();
                this.$message({
                  showClose: true,
                  message: res.data.data,
                  type: "success"
                });
              } else {
                this.$message({
                  showClose: true,
                  message: res.data.msg,
                  type: "error"
                });
              }
            })
            .catch(err => {
              console.log(err, "接口请求失败");
            });
      }).catch(() => {});
    },

    // 检查数据有效性
    verifyForm(submitForm) {}
  }
}
</script>

<style lang="scss" scoped>
.base-info-main {
  margin: 16px;
}

.click_link {
  color: #2A7FFFFF;
  cursor: pointer;
}
</style>