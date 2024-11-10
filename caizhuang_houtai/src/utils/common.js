// import MD5 from 'md5'
import axios from 'axios'
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









