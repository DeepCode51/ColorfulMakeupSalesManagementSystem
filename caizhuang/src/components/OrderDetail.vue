<template>
  <div>
       <!-- 头部搜索框 -->
    <div class="top0">
      <div class="top1">
        <img :src="searchImgurl" />
      </div>
      <div class="top2">
        <Input  v-model="searchData" size="large" class="sousuo" placeholder="输入你想查找的商品" style="outline:none"  >
          <Button slot="append" icon="ios-search" @click="goSearch" class="searchBtn"></Button>
        </Input >
      </div>
    </div>
    <div class="center-box">
        <div class="title">
            <p>您的位置: <router-link to="/">首页</router-link><Icon type="ios-arrow-forward" />
            <router-link to="/home/showOrder">我的缤色彩妆</router-link><Icon type="ios-arrow-forward" />
            <router-link to="/orderDetail">已买到的宝贝</router-link></p>
        </div>
        <!-- 订单状态 -->
        <div class="order-status" v-if="orderInfo.status==0">
            <div class="status-text">
                <p style="font-weight:700;font-size:15px" >当前订单状态：等待付款</p>
                <p style="margin-top:20px;color:#f40">请尽快付款~~</p>
                <div style="margin-top:20px;">
                    <!-- <div class="status-box;float:left;margin-left:30px">去付款</div>
                    <div class="status-box;float:left;">取消订单</div> -->
                    <Button size="small" @click="gotoPay">去付款</Button>
                    <Button size="small" @click="cancelOrder">取消订单</Button>
                </div>
            </div>
        </div>
      <!-- 订单退货状态 -->
      <div class="order-status" v-if="orderInfo.status==7">
        <div class="status-text">
          <p style="font-weight:700;font-size:15px" >正在申请退货</p>
          <p style="margin-top:20px;color:#f40">等到商家退货</p>
        </div>

      </div>
      <div class="order-status" v-if="orderInfo.status==8">
        <div class="status-text">
          <p style="font-weight:700;font-size:15px" >退货成功</p>

          <p style="margin-top:20px;color:#f40">金额已返回到原账户</p>

        </div>
      </div>

        <div class="order-status" v-if="orderInfo.status==4">
            <div class="status-text">
                <p style="font-weight:700;font-size:15px" >当前订单状态：交易关闭</p>
                <div style="margin-top:20px;">
                   关闭原因：顾客自行关闭
                </div>
            </div>
        </div>
        <div class="order-status" v-if="orderInfo.status==1">
            <div class="status-text">
                <p style="font-weight:700;font-size:15px" >当前订单状态：等待商家发货</p>
                <div style="margin-top:20px;">
                   请耐心等待，商家承诺<span style="color:#f40">48小时</span>之内发货！！
                </div>
            </div>
        </div>
        <div class="order-status" v-if="orderInfo.status==2">
            <div class="status-text">
                <p style="font-weight:700;font-size:15px" >当前订单状态：商家已发货，等待收货</p>
                <div style="margin-top:20px;">
                   商家已进行发货，预计<span style="color:#f40">1-3天</span>的物流，请耐心等待！！
                </div>
                <div style="margin-top:20px;">
                    <Button size="small" @click="confirmOrder">确认收货</Button>
                </div>
            </div>
        </div>
        <div class="order-status" v-if="orderInfo.status==3">
            <div class="status-text">
                <p style="font-weight:700;font-size:15px" >当前订单状态：交易已成功</p>
                <div style="margin-top:20px;">
                   交易成功，请给商家<span style="color:#f40">评价</span>
                   <p>如果没有收到货，或收到货后出现问题，您可以<span style="color:#f40">联系卖家</span>协商解决</p>
                   <p>如果卖家没有履行应尽的承诺，您可以联系<span style="color:#f40">缤色彩妆官方客服</span></p>

                  <div style="margin-top:20px;">
                    <Button size="big" @click="returnOrder">申请退货</Button>
                  </div>
                </div>
            </div>
        </div>
        <!-- 物流信息 -->
        <div v-if="orderInfo.status==2 || orderInfo.status==3" class="wuliu">
            <p style="font-weight:600;font-size:14px">物流信息：</p>
            <hr/>
            <div style="margin-top:20px;color:#404040;margin-bottom:30px">
                 <Alert show-icon>若快递公司未经您允许将包裹放置在智能快件箱（即自提柜）中，您有权拒收。</Alert>
                <p>发货方式：   快递</p>
                <p>物流公司：	中通快递</p>
                <p>运单号码：	75442160290397</p>
                <p>物流跟踪：  {{orderInfo.createTime}}商品已经下单</p>
                <p style="padding-left:60px">{{orderInfo.createTime}}包裹正在等待揽收</p>
                <p style="padding-left:60px">{{orderInfo.createTime}}中通快递 已揽件</p>
                <p style="padding-left:60px">{{orderInfo.createTime}}快件已发出,正在发往【{{orderInfo.address.address}}】</p>
                <p style="padding-left:60px">{{orderInfo.createTime}}快件已从【{{orderInfo.address.address}}】发出，正在发往【{{orderInfo.address.detail}}】</p>
                <p style="padding-left:60px">{{orderInfo.createTime}}快件已到达【 {{orderInfo.address.address}},{{orderInfo.address.detail}}】</p>
                <p style="padding-left:60px">{{orderInfo.createTime}}【{{orderInfo.address.detail}}】派件员：左文祥 电话：15993391164 当前正在为您派件</p>
                <p style="padding-left:60px">{{orderInfo.createTime}}您的快件已暂存至【{{orderInfo.address.detail}}】菜鸟驿站，请凭取货码及时领取。如有疑问请联系15295770476</p>
                <p style="padding-left:60px;color:#f40" v-if="orderInfo.status==3">2021-03-07 15:41:21您已在【{{orderInfo.address.detail}}】菜鸟驿站完成取件，感谢使用菜鸟驿站，期待再次为您服务。</p>

            </div>
        </div>

      <div class="order-status" v-if="orderInfo.status==5">
        <div class="status-text">
          <p style="font-weight:700;font-size:15px" >当前订单状态：您已评价</p>
          <br>
          <p>如果卖家没有履行应尽的承诺，您可以联系<span style="color:#f40">缤色彩妆官方客服</span></p>
          <div style="margin-top:20px;">
            <Button size="big" @click="returnOrder">申请退货</Button>
          </div>
          <div style="margin-top:20px;">
          </div>
        </div>
      </div>

        <!-- 订单信息 -->
        <div class="order-info">
            <ul class="tabs">
                <li>订单信息</li>
            </ul>
            <div class="info-box">
                <div style="display:block;margin-top:20px">
                    <!-- 收货地址 -->
                    <div v-if="orderInfo.address">
                        <span style="font-weight:700;font-size:14px">收货地址：</span>
                        <span>{{orderInfo.address.receName}} , {{orderInfo.address.recePhone}} , {{orderInfo.address.address}} {{orderInfo.address.detail}},{{orderInfo.address.postcode}}</span>
                    </div>
                    <!-- <hr/> -->
                    <!-- 订单备注 -->
                    <div class="memo" v-if="orderInfo.memo!=null && orderInfo.memo!=''">
                        <span style="font-weight:700;font-size:14px">订单备注：</span>
                        <span>{{orderInfo.memo}}</span>
                    </div>
                    <!-- <hr/> -->
                    <!-- 运送方式 -->
                    <div class="memo">
                        <span style="font-weight:700;font-size:14px">运送方式：</span>
                        <span>{{orderInfo.deliverymethod}}</span>
                    </div>
                    <hr/>


                    <!-- 订单信息 -->
                    <div style="margin-top:20px">
                        <p style="font-weight:700;font-size:14px">订单信息：</p>
                        <div class="info-title">
                            <span>订单编号：{{orderId}}</span>
                            <span>创建时间：{{orderInfo.createTime}}</span>
                        </div>
                        <!-- 商品信息 -->
                        <div class="goods-info">
                            <table class="table">
                                <colgroup>
                                    <col width="260px"></col>
                                    <col width="110px"></col>
                                    <col width="90px"></col>
                                    <col width="90px"></col>
                                    <col width="75px"></col>
                                    <col width="50px"></col>

                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th>宝贝</th>
                                        <th>宝贝属性</th>
                                        <th>状态</th>
                                        <th>服务</th>
                                        <th>单价</th>
                                        <th>数量</th>

                                    </tr>
                                    <!-- 商品信息 -->
                                    <tr v-for="(item,index) in itemList">
                                        <td>
                                            <div style="float: left;margin-right: 5px;">
                                                <img :src="temfielPath+item.goods.picture" class="img-50"/>
                                            </div>
                                            <div style="margin-left: 55px;line-height: 18px;text-align: left;color: gray;">
                                                <p>{{item.goods.description}}</p>
                                                <p><img :src="itemUrl" /></p>
                                                <!-- <p>发货时间：付款后15天内</p> -->
                                            </div>
                                        </td>
                                        <!-- 宝贝属性 -->
                                        <td>
                                            <div>
                                                <p style="color: gray;">颜色分类：默认</p>
                                                <p style="color: gray;">尺码：{{item.size}}</p>
                                            </div>
                                        </td>
                                        <!-- 交易状态 -->
                                        <td>
                                            <div>
                                                <!-- <p style="margin-top:20px;" v-if="item.status==4">交易关闭</p>
                                                <p style="margin-top:20px;" v-if="item.status==3">交易成功</p>
                                                <p style="margin-top:20px;" v-if="item.status==0">待付款</p>
                                                <p style="margin-top:20px;" v-if="item.status==1">待发货</p>
                                                <p style="margin-top:20px;" v-if="item.status==2">待收货</p> -->
                                                <span v-if="orderInfo.status==0">待付款</span>
                                                <span v-if="orderInfo.status==1">待发货</span>
                                                <span v-if="orderInfo.status==2">待收货</span>
                                                <span v-if="orderInfo.status==3">交易成功</span>
                                                <span v-if="orderInfo.status==4">交易关闭</span>
                                            </div>
                                        </td>
                                        <td>
                                            <!-- <div v-if="orderInfo.status==2">
                                                <Button size="small" @click="confirmOrder">确认收货</Button>
                                            </div> -->
                                            <div v-if="orderInfo.status==3 &&item.status==null">
                                                <Button size="small" @click="toReview(item)">评价</Button>
                                            </div>

                                            <div v-if="orderInfo.status==3 && item.status==1">
                                                <Button size="small" @click="toReview(item)">追评</Button>
                                            </div>

                                        </td>
                                        <td>
                                            <span>￥{{item.goodsBuyPrice}}元</span>
                                        </td>
                                        <td>
                                            <span>{{item.quantity}}</span>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>

                        </div>

                    </div>
                    <!-- 付款信息 -->
                    <div style="padding: 0 14px 0 8px;margin-top:20px">
                        <div class="pay-info">
                            <div>
                                <p>商品总价：￥{{orderInfo.totalprice}}元</p>
                                <p>运费：￥0.00</p>
                                <p>实付款：<span style="margin-top:20px;font-weight:700;color:#f40;font-size:15px">￥{{orderInfo.totalprice}}元</span></p>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

  </div>
