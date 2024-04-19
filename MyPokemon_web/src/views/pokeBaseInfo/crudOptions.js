import { listOption } from "@/plugin/avue/option";
import { formatColumn } from "@/utils";

// let baseUrl = 'http://localhost:8081/web';
let baseUrl = process.env.VUE_APP_BASE_URL;

export const crudOptions = vm => {
  const config = Object.assign({}, listOption, {
    dialogWidth: "43%", // 表格弹窗宽度
    // span: 50,
    // height: vm.crudMaxHeight,
    // maxHeight: vm.crudMaxHeight - 54 || "320px", // 减去tab的高度
    menu: true,
    //menuType: "icon",
    selection: true, // 是否要多选框
    menuHeaderAlign: "center",
    menuAlign: "center",
    menuWidth: 100,
    index: true, // 是否要序号列
    indexLabel: "序号",
    indexWidth: 70,
    addBtn: false,
    addBtnIcon: "-",
    addBtnText: "新增",
    delBtn: false,
    editBtn: false,
    // addTitle: "新增人脸布控任务", // 表格新增弹窗标题
    // editTitle: "编辑通行策略", // 表格修改弹窗标题
    //viewTitle: "详情",
    loadingText: "Loading...",
    loadingSpinner: "svg",
    loadingSvgViewBox: "-10, -10, 50, 50",
    loadingBackground: "rgba(122, 122, 122, 0.8)",
    searchShowBtn: false,
    searchSpan: 3, // 搜索框长度
    searchLabelWidth: 60, // 搜索框标题长度 // 也在column属性中单独配置
    searchIndex: 6, // 表格搜索半收缩个数
    // searchGutter: 100,

    //formslot: true,
    column: [
      {
        label: "编号",
        prop: "pokeBaseCode",
        formslot: false, // 开放插槽
        search: true,
        // searchSpan: 6, // 搜索框长度
        // searchLabelWidth: 110, // 搜索框标题长度
        searchPlaceholder: "全部",
        overHidden: true,
        minWidth: 60,
        labelWidth: 100, // 表单标题宽度
        // addDisplay: false,
        // editDisabled: true,
        // order: 3,
        // searchOrder: 9,
        rules: [
          {
            required: true,
            // message: "请输入巡更组名称",
            trigger: "blur"
          }
        ]
      },
      {
        label: "名称",
        prop: "pokeBaseName",
        type: "input",
        formslot: false, // 开放插槽
        search: true,
        overHidden: true,
        minWidth: 250,
        // addDisplay: false,
        // editDisabled: true,
        // order: 8,
        rules: [
          {
            required: true,
            trigger: "blur"
          }
        ]
      },
      {
        label: "属性",
        prop: "prop1",
        type: "select",
        dicUrl: `${baseUrl}/web/prop`,
        props: {
          label: 'propName',
          value: 'propName'
        },
        formslot: false, // 开放插槽
        search: true,
        overHidden: false,
        minWidth: 250,
        // addDisplay: false,
        // editDisabled: true,
        // order: 8,
        formatter({ prop1, prop2 }) {
          let propList = prop2 ? [prop1, prop2] : [prop1];
          // nameList.sort();
          return propList ? propList.join("、") : "";
        },
        rules: [
          {
            required: true,
            trigger: "blur"
          }
        ]
      },
      {
        label: "属性2",
        prop: "prop2",
        hide: true,
        type: "select",
        dicUrl: `${baseUrl}/web/prop`,
        props: {
          label: 'propName',
          value: 'propName'
        },
        formslot: false, // 开放插槽
        search: true,
        overHidden: true,
        minWidth: 250,
        // addDisplay: false,
        // editDisabled: true,
        // order: 8,
        rules: [
          {
            required: true,
            trigger: "blur"
          }
        ]
      },
      {
        label: "世代",
        prop: "gen",
        type: "select",
        multiple: true,
        dicUrl: `${baseUrl}/web/gen`,
        props: {
          label: 'genName',
          value: 'genCode'
        },
        formslot: false, // 开放插槽
        search: true,
        overHidden: true,
        minWidth: 250,
        // addDisplay: false,
        // editDisabled: true,
        // type: "input",
        // order: 8,
        rules: [
          {
            required: true,
            trigger: "blur"
          }
        ]
      },
      {
        label: "日文",
        prop: "nameJpn",
        formslot: false, // 开放插槽
        search: false,
        overHidden: true,
        minWidth: 250,
        // addDisplay: false,
        // editDisabled: true,
        // type: "input",
        // order: 8,
        rules: [
          {
            required: true,
            trigger: "blur"
          }
        ]
      },
      {
        label: "英文",
        prop: "nameEng",
        formslot: false, // 开放插槽
        search: false,
        overHidden: true,
        minWidth: 250,
        // addDisplay: false,
        // editDisabled: true,
        // type: "input",
        // order: 8,
        rules: [
          {
            required: true,
            trigger: "blur"
          }
        ]
      }
    ]
  });
  return Object.prototype.toString.call(formatColumn) === "[object Function]" ? formatColumn(config) : config;
}