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
        <el-button type="primary" :size="size" @click="handleShowAdd"> 新增 </el-button>
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
import { getBaseInfoList ,getAllBaseInfo } from "@/api/pokeBaseInfoApi"

export default {
  name: "PokeBaseInfo",
  mixins: [MixinCrud],
  data() {
    return {
      api: {
        listApi: getBaseInfoList
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
      console.log("详情", row);
    },
    // 显示 编辑 页
    handleShowEdit(row) {},
    // 显示 新增 页
    handleShowAdd() {
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
    },
    // 批量删除
    async handleDelList() {},

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