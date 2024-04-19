import {getAllProp} from "@/api/propApi";

export default {
  data() {
    return {
      allPropList: [],
    };
  },
  created() {
    // 获取所有属性的css
    getAllProp()
      .then(res => {
        if (res.data?.code === 10041) {
          // console.log(res.data.data);
          this.allPropList = res.data?.data || [];
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
  },
  methods: {
    getColorBase(prop) {
      let result = this.allPropList.find(p => p.propName === prop);
      return result?.cssColorBase || "#303133";
    },
    getColorBackground(prop) {
      let result = this.allPropList.find(p => p.propName === prop);
      return result?.cssColorBackground || "#c0c4cc";
    },
    getColorBorder(prop) {
      let result = this.allPropList.find(p => p.propName === prop);
      return result?.cssColorBorder || "#606266";
    }
  }
};