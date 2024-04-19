<template>
  <div>
    <div class="add-body-card">
      <el-card shadow="always">
        <div slot="header" class="add-body-title">
          <div class="title-text body-title">{{ pageTitle }}宝可梦</div>
          <el-button class="title-button" size="small" @click.stop="cancel">返 回</el-button>
          <el-button class="title-button" type="primary" size="small" :loading="btnLoading" @click.stop="submitForm">
            保 存
          </el-button>
        </div>
        <div class="add-body-form">
          <el-form :model="dialogForm" :rules="rules" ref="dialogForm" label-width="60px" :label-position="'top'" class="form-body">
            <div class="grid">
              <el-form-item label="编号" prop="pokeBaseCode">
                <el-input v-model="dialogForm.pokeBaseCode" :disabled=isEdit maxlength="10" show-word-limit clearable/>
              </el-form-item>

              <el-form-item label="名称" prop="pokeBaseName">
                <el-input v-model="dialogForm.pokeBaseName" maxlength="10" show-word-limit clearable/>
              </el-form-item>

              <el-form-item label="日文" prop="nameJpn">
                <el-input v-model="dialogForm.nameJpn" maxlength="50" show-word-limit clearable/>
              </el-form-item>

              <el-form-item label="世代" prop="gen">
                <mj-select v-model="dialogForm.gen" :dictFunc="getAllGen" :dicProp="genDict" placeholder="请选择"></mj-select>
              </el-form-item>

              <el-form-item label="属性" prop="props">
                <div class="form-item-prop">
                  <div class="tag-show">
                    <el-tag
                      v-if="!isEmpty(dialogForm.prop1)"
                      :style="{
                        'color':getColorBase(dialogForm.prop1),
                        'background-color':getColorBackground(dialogForm.prop1),
                        'border-color':getColorBorder(dialogForm.prop1)
                      }"
                    >
                      {{ dialogForm.prop1 }}
                    </el-tag>
                    <el-tag
                      v-if="!isEmpty(dialogForm.prop2)"
                      :style="{
                        'color':getColorBase(dialogForm.prop2),
                        'background-color':getColorBackground(dialogForm.prop2),
                        'border-color':getColorBorder(dialogForm.prop2)
                      }"
                    >
                      {{ dialogForm.prop2 }}
                    </el-tag>
<!--                    <el-tag v-for="item in dialogForm.propList" :key="item" >-->
<!--                      {{ item }}-->
<!--                    </el-tag>-->
                  </div>
                  <el-button class="choose-props" @click="chooseProps" size="small" round plain type="info">选 择</el-button>
                </div>
              </el-form-item>

              <el-form-item label="英文" prop="nameEng">
                <el-input v-model="dialogForm.nameEng" maxlength="50" show-word-limit clearable/>
              </el-form-item>

              <el-form-item label="图片" prop="pokeBasePic" class="span3">
                <el-input v-model="dialogForm.pokeBasePic" :disabled="true"/>
              </el-form-item>
            </div>
          </el-form>
        </div>
      </el-card>
    </div>

    <choose-props
      :showChooseProps.sync="showChooseProps"
      :originProps="propObj"
      @handleSubmitProp="handleSubmitProp"
      ref="choosePropDialog"
    />
  </div>
</template>

<script>
import ChooseProps from "@/views/pokeBaseInfo/components/ChooseProps.vue";
import {BASE_CONSTANT} from "@/views/baseConstants";
import { getBaseInfoDetail, addBaseInfo, updateBaseInfo } from "@/api/pokeBaseInfoApi";
import { getAllGen } from "@/api/genApi";
import {cloneDeep, isEmpty} from "lodash";
import CssProp from "@/mixin/cssProp";

