import request from "@/utils/request";
// let PREFIX = "/sp-security/web/access/strategy";
// let PREFIX2 = "/sp-security";
let PREFIX = "http://localhost:8081/web/mypokes";
let PREFIX2 = "http://localhost:8080";

// 如果后端提供的接口满足统一要求，则接口的前缀放置在/config/index.development.json的baseURL中，在接口页面直接url后面跟上后缀
// 如果不满足统一接口要求，则直接写成baseURL：当下的地址

// 条件查询宝可梦信息
export function getBaseInfoList(data) {
  return request({
    url: `${PREFIX}/pageList`,
    method: "POST",
    data
  });
}

// 查询所有标签
export function getAllBaseInfo() {
  return request({
    url: `${PREFIX}`,
    method: "GET"
  });
}