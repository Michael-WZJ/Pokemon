<template>
  <el-dialog
      width="20%"
      :title="'选择属性'"
      :close-on-click-modal="true"
      :close-on-press-escape="false"
      :before-close="closeChooseProps"
      :visible.sync="showChooseProps"
  >
    <div class="choose-prop-body">
      <el-form :model="propObj" :rules="rules" ref="choosePropForm" label-width="60px" :label-position="'right'" class="form-body">
        <el-form-item label="属性1" prop="prop1">
          <mj-select v-model="propObj.prop1" :dictFunc="getAllProp" :dicProp="propDict" placeholder="请选择"></mj-select>
        </el-form-item>

        <el-form-item label="属性2" prop="prop2">
          <mj-select v-model="propObj.prop2" :dictFunc="getAllProp" :dicProp="propDict" placeholder="请选择"></mj-select>
        </el-form-item>
      </el-form>
    </div>

    <span slot="footer" class="dialog-footer">
      <!--      <el-button @click="printTest">测试</el-button>-->
      <el-button @click="closeChooseProps" size="small">取 消</el-button>
      <el-button type="primary" @click="submitProps" :disabled="!propsUpdated" size="small">保 存</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { getAllProp } from "@/api/propApi";
import { cloneDeep, isEmpty } from "lodash";
import fa from "element-ui/src/locale/lang/fa";

export default {
  name: "ChooseProps",
  props: {
    showChooseProps: {
      type: Boolean,
      default: false
    },
    originProps: {
      type: Object,
      require: true
    },
  },
  data() {
    return {
      propObj: {
        prop1: "",
        prop2: ""
      },
      rules: {
        prop1: [{ required: true, message: "必填项，请填写", trigger: "blur" }],
      },
      propDict: {
        label: 'propName',
        value: 'propName'
      },
    };
  },
  computed: {
    propsUpdated() {
      return !(this.originProps.prop1 === this.propObj.prop1
          && this.originProps.prop2 === this.propObj.prop2);
    }
  },
  watch: {
    originProps: {
      deep: true,
      immediate: true,
      handler(newVal) {
        this.propObj = cloneDeep(newVal);
      }
    }
  },
  methods: {
    getAllProp,
    //关闭弹窗
    closeChooseProps() {
      this.$emit("update:showChooseProps", false);
      this.propObj = cloneDeep(this.originProps);
    },
    submitProps() {
      // console.log("提交");
      if (!this.verifyProps(this.propObj)) {
        return;
      }
      let data = cloneDeep(this.propObj);
      this.$emit("handleSubmitProp", data);
      // console.log("提交了");
    },

    // 检查数据有效性
    verifyProps(propObj) {
      if (isEmpty(propObj.prop1)) {
        this.$message({
          showClose: true,
          message: "请选择属性1 ！",
          type: "warning"
        });
        return false;
      }

      if (propObj.prop1 === propObj.prop2) {
        this.$message({
          showClose: true,
          message: "属性1与属性2不能相同 ！",
          type: "warning"
        });
        return false;
      }

      return true;
    }
  }
};
</script>

<style scoped></style>