</template>

<script>
import api from '../utils/api';
import { sendServer } from '../utils/common';
export default {
    data(){
        return{
            temfielPath:'http://localhost:8089/img/',
            orderInfo:{},
            itemList:[],
            orderId:this.$route.query.orderId,
            searchData:'',
            searchImgurl:require('../../static/img/index/logo.png'),
            imgurl:require('../../static/img/index/susu.png'),
            itemUrl:require('../../static/img/index/eat/item.png'),
        }
    },
    created(){
        this.getOrderDetail();
    },
    methods:{
        //退货
      returnOrder(){
        this.$confirm('是否申请退货', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '暂时不',
          type: 'warning'
        }).then(() => {
          this.return();
        }).catch(() => {

        });
      },
      return(){
        let orderId=this.orderId;
        let status="7";
        api.updateStatus(orderId,status).then((res)=>{
          if(res.code==100){
            let params={},send={};
            send.orderId=orderId;
            send.status=7//后台订单状态变为7表示已退货
            params.send=send;
            params.url="bgorder/updateStatus";
            sendServer(params,(commRes)=>{
              if(commRes.code==100){
                this.$Message.success("退货成功")
                this.orderInfo.status=7
              }
            })

          }
        })

      },
        //去评价
        toReview(item){
            console.log(item)
            this.$router.push(
              {path:'/review',query:{item:JSON.stringify(item)}}
            )
        },
        //付款
        gotoPay(){
            this.$router.push(
              {path:'/pay',query: {totalPrice:this.orderInfo.totalprice,orderId:this.orderId}}
            )
        },
        //取消订单
        cancelOrder(){
            this.$confirm('确定取消该订单吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '暂时不',
                type: 'warning'
            }).then(() => {
                this.cancel();
            }).catch(() => {

            });
        },
        cancel(){
          let orderId=this.orderId;
          let status="4";
          api.updateStatus(orderId,status).then((res)=>{
            if(res.code==100){
              this.$Message.success("订单已经被取消")
              this.$router.push('/home/showOrder')
            }
          })
        },
        //确认收货
        confirmOrder(){
            this.$confirm('是否确认收货', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '暂时不',
                type: 'warning'
            }).then(() => {
                this.confirm();
            }).catch(() => {

            });
        },
        confirm(){
            let orderId=this.orderId;
            let status="3";
            api.updateStatus(orderId,status).then((res)=>{
                if(res.code==100){
                    let params={},send={};
                    send.orderId=orderId;
                    send.status=3//后台订单状态变为3表示已收货
                    params.send=send;
                    params.url="bgorder/updateStatus";
                    sendServer(params,(commRes)=>{
                        if(commRes.code==100){
                            this.$Message.success("收货成功")
                            this.orderInfo.status=3
                        }
                    })

                }
            })

        },
        goSearch(){

        },
        getOrderDetail(){
            let params={},send={};
            send.orderId=this.orderId,
            params.send=send;
            params.url='order/showOrderDetail'
            sendServer(params,(commRes)=>{
                if(commRes.code==100){
                    this.itemList=commRes.extend.order.orderItemList
                    this.orderInfo=commRes.extend.order
                }
            })
        }
    }

}
</script>

