import axios from "axios";

const service = axios.create({
  // baseURL: window[process.env.VUE_APP_PAGE_CONFIG].baseUrl, // api的base_url
  timeout: 60000,
  headers: {
    "Content-Type": "application/json;charset=UTF-8",
    Accept: "application/json"
  },
  responseType: "json"
  // withCredentials: false
});

export default service;