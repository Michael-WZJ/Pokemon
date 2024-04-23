<template>
  <div class="prop-color-body">
    <el-card shadow="always">
      <div slot="header" class="card-title">
        <div class="title-text body-title">属性 - 颜色</div>
        <el-button class="title-button" size="small" v-show="!isEdit" @click="isEdit=true">修 改</el-button>
        <el-button class="title-button" size="small" v-show="isEdit" @click="cancel">返 回</el-button>
        <el-button class="title-button" type="primary" v-show="isEdit" :disabled="!canSubmit" size="small" :loading="btnLoading" @click.stop="submitProp">
          保 存
        </el-button>
      </div>

      <div class="card-body">
        <div v-for="item in propList" :key="item.propSort" class="prop-item">
          <div class="prop-item-tag">
            <el-tag
              :style="{
                'color':item.cssColorBase,
                'background-color':item.cssColorBackground,
                'border-color':item.cssColorBorder
              }"
            >
              {{ item.propName }}
            </el-tag>
          </div>

          <div class="grid-prop">
            <el-input v-model.trim="item.cssColorBase" :disabled="!item.edit" clearable></el-input>
            <el-input v-model.trim="item.cssColorBackground" :disabled="!item.edit" clearable></el-input>
            <el-input v-model.trim="item.cssColorBorder" :disabled="!item.edit" clearable></el-input>
          </div>

          <div class="prop-item-button" v-show="isEdit">
            <el-button v-show="!item.edit" @click="editProp(item.propName)" type="text">修改</el-button>
            <el-button v-show="item.edit" @click="cancelEditProp(item.propName)" type="text">取消</el-button>
          </div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import {batchUpdateColor, getAllProp} from "@/api/propApi";
import {cloneDeep} from "lodash";
import {BASE_CONSTANT} from "@/views/baseConstants";

export default {
  name: "PropColor",
  data() {
    return {
      api: {
        listApi: getAllProp
      },
      originPropList: [],
      propList: [],
      isEdit: false,
      btnLoading: false
    };
  },
  computed: {
    canSubmit() {
      let editList = this.propList.filter(p => p.edit === true);
      return editList.length > 0;
    }
  },
  created() {
    this.onLoad();
  },
  methods: {
    onLoad() {
      this.getAllProp();
    },
    async getAllProp() {
      this.api
          .listApi()
          .then(res => {
            if (res.data.code === 10041) {
              this.originPropList = res.data?.data || [];
              this.originPropList.forEach(prop => prop.edit = false);

              this.propList = cloneDeep(this.originPropList);
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
    },
    cancel() {
      this.isEdit = false;
      // this.onLoad();
      this.propList = cloneDeep(this.originPropList);
    },

    async submitProp() {
      let data = this.propList.filter(p => p.edit === true);
      // console.log(data);
      if (data.length === 0) {
        return;
      }

      batchUpdateColor(data)
          .then(res => {
            if (res.data.code === 10031) {
              this.$message({
                showClose: true,
                message: res.data.data,
                type: "success"
              });

              this.isEdit = false;
              this.onLoad();
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
    },

    editProp(name) {
      let prop = this.propList.find(p => p.propName === name);
      // console.log(prop);
      prop.edit = true;
    },
    cancelEditProp(name) {
      let prop = this.propList.find(p => p.propName === name);
      // console.log(prop);
      prop.edit = false;
      Object.assign(prop, {}, this.originPropList.find(p => p.propName === name));
    }
  }
}
</script>

<style scoped lang="scss">
.prop-color-body {
  //width: 50%;
  //padding: 32px 0 0 0;

  .card-title:before,
  .card-title:after {
    display: table;
    content: "";
  }
  .card-title:after {
    clear: both
  }

  .body-title {
    display: inline;
  }
}

.title-text {
  height: 24px;
  /* margin-left: 8px; */

  /* PF/Text/Medium/16 */
  font-family: PingFang SC;
  font-size: 18px;
  font-weight: normal;
  line-height: 24px;
  text-align: center;
  display: flex;
  align-items: center;
  letter-spacing: 0em;

  /* 文字色/85-标题 */
  color: rgba(0, 0, 0, 0.85);
}

.title-button {
  float: right;
  margin: 0 0 0 16px;
}

.prop-item {
  display: flex;
  padding: 0 0 16px 0;

  .prop-item-tag {
    width: 15%;
    padding-right: 16px;
    text-align: center;

    span {
      width: 100%;
    }
  }

  .prop-item-button {
    //display: flex;
    margin: 0 0 0 16px;

    .el-button+.el-button {
      margin-left: 0;
    }
  }
}

// grid布局
.grid-prop {
  display: grid;
  grid-column-gap: 16px;
  grid-template-columns: repeat(3, 1fr);

  .span2 {
    grid-column-start: span 2;
  }

  .span3 {
    grid-column-start: span 3;
  }

  .span4 {
    grid-column-start: span 4;
  }
}
</style>