export default {
  mixins: [CssProp],
  name: 'BaseInfoAdd',
  components: { ChooseProps },
  data() {
    return {
      api: {
        updateApi: updateBaseInfo,
        addApi: addBaseInfo,
      },
      originForm: {},
      dialogForm: {
        pokeBaseId: null,
        pokeBaseCode: "",
        pokeBaseName: "",
        gen: "",
        prop1: "",
        prop2: "",
        nameJpn: "",
        nameEng: "",
        pokeBasePic: ""
      },
      propObj: {
        prop1: "",
        prop2: "",
      },
      btnLoading: false,
      showChooseProps: false,
      genDict: {
        label: 'genName',
        value: 'genCode'
      },
      rules: {
        pokeBaseCode: [{ required: true, message: "必填项，请填写", trigger: "blur" }],
        pokeBaseName: [{ required: true, message: "必填项，请填写", trigger: "blur" }],
        gen: [{ required: true, message: "必填项，请填写", trigger: "blur" }],
        props: [{ required: true, message: "必填项，请填写", trigger: "blur" }],
      }
    };
  },
  computed: {
    // 编辑状态的宝可梦编号
    formId() {
      return this.$route?.query?.id;
    },
    // 单据状态
    formType() {
      return this.$route?.query?.type;
    },
    // 是否重置单据 新增时需要重置
    formReset() {
      return this.$route?.query?.reset;
    },
    isEdit() {
      return this.formType === BASE_CONSTANT.EDIT;
    },
    // 当前页面标题文本
    pageTitle() {
      if (this.formType === BASE_CONSTANT.EDIT) {
        return BASE_CONSTANT.EDIT_TXT;
      }
      return BASE_CONSTANT.ADD_TXT;
    }
  },
  watch: {
    formId: {
      immediate: true,
      handler(val) {
        if (val) {
          this.getBaseInfo4Edit(val);
        }
      }
    },
    formReset: {
      immediate: true,
      handler(val) {
        if (val) {
          // 清空数据 keep-alive开启时需要清空 todo
          this.resetFormData();
        }
      }
    }
  },
  methods: {
    getAllGen,
    isEmpty,
    async getBaseInfo4Edit(code) {
      // console.log(code);
      getBaseInfoDetail(code)
          .then(res => {
            // console.log(res);
            if (res.data.code === 10041) {
              // console.log(res.data);
              Object.assign(this.originForm, res?.data?.data || {});
              Object.assign(this.dialogForm, res?.data?.data || {});
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
      this.$router.go(-1);
    },
    // 重置数据
    resetFormData() {
      this.$route.query.reset = false;
      this.originForm = {};
      this.dialogForm = {
        pokeBaseId: null,
        pokeBaseCode: "",
        pokeBaseName: "",
        gen: "",
        prop1: "",
        prop2: "",
        nameJpn: "",
        nameEng: "",
        pokeBasePic: ""
      };
    },

    chooseProps() {
      this.propObj.prop1 = this.dialogForm.prop1;
      this.propObj.prop2 = this.dialogForm.prop2;
      this.showChooseProps = true;
    },
    handleSubmitProp(newProps) {
      // console.log("接收 属性", newProps);

      this.dialogForm.prop1 = newProps.prop1;
      this.dialogForm.prop2 = newProps.prop2;
      this.$refs.choosePropDialog.closeChooseProps();
    },

    /** 增删改 **/
    // 处理 提交表单
    submitForm() {
      console.log("提交", this.dialogForm);

      let submitForm;
      submitForm = cloneDeep(this.dialogForm);

      if (this.formType === BASE_CONSTANT.EDIT) {
        this.rowUpdate(submitForm);
      } else {
        this.rowSave(submitForm);
      }
    },

    // 发送新增请求
    async rowSave(submitForm) {
      if (!this.api.addApi) {
        this.$message({
          message: "请设置api.add属性!",
          type: "warning"
        });
        return;
      }

      if (!this.verifyForm(submitForm)) {
        return;
      }

      this.api
          .addApi(submitForm)
          .then(res => {
            if (res.data.code === 10011) {
              this.$message({
                showClose: true,
                message: BASE_CONSTANT.ADD_SUCCESS_TXT,
                type: "success"
              });
              setTimeout(() => {
                this.$router.go(-1); // todo 是否改成切换路由
              }, 0);
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
    // 发送修改请求
    async rowUpdate(submitForm) {
      if (!this.api.updateApi) {
        this.$message({
          message: "请设置api.update属性!",
          type: "warning"
        });
        return;
      }

      if (!this.verifyForm(submitForm)) {
        return;
      }

      this.api
          .updateApi(submitForm)
          .then(res => {
            if (res.data.code === 10031) {
              this.$message({
                showClose: true,
                message: BASE_CONSTANT.EDIT_SUCCESS_TXT,
                type: "success"
              });
              setTimeout(() => {
                this.$router.go(-1); // todo 是否改成切换路由
              }, 0);
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

    // 检查数据有效性
    verifyForm(submitForm) {
      if (isEmpty(submitForm.pokeBaseCode)) {
        this.$message({
          showClose: true,
          message: "请填入编号！",
          type: "warning"
        });
        return false;
      }

      if (isEmpty(submitForm.pokeBaseName)) {
        this.$message({
          showClose: true,
          message: "请填入宝可梦名称！",
          type: "warning"
        });
        return false;
      }

      if (isEmpty(submitForm.gen)) {
        this.$message({
          showClose: true,
          message: "请选择世代！",
          type: "warning"
        });
        return false;
      }

      if (isEmpty(submitForm.prop1)) {
        this.$message({
          showClose: true,
          message: "请选择属性！",
          type: "warning"
        });
        return false;
      }

      return true;
    }
  }
}
</script>

<style scoped lang="scss">
.add-body-card {
  width: 50%;
  margin: auto;
  padding: 32px 0 0 0;

  .view-body-title:before,
  .view-body-title:after {
    display: table;
    content: "";
  }
  .view-body-title:after {
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

::v-deep .add-body-form {
  .el-form-item {
    margin-right: 0;
  }

  .el-form-item__label {
    //padding: 0 0 0 0;
  }

  .el-select {
    display: block;
  }

  .form-item-prop {
    display: flex;
    align-items: center;

    .choose-props {
      //float: right;
      margin-left: auto;
    }

    .tag-show {
      display: inline;
      text-align: center;
      span {
        //color: #35c97f;
        margin-right: 16px;
        width: 80px;
        //background-color: #eefff6;
        border: 1px solid #b0f9be;
      }
    }
  }
}

// grid布局
.grid {
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