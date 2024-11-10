<template>
  <div>
    <order-title :current="current"></order-title>
    <div class="pay-container">
      <div class="paymethod">
        <p style="font-size:15px;font-weight:700;margin-left:20px">请选择支付方式：</p>
        <RadioGroup v-model="payMethod" vertical @on-change="change" class="ra">
          <Radio v-for='(item,index) in paylist' :label='item' :key='index' :class="[payMethod==item?'selected':'radio']" >
           <span>{{item}}</span>
           <span v-if="payMethod==item" class="price">￥{{totalPrice}}元</span>
          </Radio>
        </RadioGroup>
      </div>
      <Alert show-icon style="margin-left:20px">
        扫码支付
        <Icon type="md-expand" />
        <template slot="desc">请扫描右边二维码进行支付</template>
      </Alert>
      <div class="pay-box">
        <div class="pay-demo">
          <img src="static/img/pay/pay-demo.png">
        </div>
        <div class="pay-qr-scan">
          <img :src="payImg">
          <div class="pay-tips" @click="payOrder">
            <router-link to="/payDone"><p>点击我, 完成支付</p></router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import OrderTitle from '@/components/common/OrderTitle';
import { sendServer } from '../utils/common';
export default {
  name: 'Pay',
  components:{
    OrderTitle,
  },
  data(){
    return{
      current:0,
      payImg:require('../../static/img/pay.png'),
      payMethod:'花呗',
      paylist:['花呗','账户余额','银行卡'],
      totalPrice:this.$route.query.totalPrice,
      orderId:this.$route.query.orderId,

    }
    
  },
  methods: {
   change(data){//返回选中的id
      this.paylist.forEach(item=>{
        if(item==data){
          this.payMethod=item
        }
      })
      console.log(this.payMethod)
    },
    payOrder(){
      let params={},send={}
      send.orderId=this.orderId;
      send.paymethod=this.payMethod;
      params.send=send;
      params.url="order/payOver"
      sendServer(params,(commRes)=>{
        if(commRes.code==100){
          console.log(commRes)
          var time=new Date().getTime()
          let order=commRes.extend.order
          let address=order.address.address+order.address.detail
          let params={},send={};
          send.orderId=order.orderId;
          send.userId=order.userId;
          send.totalprice=order.totalprice;
          send.address=address//收货地址
          send.postcode=order.address.postcode;
          send.recePhone=order.address.recePhone;
          send.receName=order.address.receName;
          send.deliverymethod=order.deliverymethod;
          send.memo=order.memo;
          send.createTime=time
          send.paymethod=order.paymethod;
          send.status=1;//1表示已付款
          send.item=JSON.stringify(order.orderItemList);
          // send.item=encodeURIComponent(JSON.stringify(order.orderItemList))
          params.send=send;
          params.url="bgorder/toBgOrder"
          sendServer(params,(commRes)=>{
            if(commRes.code==100){
              this.$Message.success("订单付款成功！！！")
              //付款成功之后 根据商品ID来改变商品的销量
              //创建一个触发器来完成

            }
          })
          
        }
      })

    }
  }

};
</script>

<style scoped>
.price{
  color: #f40;
  float: right;
  font-size: 14px;
}
.paymethod{
  margin-bottom: 20px;
}
.selected{
  border: 2px solid #92c9e4;
  /* background: #92c9e4; */
  height: 40px;
  line-height: 40px;
  padding-left: 20px;
  width: 1020px;
  
}
.radio{
  height: 40px;
  line-height: 40px;
  padding-left: 20px;

}
.ra{
  margin-left: 50px;
}
.pay-container {
  margin: 15px auto;
  width: 80%;
  min-width: 1000px;
}
.pay-box {
  height: 500px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.pay-demo {
  width: 50%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.pay-demo img{
  height: 80%;
}
.pay-qr-scan {
  width: 50%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.pay-tips {
  width: 50%;
  text-align: center;
  font-size: 14px;
  line-height: 30px;
}
.pay-tips a {
  color: #999999;
}
</style>
