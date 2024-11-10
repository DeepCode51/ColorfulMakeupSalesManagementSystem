import axios from 'axios'
// 公共路由(网络请求地址)
// axios.defaults.baseURL = 'http://localhost:8089/hf';
// 请求响应超时时间
axios.defaults.timeout = 30000;
let base = 'http://localhost:8089/hf/';
function sendServer(urlParams, callback){
  var params ={};
  params = urlParams.send;
	axios({
	  method: 'post',
	  url:`${base}${urlParams.url}`,
	//   url: urlParams.url,
	  data: params
	}).then((res)=>{
		 if (typeof(callback) == 'function') {
			 callback(res.data)
		 }
	})
}

function sendServerGet(urlParams, callback){
	var params ={};
	params = urlParams.send;
	axios({
		method: 'get',
		url:`${base}${urlParams.url}`,
		params: params
	}).then((res)=>{
		if (typeof(callback) == 'function') {
			callback(res.data)
		}
	})
  }
export { sendServer,sendServerGet}









