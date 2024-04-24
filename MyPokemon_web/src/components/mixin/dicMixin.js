export default {
  props: {
    isString: {
      // 传给该组件的值 是否是 单个字符串
      type: Boolean,
      default: false
    },
    separator: {
      type: String,
      default: ","
    },
    dicProp: {
      type: Object,
      default: () => ({
        label: 'label',
        value: 'value'
      })
    },
    dictFunc: {
      type: Function,
      default: null
    }
  },
  data() {
    return {
      dicOptionList: []
    };
  },
  computed: {

  },
  watch: {

  }
};