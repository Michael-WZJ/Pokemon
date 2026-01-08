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
        <el-button type="text" :size="size" @click="handleShowEdit(row, $event)">编辑</el-button>
        <el-button type="text" :size="size" @click="rowDel(row)">删除</el-button>
        <!--        <el-button type="text" :size="size" @click="handleShowView(row)">详情</el-button>-->
      </template>

      <!-- "名称"列 插槽 -->
      <template #pokeBaseName="{ row }">
        <span class="click_link" @click="handleShowView(row, $event)">{{ row.pokeBaseName }}</span>
      </template>

      <!-- "图片"列 插槽 -->
      <template #pokePic="{ row }">
        <el-popover
            placement="right"
            trigger="hover"
            :open-delay="300"
            popper-class="image-popover"
        >
          <el-image
              :src="getPicUrl(row.pokeBaseCode)"
              style="max-width: 300px; max-height: 300px;"
          />
          <div slot="reference">
            <el-image
                :src="getPicUrl(row.pokeBaseCode)"
                :preview-src-list="[getPicUrl(row.pokeBaseCode)]"
                class="thumbnail"
                fit="cover"
            ></el-image>
          </div>
        </el-popover>
      </template>

      <!-- "属性"列 插槽 -->
      <template #prop1="{ row }">
        <div class="tag-show">
          <el-tag
            v-if="!isEmpty(row.prop1)"
            :style="{
              'color':getColorBase(row.prop1),
              'background-color':getColorBackground(row.prop1),
              'border-color':getColorBorder(row.prop1)
            }"
          >
            {{ row.prop1 }}
          </el-tag>
          <el-tag
            v-if="!isEmpty(row.prop2)"
            :style="{
              'color':getColorBase(row.prop2),
              'background-color':getColorBackground(row.prop2),
              'border-color':getColorBorder(row.prop2)
            }"
          >
            {{ row.prop2 }}
          </el-tag>
        </div>
      </template>
    </avue-crud>
  </div>
</template>

<script>
import { crudOptions } from "@/views/pokeBaseInfo/crudOptions";
import MixinCrud from "@/mixin/avueCrud";
import { getBaseInfoList, delBaseInfoByCode, delBaseInfoByCodes ,getAllBaseInfo } from "@/api/pokeBaseInfoApi"
import {BASE_CONSTANT} from "@/views/baseConstants";
import CssProp from "@/mixin/cssProp";
import {isEmpty} from "lodash";
import {API_ENDPOINTS} from "@/constants";

const picBaseUrl = process.env.VUE_APP_BASE_URL + API_ENDPOINTS.PIC.BASE_PIC;


export default {
  name: "PokeBaseInfo",
  mixins: [MixinCrud, CssProp],
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
    isEmpty,
    onLoad() {
      this.handleList();
    },
    //对应着@search-change  表示点击搜索后触发该事件
    searchChangeBase(params, done) {
      // console.log(params);
      if ("nameEng" in params) {
        params.nameEngStr = params.nameEng;
        delete params.nameEng;
      }
      // console.log(params);
      this.searchChange(params, done);
    },
    getPicUrl(code) {
      return picBaseUrl + code;
    },

    // 鼠标移入图片样式
    mouseOverStyle(index) {
      // console.log(index)
      const res = document.getElementsByClassName("thumbnail");
      const element = Array.from(res)[index];
      console.log(element);
      element.style.transform = "scale(5)";
    },
    // 鼠标移出图片样式
    mouseOutStyle(index) {
      const res = document.getElementsByClassName("thumbnail");
      const element = Array.from(res)[index];
      element.style.transform = "scale(1)";
    },

    /** 会话相关 **/
    // 显示 详情 页
    handleShowView(row, event) {
      // console.log("event", event);
      // console.log("ctrl", event.ctrlKey);
      let ctrlFlag = event.ctrlKey;

      if (ctrlFlag) {
        // 按下ctrl键，则在本页面打开
        this.$router?.push({
          name: "MyPokemon-pokeBaseInfo-pokeBaseInfoDetail",
          query: { id: row?.pokeBaseCode }
        });
      } else {
        // 不按下ctrl键，则在新页面打开
        let routeData = this.$router?.resolve({
          name: "MyPokemon-pokeBaseInfo-pokeBaseInfoDetail",
          query: { id: row?.pokeBaseCode }
        });
        window.open(routeData?.href, '_blank');
      }

      // console.log("详情", row);
    },
    // 显示 编辑 页
    handleShowEdit(row, event, type = BASE_CONSTANT.EDIT) {
      // console.log("event", event);
      // console.log("ctrl", event.ctrlKey);
      let ctrlFlag = event.ctrlKey;
      let code = row?.pokeBaseCode;

      if (ctrlFlag) {
        // 按下ctrl键，则在新页面打开
        let routeData = this.$router?.resolve({
          name: "MyPokemon-pokeBaseInfo-pokeBaseInfoAdd",
          query: { id: code, type: type}
        });
        window.open(routeData?.href, '_blank');
      } else {
        this.$router?.push({
          name: "MyPokemon-pokeBaseInfo-pokeBaseInfoAdd",
          query: { id: code, type: type}
        });
      }
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

.tag-show {
  display: inline;
  text-align: center;
  span {
    //height: 20px;
    //color: #35c97f;
    margin-right: 8px;
    width: 48px;
    //background-color: #eefff6;
    border: 1px solid;
  }
}

/* 缩略图样式 */
.thumbnail {
//  width: 200px;
  height: 25px;
//  border-radius: 4px;
//  border: 1px solid #ebeef5;
//  overflow: hidden;
//  cursor: pointer;
//  transition: all 0.3s;
}
</style>