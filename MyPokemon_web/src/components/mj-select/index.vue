<template>
  <div class="mj-select">
    <!-- v-bind="$attrs" 用于将父组件传递给子组件的 attribute 绑定到子组件的元素上 -->
    <el-select v-model="val" v-bind="$attrs" :multiple="multiple" :filterable="filterable" @change="handleChange" clearable>
      <el-option
          v-for="item in options"
          :key="item[dicProp.value]"
          :value="item[dicProp.value]"
          :label="item[dicProp.label]"
      />
    </el-select>
  </div>
</template>

<script>
import dicMixin from "../mixin/dicMixin";
import {cloneDeep} from "lodash";

export default {
  mixins: [dicMixin],
  props: {
    value: {
      required: true
    },
    multiple: {
      type: Boolean,
      default: false
    },
    filterable: {
      type: Boolean,
      default: true
    },
    optionList: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      val: null
    };
  },
  computed: {
    options() {
      // 优先使用父组件传入的option
      if (this.optionList.length > 0) {
        return cloneDeep(this.optionList);
      } else {
        return cloneDeep(this.dicOptionList);
      }
    }
  },
  watch: {
    value: {
      immediate: true,
      handler(newVal) {
        if (this.isString) {
          if (newVal === "" || newVal === null || newVal === undefined) {
            this.val = [];
            return;
          }
          this.val = newVal.split(this.separator);
        } else {
          this.val = newVal;
        }
      }
    }
  },
  created() {
    this.getDictData();
  },
  methods: {
    // 选中值发生变化时触发
    handleChange(newVal) {
      if (this.isString && this.multiple) {
        newVal = newVal.join(this.separator);
      }
      this.$emit("input", newVal);
    },
    async getDictData() {
      // 判断是否传入了dictFunc
      if (!this.dictFunc) {
        console.log("mj-select: ", "No dictFunc");
        return;
      }
      // 判断是否是有效的dictFunc
      // todo 会调用三次，不合理
      if (!this.dictFunc() || typeof this.dictFunc().then !== 'function') {
        console.log("mj-select: ", "Invalid dictFunc");
        return;
      }

      this.dictFunc()
          .then(res => {
            if (res?.data?.code === 10041) {
              // console.log(res.data.data);
              this.dicOptionList = res.data?.data || [];
            } else {
              this.$message({
                showClose: true,
                message: res?.data?.msg,
                type: "error"
              });
            }
          })
          .catch(err => {
            console.log(err, "接口请求失败");
          });
    }
  }
};
</script>

<style scoped lang="scss"></style>