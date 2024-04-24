import request from "@/utils/request";
// let PREFIX = "/sp-security/web/access/strategy";
// let PREFIX2 = "/sp-security";
let PREFIX = "http://localhost:8081/web/gen";
let PREFIX2 = "http://localhost:8080";

// 如果后端提供的接口满足统一要求，则接口的前缀放置在/config/index.development.json的baseURL中，在接口页面直接url后面跟上后缀
// 如果不满足统一接口要求，则直接写成baseURL：当下的地址

// 查询所有世代
export function getAllGen() {
  return request({
    url: `${PREFIX}`,
    method: "GET",
  });
}