<template>
  <div>
    <div class="view-body-card">
      <el-card shadow="always">
        <div slot="header" class="view-body-title">
            <div class="title-text body-title">基本信息</div>
            <el-button class="title-button" size="small" @click.stop="cancel">返 回</el-button>
          <el-button class="title-button edit-button" size="small" @click.stop="showEdit">编 辑</el-button>
        </div>

        <div class="view-body-base-info">
          <el-descriptions :column="3" direction="vertical" border :labelStyle="detailLabelStyle" :contentStyle="detailContentStyle">
            <el-descriptions-item label="编号">
              {{ form.pokeBaseCode }}
            </el-descriptions-item>

            <el-descriptions-item label="名称">
              {{ form.pokeBaseName }}
            </el-descriptions-item>

            <el-descriptions-item label="日文">
              {{ form.nameJpn }}
            </el-descriptions-item>

            <el-descriptions-item label="世代">
              {{ form.gen }}
            </el-descriptions-item>

            <el-descriptions-item label="属性">
              <div class="tag-show">
                <el-tag
                  v-if="!isEmpty(form.prop1)"
                  :style="{
                    'color':getColorBase(form.prop1),
                    'background-color':getColorBackground(form.prop1),
                    'border-color':getColorBorder(form.prop1)
                  }"
                >
                  {{ form.prop1 }}
                </el-tag>
                <el-tag
                  v-if="!isEmpty(form.prop2)"
                  :style="{
                    'color':getColorBase(form.prop2),
                    'background-color':getColorBackground(form.prop2),
                    'border-color':getColorBorder(form.prop2)
                  }"
                >
                  {{ form.prop2 }}
                </el-tag>
              </div>

            </el-descriptions-item>

            <el-descriptions-item label="英文">
              {{ form.nameEng }}
            </el-descriptions-item>

            <el-descriptions-item label="图片">
              <el-image
                  v-if="pokePicFlag"
                  :src="context(`${picPath}`)"
                  :preview-src-list="[context(`${picPath}`)]"
                  class="showImage"
                  fit="cover"
                  @mouseover="mouseOverStyle(0)"
                  @mouseout="mouseOutStyle(0)"
              />
              <span v-else>无图片 {{ form.pokeBasePic }}</span>
            </el-descriptions-item>
          </el-descriptions>

          <div class="view-body-link grid-link">
            <el-button @click.stop="toPrev" type="text" class="left-button">&larr; &nbsp; No.{{ form.prevCode }}</el-button>
            <div class="evolution-button">
              <el-button @click.stop="toEvolution" v-if="hasEvol" type="text">进化</el-button>
              <el-button @click.stop="toFilial" v-if="hasFilial" type="text">后退</el-button>
            </div>
            <el-button @click.stop="toNext" type="text" class="right-button">No.{{ form.nextCode }} &nbsp; &rarr;</el-button>
          </div>
        </div>
      </el-card>

      <div class="container-card">
        <el-card v-if="evolList.length > 1" v-show="showEvol" class="evolution-card" :style="{ width: computeEvolWidth(evolList.length) }">
          <div class="evolution-body">
            <div v-for="evol in evolPokeList" :key="evol.pokeBaseCode" class="evol-item">
              <el-button @click="redirectDetail(evol.pokeBaseCode)" type="text">
                {{ evol.pokeBaseName }}
              </el-button>
            </div>
          </div>
        </el-card>

        <el-card v-if="filialList.length > 1" v-show="showFilial" class="evolution-card" :style="{ width: computeEvolWidth(filialList.length) }">
          <div class="evolution-body">
            <div v-for="fil in filialPokeList" :key="fil.pokeBaseCode" class="evol-item">
              <el-button @click="redirectDetail(fil.pokeBaseCode)" type="text">
                {{ fil.pokeBaseName }}
              </el-button>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
import { getBaseInfoDetail, getByCodeList } from "@/api/pokeBaseInfoApi";
import { BASE_CONSTANT } from "@/views/baseConstants";
import {cloneDeep, isEmpty} from "lodash";
import CssProp from "@/mixin/cssProp";

const PATH = "PokemonPics"
// 用于构造获取图片 目录的上下文环境【require不能用纯动态地址】
const context = require.context('D://PokemonPics', true, /.(png|jpg)$/);