<style scoped>
.wuliu{
    margin-top: 30px;
}
/* .status-box{
    border: 1px solid #f40;
    width: 50px;
    height: 30px;
    line-height: 30px;
    text-align: center;
} */
.memo{
    margin-top: 20px;
}
.pay-info{
    float: right;
    padding-top: 15px;
    line-height: 23px;
    text-align: right;
    margin-right: 5px;
}
.img-50{
    width:50px;
    height: 50px;
}
.table td {
    padding: 8px 5px;
    vertical-align: middle;
    border: 1px solid #ddd;
    line-height: 150%;
}
.table th {
    background-color: #e8f2ff;
    color: #404040;
    padding: 8px 10px;
    font-weight: 400;
    word-wrap: break-word;
    text-align: center;
}
.table {
    border-spacing: 0;
    border-collapse: collapse;
    border: 1px solid #ddd;
    text-align: center;
    line-height: 130%;
    width: 100%;
}
.goods-info{
    padding-top: 20px;
}
.info-title span{
    float: left;
    width: 400px;
}
.info-title{
    border-collapse: collapse;
    border-spacing: 0;
    line-height: 30px;
    padding-left: 80px;
}
.info-box{
    position: relative;
    top: 26px;
    overflow: hidden;
    margin-bottom: 40px;
    padding: 10px 20px;
    border: 1px solid #AEC7E5;
}
.tabs li {
    float: left;
    width: 130px;
    height: 27px;
    margin-right: 3px;
    padding-top: 8px;
    line-height: 21px;
    text-align: center;
    background: url('../../static/img/index/eat/detail.gif') 0 6px no-repeat;
    cursor: pointer;
    background-position: 0 -40px;
}

.tabs{
    position: absolute;
    margin-left: 20px;
    z-index: 50;
    list-style: none;
}
.order-info{
    margin: 20px 0;
    width: 100%;
}
.order-status{
    margin-top: 20px;
    width: 100%;
    padding: 10px 20px 20px 60px;
    background: #FFF7EB;
    border: 1px solid #F58B0F;

}
.title a{
    color: #3c3c3c;
}
.title a:hover{
    color: #f40;
}
.center-box{
    width: 1050px;
    margin: 5px auto;
}
.top0{
  /* width: 80%; */
  width: 1050px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin: 5px auto;
}

.top1 img{
  width: 210px;
  height: 100px;
}
.top2{
  width: 50%;
}
.sousuo {
  margin: 5px 0px;
  border: 3px solid #ff5000;
  border-top-left-radius: 20px;
  border-bottom-left-radius: 20px;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
  overflow: hidden;
  height: 36px;
}

.sousuo >>> .ivu-input:focus{
    outline:none;
    border:none;
    box-shadow: 0 0 0 2px transparent;
    border-bottom: 1.3px solid gainsboro;
}

</style>
