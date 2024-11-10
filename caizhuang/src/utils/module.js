import {sendServer,sendServerGet} from '@/utils/common'
import store from '@/vuex/store';

export const getGoodsList=()=>{
    return new Promise((resolve,reject)=>{
        let params={},send={}
        params.url='goods/list';
        sendServer(params,(commRes)=>{
            if(commRes.code==100){
                resolve(commRes)
            }
            reject(false); // 失败回调
            return false;
        })
    })

}

export const getAllCollection=(name)=>{
    return new Promise((resolve,reject)=>{
        let params={},send={}
        let userInfo=JSON.parse(localStorage.getItem('userdata'));
        send.userId=userInfo.id;
        send.searchname=name
        params.send=send;
        params.url='like/likeSearch';
        sendServerGet(params,(commRes)=>{
            console.log(commRes)
            if(commRes.code==100){
                resolve(commRes)
            }
            reject(false); // 失败回调
            return false;
        })

    })
}

export const getUserCart=()=>{
    return new Promise((resolve,reject)=>{
        let params={},send={}
        let userInfo=JSON.parse(localStorage.getItem('userdata'));
        send.userId=userInfo.id;
        params.send=send;
        params.url='cart/showCart';
        sendServer(params,(commRes)=>{
            // console.log(commRes)
            if(commRes.code==100){
                resolve(commRes)
            }
            reject(false); // 失败回调
            return false;
        })

    })
}
export const getAddList=()=>{
    return new Promise((resolve,reject)=>{
        let params={},send={}
        let userInfo=JSON.parse(localStorage.getItem('userdata'));
        send.userId=userInfo.id;
        params.send=send;
        params.url='address/showAddress';
        sendServer(params,(commRes)=>{
            // console.log(commRes)
            if(commRes.code==100){
                resolve(commRes)
            }
            reject(false); // 失败回调
            return false;
        })
    })
}


export const updateCart=(id,quantity)=>{
    return new Promise((resolve,reject)=>{
        let params={},send={}
        send.id=id;
        send.quantity=quantity;
        params.send=send;
        params.url='cart/updateCart';
        sendServer(params,(commRes)=>{
            if(commRes.code==100){
                resolve(commRes)
            }
            reject(false); // 失败回调
            return false;
        })

    })

}

//获取商品分类
export const getGoodsType=()=>{
    return new Promise((resolve,reject)=>{
        let params={};
        params.url="goods/getGoodsType";
        sendServer(params,(commRes)=>{
            if(commRes.code==100){
                resolve(commRes)
            }
            reject(false); // 失败回调
            return false;
        })

    })
}


//更改订单的状态
export const updateStatus=(orderId,status)=>{
    return new Promise((resolve,reject)=>{
        let params={},send={}
        send.orderId=orderId;
        send.status=status;
        params.send=send;
        params.url="order/updateStatus";
        sendServer(params,(commRes)=>{
            if(commRes.code==100){
                resolve(commRes)
            }
            reject(false); // 失败回调
            return false;
        })
    })
}
//更改订单明细的状态 status  已评价 ，追评 等等
export const updateItemStatus=(itemId,status)=>{
    return new Promise((resolve,reject)=>{
        let params={},send={}
        send.itemId=itemId;
        send.status=status;
        params.send=send;
        params.url="orderItem/updateItemStatus";
        sendServer(params,(commRes)=>{
            if(commRes.code==100){
                resolve(commRes)
            }
            reject(false); // 失败回调
            return false;
        })
    })
}