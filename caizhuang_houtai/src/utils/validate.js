/**
 * Created by PanJiaChen on 16/11/18.
 */

/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validUsername(str) {
  const valid_map = ['admin', 'editor']
  return valid_map.indexOf(str.trim()) >= 0
  // var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>《》/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
  // // /[!~@#$%&=]+/gi.test(str)
  
  // var a=new RegExp("/^\S[a-zA-Z\s\d\u4e00-\u9fa5]+\S$/");
  // return a.test(str)
  
  // var a=(/^\S[a-zA-Z\s\d\u4e00-\u9fa5]+\S$/.test(str)
  // return a
}


/* 大小写字母*/
export function validatAlphabets(str) {
  const reg = /^[A-Za-z]+$/;
  return reg.test(str);
}




