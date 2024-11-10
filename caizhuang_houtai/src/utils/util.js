const isPhone = function(rule, value, callback) {
    // if (!value) {
    //     return callback(new Error('输入不可以为空'));
    // }
    if (value) {
        var pattern = /^1[23456789]\d{9}$/;
        if(pattern.test(value)){
            return callback()
        }
        return callback(new Error('输入的手机号错误'))
    }else{
        return callback()
    }
}

const getLocalTime= function getLocalTime(nS) {     
    return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');     
 }
module.exports={
    isPhone:isPhone,
    getLocalTime:getLocalTime
}