export default {
  mixins: [CssProp],
  name: 'BaseInfoDetail',
  data() {
    return {
      form: {
        pokeBaseId: null,
        pokeBaseCode: "",
        pokeBaseName: "",
        gen: "",
        prop1: "",
        prop2: "",
        nameJpn: "",
        nameEng: "",
        pokeBasePic: "",
        evolution: "",
        filial: "",
        prevCode: "",
        nextCode: ""
      },
      pokePicFlag: false,
      showEvol: false,
      showFilial: false,
      evolPokeList: [],
      filialPokeList: [],
      //详情中描述列表的样式
      detailLabelStyle: {},
      detailContentStyle: {}
    };
  },
  mounted() {
    this.detailLabelStyle = cloneDeep(BASE_CONSTANT.DETAIL_LABEL_STYLE);
    this.detailContentStyle = cloneDeep(BASE_CONSTANT.DETAIL_CONTENT_STYLE);
  },
  computed: {
    // 宝可梦编号
    formId() {
      return this.$route?.query?.id;
    },
    picPath() {
      if (!isEmpty(this.form.pokeBasePic)) {
        // 构造相对路径【基于require.context上下文】
        let path = '.' + this.form.pokeBasePic;
        console.log("图片路径", path);
        return path;
      } else {
        return "";
      }
    },
    hasEvol() {
      return !isEmpty(this.form.evolution);
    },
    hasFilial() {
      return !isEmpty(this.form.filial);
    },
    evolList() {
      if (!this.hasEvol) {
        return [];
      }
      return this.form.evolution.split(BASE_CONSTANT.SPLIT_CAESURA);
    },
    filialList() {
      if (!this.hasFilial) {
        return [];
      }
      return this.form.filial.split(BASE_CONSTANT.SPLIT_CAESURA);
    }
  },
  watch: {
    formId : {
      immediate: true,
      handler(val) {
        if (val) {
          this.getBaseInfoDetail(val);
        }
        this.showEvol = false;
        this.showFilial = false;
      }
    },
    evolList: {
      deep: true,
      immediate: true,
      handler(val) {
        if (val.length > 1) {
          this.getEvolList(val);
        }
      }
    },
    filialList: {
      deep: true,
      immediate: true,
      handler(val) {
        if (val.length > 1) {
          this.getFilialList(val);
        }
      }
    },
    // 检测图片路径是否有效
    "form.pokeBasePic": {
      immediate: false,
      handler(val) {
        let flag = false;
        try {
          let pic = context(`${this.picPath}`);
          flag = true;
        } catch (err) {
          // console.log("err", err);
        } finally {
          console.log("pic", flag);
          this.pokePicFlag = flag;
        }
      }
    }
  },
  methods: {
    context,
    isEmpty,
    async getBaseInfoDetail(code) {
      // console.log(code);
      getBaseInfoDetail(code)
          .then(res => {
            // console.log(res);
            if (res.data.code === 10041) {
              // console.log(res.data);
              Object.assign(this.form, res?.data?.data || {});
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
    async getEvolList(evolList) {
      // console.log("获取进化", evolList);
      getByCodeList(evolList)
          .then(res => {
            // console.log(res);
            if (res.data.code === 10041) {
              // console.log(res.data);
              this.evolPokeList = res?.data?.data || [];
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
    async getFilialList(filList) {
      // console.log("获取后退", filList);
      getByCodeList(filList)
          .then(res => {
            // console.log(res);
            if (res.data.code === 10041) {
              // console.log(res.data);
              this.filialPokeList = res?.data?.data || [];
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
    cancel(event) {
      // console.log("event2", event.ctrlKey);

      this.$router?.push({
        name: "MyPokemon-pokeBaseInfo-pokeBaseInfoList"
      });

      // this.$router.go(-1);
    },

    // 显示 编辑 页
    showEdit() {
      let code = this.formId;
      let type = BASE_CONSTANT.EDIT;

      this.$router?.push({
        name: "MyPokemon-pokeBaseInfo-pokeBaseInfoAdd",
        query: { id: code, type: type}
      });
    },
    toEvolution() {
      if (this.evolList.length > 1) {
        this.showEvol = !this.showEvol;
      } else {
        this.redirectDetail(this.form.evolution);
      }
    },
    toFilial() {
      if (this.filialList.length > 1) {
        this.showFilial = !this.showFilial;
      } else {
        this.redirectDetail(this.form.filial);
      }
    },
    toPrev() {
      this.redirectDetail(this.form.prevCode);
    },
    toNext() {
      this.redirectDetail(this.form.nextCode);
    },
    // 跳转 详情页
    redirectDetail(code) {
      this.$router?.push({
        name: "MyPokemon-pokeBaseInfo-pokeBaseInfoDetail",
        query: { id: code }
      });
    },

    computeEvolWidth(len) {
      let per = Math.min(len/12*100, 100);
      return `${per}%`;
    },

    //鼠标移入图片样式
    mouseOverStyle(index) {
      // const res = document.getElementsByClassName("showImage");
      // // console.log(res);
      // Array.from(res)[index].style.transform = "scale(5)";
    },
    //鼠标移出图片样式
    mouseOutStyle(index) {
      // const res = document.getElementsByClassName("showImage");
      // Array.from(res)[index].style.transform = "scale(1)";
    }
  }
}
</script>

<style scoped lang="scss">
.view-body-card {
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

  .edit-button {
    margin-right: 16px;
  }

  .view-body-base-info {
    .view-body-link {
      .el-button {
        padding: 16px 0 0 0;
      }

      .left-button {
        //float: left;
        margin-right: auto;
      }

      .right-button {
        //float: right;
        margin-left: auto;
      }

      .evolution-button {
        display: flex;
        justify-content: center; // 水平居中
        //align-items: center;
      }
    }

    // grid布局
    .grid-link {
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
  }

  ::v-deep .container-card {
    display: flex;
    flex-direction: column; /* 设置为列布局 */
    align-items: center; /* 水平居中 */

    .evolution-card {
      .el-card__body {
        padding: 8px;
      }

      .evolution-body {
        display: flex;
        justify-content: center;

        .evol-item {
          padding: 0 16px 0 16px;
        }
      }
    }
  }


  .body-title {
    display: inline;
  }

  .tag-show {
    display: inline;
    text-align: center;
    span {
      //color: #35c97f;
      margin-right: 8px;
      width: 50px;
      //background-color: #eefff6;
      border: 1px solid #b0f9be;
    }
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
}

.showImage {
  //width: 60px;
  height: 200px;
}
</style>