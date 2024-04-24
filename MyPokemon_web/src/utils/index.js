
const textWidthDom = document.createElement("span");
textWidthDom.style.display = "inline-block";
textWidthDom.style.fontSize = "14px"; // 目前表头是 14px

export function getTextWidth(str = "", sortable = false) {
  textWidthDom.textContent = str;
  document.body.appendChild(textWidthDom);
  const width = textWidthDom.clientWidth + 30 + (sortable ? 34 : 10); // 表头 padding 20
  document.body.removeChild(textWidthDom);
  return width;
}
//递归查找子栏目计算宽度
export function setWidth(arr, i18n = null) {
  arr.forEach(c => {
    if (i18n) {
      if (c.type === "select") c.placeholder = i18n("请选择");
      else c.placeholder = i18n("请输入");
    }
    if (c.children && c.children.length) {
      setWidth(c.children);
    } else {
      const width = getTextWidth(c.label, c.sortable);
      // if (c.minWidth != null && c.minWidth <= width) {
      //   c.width = width;
      // }
      if ((c.minWidth && c.minWidth < width) || !c.minWidth) {
        c.minWidth = width;
      }
      c.type === "select" && (c.filterable = true);
      //列表搜索select多选样式修改
      c.multiple && (c.tags = true);
      c.type === "datetime" && (c.format = "yyyy-MM-dd hh:mm:ss");
      c.overHidden !== false && (c.overHidden = true);
    }
  });
  return arr;
}

// avue-crud组件搜索布局格式化
export function handleSearchColumn(columns) {
  columns = columns.filter(item => item.prop !== "colHolder");
  const searchColumns = columns.filter(item => item.search);
  if (searchColumns.length < 4) return columns;
  searchColumns.sort((a, b) => {
    let aSearchOrder = a.searchOrder || 0;
    let bSearchOrder = b.searchOrder || 0;
    return bSearchOrder - aSearchOrder;
  });
  const insertIndex = columns.findIndex(item => item.prop === searchColumns[2].prop);
  const insertColumn = searchColumns[2];
  columns.splice(insertIndex + 1, 0, {
    label: "",
    prop: "colHolder",
    search: true,
    show: false,
    display: false,
    addDisplay: false,
    editDisplay: false,
    viewDisplay: false,
    hide: true,
    showColumn: false,
    searchOrder: insertColumn.searchOrder || 0
  });
  return columns;
}

// avue-curd组件空值处理
export function handleNullValue(columns) {
  columns.forEach(column => {
    if (typeof column.formatter != "function") {
      column.formatter = (row, value) => {
        if (row[column.prop] === 0 || row[column.prop]) {
          if (Array.isArray(column.dicData) && column.prop.indexOf("handleByDict") === -1) {
            return column.dicData?.filter(p => p.value === row[column.prop])?.[0]?.label || row[column.prop];
          }
          return row[column.prop];
        } else {
          return "--";
        }
      };
    }
  });
  return columns;
}

// 处理列
/**
 * 处理avue-crud列
 * @param {*} option avue-crud配置
 * @param {*} i18n i18n实例对象
 * @param {*} formatNull 是否对空值进行处理
 */
export function formatColumn(option, i18n = null, formatNull = true) {
  option.column = setWidth(option.column, i18n); // 设置宽度
  // option.column = handleByDict(option.column); // 设置select类型显示
  if (formatNull) option.column = handleNullValue(option.column); // 处理空值
  // option.column = handleSearchColumn(option.column); // 设置搜索布局 //wzj注解
  return option;
}