import request from "@/utils/request";
let baseUrl = process.env.VUE_APP_BASE_URL;
let PREFIX = baseUrl + "/web/poke/picture";
let PREFIX2 = "http://localhost:8081";

// 如果后端提供的接口满足统一要求，则接口的前缀放置在/config/index.development.json的baseURL中，在接口页面直接url后面跟上后缀
// 如果不满足统一接口要求，则直接写成baseURL：当下的地址


// 按编号获取图片 暂未使用
export function getPicByCode(code) {
  return request({
    url: `${PREFIX}/${code}`,
    method: "GET",
    loading: false
  });
}