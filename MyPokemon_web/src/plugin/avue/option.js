// 编辑表格最大高度
export const EDIT_TABLE_MAX_HEIGHT = 400;
// 弹窗表格最大高度
export const DIALOG_TABLE_MAX_HEIGHT = 400;
//crud操作列菜单配置
export const menuConst = {
  menuAlign: "center",
  menuWidth: 64,
  //搜索区域折叠配置
  searchIcon: true, //是否启用功能按钮
  searchIndex: 3 //配置收缩展示的个数
};

//已废弃，默认CRUD选项
export const optionConst = {
  searchIndex: 3,
  searchIcon: true,
  searchMenuPosition: "left",
  // searchBtnIcon: " ",
  // emptyBtnIcon: " ",
  searchLabelPosition: "left",
  labelSuffix: " ",
  searchMenuSpan: 6,
  border: true,
  index: true,
  // indexLabel: "序号",
  stripe: true,

  menu: true,
  highlightCurrentRow: true,
  searchShow: true,
  menuBtn: true,
  searchBtn: true,
  filterBtn: false,
  columnBtn: false,
  excelBtn: false,
  printBtn: false,
  cellBtn: false,
  addBtn: false, // 默认true
  editBtn: false,
  delBtn: false,
  // sortable: true,
  rowSort: false,

  searchFilterable: false,

  fit: true,
  maxHeight: 380,
  labelPosition: "top",

  defaultSort: {
    prop: "updatedDate",
    order: "descending"
  },
  ...menuConst
};
// 查询列表页配置
export const listOption = {
  rowKey: "id",
  searchIndex: 3,
  searchIcon: true,
  searchMenuPosition: "left",
  searchBtnIcon: " ",
  emptyBtnIcon: " ",
  //弹窗表单按钮统一去掉icon
  editBtnIcon: " ",
  saveBtnIcon: " ",
  updateBtnIcon: " ",
  cancelBtnIcon: " ",
  searchLabelPosition: "right",
  labelSuffix: " ",
  updateBtnText: "保存",
  searchMenuSpan: 6,
  addBtn: false,
  editBtn: false,
  delBtn: false,
  refreshBtn: false,
  filterBtn: false,
  columnBtn: false,
  align: "left",
  headerAlign: "left",
  menuAlign: "center",
  menuHeaderAlign: "center",
  menuWidth: "100",
  menuFixed: "right",
  viewTitle: "详情",
  // sortable: true,
  rowSort: false,
  tip: false,
  index: true,
  gutter: 16,
  selection: false,
  border: true,
  highlightCurrentRow: true
};
// 查询列表页高度
export function crudMaxHeight() {
  return window.innerHeight - 32 - 90 - 220;
}
// 单据详情简单表格配置
export const simpleOption = {
  border: true,
  index: true,
  // indexLabel: "序号",
  header: false,
  menu: false,
  stripe: false,
  tip: false
  // defaultSort: {
  //   prop: "updatedDate",
  //   order: "descending"
  // },
};
// 行内表格编辑
export const simpleEditOption = {
  rowKey: "id",
  index: true,
  border: true,
  filterDic: true,
  addBtn: false,
  addRowBtn: true,
  cellBtn: true,
  refreshBtn: false,
  columnBtn: false,
  addBtnIcon: " ",
  editBtnIcon: " ",
  delBtnIcon: " ",
  saveBtnIcon: " ",
  cancelBtnIcon: " ",
  tip: false
};

// 弹窗表格编辑
export const dialogEditOption = {
  rowKey: "id",
  border: true,
  filterDic: true,
  refreshBtn: false,
  columnBtn: false,
  addBtnIcon: " ",
  editBtnIcon: " ",
  delBtnIcon: " ",
  labelPosition: "top"
};

export const pageConst = {
  pageSizes: [15, 20, 50, 100], //默认
  currentPage: 1,
  total: 0,
  pageSize: 15
};

export const formOptionConst = {
  menuBtn: false,
  emptyBtn: false,
  submitBtn: false,
  labelPosition: "top",
  labelSuffix: " ",
  size: "small",
  gutter: 16
};
export const formReminder = {
  menuBtn: false, //操作按钮是否显示
  emptyBtn: false, //清空按钮
  submitBtn: false, //提交按钮
  labelPosition: "left"
  // labelSuffix: " ",
  // size: "mini",
  // gutter: 26
};

//无人机巡防：任务管理列表页配置
export const taskOption = {
  rowKey: "id",
  searchIndex: 3,
  searchIcon: false,
  searchMenuPosition: "left",
  searchLabelPosition: "left",
  labelSuffix: " ",
  searchMenuSpan: 6,
  addBtn: true,
  editBtn: false,
  delBtn: false,
  excelBtn: false,
  refreshBtn: true,
  filterBtn: false,
  columnBtn: false,
  align: "left",
  headerAlign: "left",
  menuAlign: "left",
  menuHeaderAlign: "left",
  menuWidth: "100",
  menuFixed: "right",
  rowSort: false,
  tip: false,
  index: false,
  gutter: 16,
  selection: false,
  border: true,
  highlightCurrentRow: true
};
