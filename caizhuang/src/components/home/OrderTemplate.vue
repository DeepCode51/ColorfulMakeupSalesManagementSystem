<template>
  <div>
      <!-- 表头 -->
      <div class="title">
          <table class="table-head bought-table-mod">
              <tbody>
                  <tr>
                      <th style="width:38%">宝贝</th>
                      <th style="width:10%">单价</th>
                      <th style="width:5%">数量</th>
                      <th style="width:12%">商品操作</th>
                      <th style="width:12%">实付款</th>
                      <th style="width:12%">交易状态</th>
                      <th style="width:12%">交易操作</th>

                  </tr>
              </tbody>
          </table>
      </div>
      <div v-if="a==5 && orderList.length!=0" style="margin-top:10px">
        <label class="bought-wrapper-mod__checkbox-label___3Va60">
            <span class="bought-wrapper-mod__checkbox___11anQ"><input type="checkBox"/></span>
            <span>全选</span>
            <span><Button size="small">批量永久删除</Button></span>
        </label>
      </div>
      <!-- 内容 -->
      <div style="margin-top:60px" v-if="orderList.length==0">
          <p style="text-align:center">没有符合条件的宝贝，请尝试其他搜索条件。</p>
      </div>
      <div class="center" v-for="(item,index) in orderList" :key="index">
          <table class="table-head bought-table-mod">
              <colgroup>
                <col class="bought-table-mod0"></col>
                <col class="bought-table-mod1"></col>
                <col class="bought-table-mod2"></col>
                <col class="bought-table-mod3"></col>
                <col class="bought-table-mod3"></col>
                <col class="bought-table-mod3"></col>
                <col class="bought-table-mod3"></col>
              </colgroup>
              <!-- 表头 -->
              <tbody class="bought-wrapper-mod">
                  <tr>
                      <td class="td-title">
                          <label class="bought-wrapper-mod__checkbox-label___3Va60">
                              <span class="bought-wrapper-mod__checkbox___11anQ"><input type="checkBox"/></span>
                              <span>{{item.createTime}}</span>
                          </label>
                          <span>订单号：{{item.orderId}}</span>
                      </td>
                      <td colspan="2" class="bought-wrapper-mod__seller-container___3dAK3 ">
                          <span>缤色彩妆店铺</span>
                      </td>
                      <td>
                          <span style="font-size:13px"><Icon type="ios-happy" /></span>
                          <span style=" background-color: #f40;border-radius: 2px;color:#FFF">和我联系</span>
                      </td>
                      <td colspan="3">
                          <span>订单总价：<span style="margin-top:20px;font-weight:700;color:#f40;font-size:15px">￥{{item.totalprice}}元</span></span>
                          <button style="font-size:17px;float: right;margin-right: 45px;" @click="deleteByPrimaryKey(item.orderId)"><Icon type="md-trash" /></button>
                      </td>

                  </tr>
              </tbody>
              <!-- 内容 -->
              <tbody>
                  <tr class="td-center" v-for="(item0,index) in item.orderItemList">
                      <td class="td-border">
                          <div class="goods-info">
                              <div style="float:left">
                                  <img :src="temfielPath+item0.goods.picture" class="img-80"/>
                              </div>
                              <div style="margin-left:90px">
                                  <p>
                                      <a>{{item0.goods.goodsname}}</a>
                                  </p>
                                  <p>
                                      <span class="span">颜色分类：默认</span>
                                      <span class="span">尺码：{{item0.size}}</span>
                                  </p>
                                  <p>
                                      <img :src="orderImgUrl"/>
                                  </p>
                              </div>
                          </div>
                      </td>
                      <!-- 价格 -->
                      <td class="td-border">
                          <p style="margin-top:20px;">￥{{item0.goodsBuyPrice}}元</p>
                      </td>
                      <!-- 数量 -->
                      <td class="td-border">
                          <p style="margin-top:20px;">{{item0.quantity}}</p>
                      </td>
                      <!-- 商品操作 -->
                      <td class="td-border">
                          <p style="margin-top:20px;"><router-link :to="{path:'/orderDetail',query:{orderId:item.orderId}}" target="_blank">申请售后</router-link></p>
<!--  这个暂时用不到       <p>投诉卖家</p>-->
                      </td>
                      <!-- 实付款 -->
                      <td>
                         <p style="margin-top:20px;font-weight:700;font-size:13px">￥{{item0.goodsBuyPrice*item0.quantity}}元</p>
                         <p style="color: #9e9e9e;">(含运费：￥0.00)</p>
                      </td>
                      <!-- 交易状态 -->
                      <td class="td-action">
                          <p style="margin-top:20px;" v-if="item.status==8">退货成功</p>
                          <p style="margin-top:20px;" v-if="item.status==7">等待商家退货</p>
                          <p style="margin-top:20px;" v-if="item.status==5">交易完成</p>
                          <p style="margin-top:20px;" v-if="item.status==4">交易关闭</p>
                          <p style="margin-top:20px;" v-if="item.status==3">交易成功</p>
                          <p style="margin-top:20px;color:#f40" v-if="item.status==0">待付款</p>
                          <p style="margin-top:20px;" v-if="item.status==1">待发货</p>
                          <p style="margin-top:20px;" v-if="item.status==2">待收货</p>
                          <p><router-link :to="{path:'/orderDetail',query:{orderId:item.orderId}}" target="_blank">订单详情</router-link></p>
                          <p v-if="item.status==2 || item.status==3 || item.status==5"><router-link :to="{path:'/orderDetail',query:{orderId:item.orderId}}" target="_blank">查看物流</router-link></p>
                      </td>
                      <!-- 交易操作 -->
                      <td>
                          <Button size="small" style="margin-top:20px"v-if="item.status==3 &&item0.status==null" @click="toReview(item0)">评价</Button>
                          <Button size="small" style="margin-top:20px"v-if="(item.status==3  || item.status==5) &&item0.status==1" @click="lookReviewById(item0)">追评</Button>
                      </td>

                  </tr>

              </tbody>
          </table>
      </div>
      <div v-if="a==5 && orderList.length!=0" style="margin-top:10px">
        <label class="bought-wrapper-mod__checkbox-label___3Va60">
            <span class="bought-wrapper-mod__checkbox___11anQ"><input type="checkBox"/></span>
            <span>全选</span>
            <span><Button size="small">批量永久删除</Button></span>
        </label>
      </div>
  </div>
</template>

<script>
import { sendServer } from '../../utils/common';
export default {
    props:{
        a:{
            type:String,
            default: "0",
        },
    },
    data(){
        return{
            temfielPath:'http://localhost:8089/img/',
            orderList:[],//
            userInfo:JSON.parse(localStorage.getItem('userdata')),
            imgUrl:require("../../../static/img/index/susu.png"),
            orderImgUrl:require("../../../static/img/index/eat/item.png")

        }
    },
    mounted () {
        if(this.a=="0"){//查看所有订单
            this.showOrders(status=null);
        }else if(this.a=="1"){//查看待付款 status=0
            this.showOrders(0);
        }else if(this.a=="2"){//查看代发货 status=1
            this.showOrders(1);
        }else if(this.a=="3"){//查看代收货 status=2 待收货也就是已发货
            this.showOrders(2);
        }else if(this.a=="4"){
            this.showOrders(3);//查看待评价 ，即status=3 待评价也就是确认收货了
        }else if(this.a=="6"){
            this.showOrders(4);//订单关闭状态
        }

        //status==4 的时候 订单关闭
  },
  methods:{
  deleteByPrimaryKey(orderId){
                   let params={},send={};
                   send.orderId=orderId;
                   params.send=send;
                   params.url="order/deleteOrder?orderId="+orderId;
                   sendServer(params,(commRes)=>{

                     if(commRes.code==100){
                       this.$Message.success("删除成功")
                     }
                   })
                 },
    showOrders(status){
          let params={},send={};
          send.userId=this.userInfo.id;
          send.status=status;
        //   console.log(send)
          params.send=send;
          params.url="order/showOrder";
          sendServer(params,(commRes)=>{
               console.log(commRes)
              if(commRes.code==100){
                  this.orderList=commRes.extend.orderList
                  //console.log(this.orderList.length)

              }
          })
    },
    toReview(item){
        //console.log(item)
        this.$router.push(
                {path:'/review',query:{item:JSON.stringify(item)}}
        )
    },
    lookReviewById(item){
        this.$router.push(
                {
                    path:'/review',
                    query:{item:JSON.stringify(item)}
                }
        )
    }
  }

}
</script>

<style scoped>
.td-action a{
    color: #3c3c3c;
}
.td-action a:hover{
    color: #f40;
}
.center:hover{
    border: 1px solid #ccc;
    box-shadow: 0px 0px 15px #ccc;
}
.td-border{
    border-left: none!important;
}
.span{
    color: #9e9e9e;
    margin-right: 8px;
    line-height: 16px;
}
.goods-info a {
    color: #3c3c3c;
    text-decoration: none;
    word-break: break-all;
}
.goods-info a:hover{
    color: #f40;
}
.goods-info p {
    margin-top: 8px;
    margin-bottom: 8px;
    line-height: 1;
    text-align: left;
}
.img-80{
    width:80px;
    height: 80px;
}
.goods-info{
    margin-left: 20px;
}
.td-center td{
    border-right: none!important;
    text-align: center;
    border-color: #ececec;
    vertical-align: top;
    padding-top: 10px;
    padding-bottom: 10px;
    /* border-top: 1px solid #ececec; */
    border-bottom: 1px solid #ececec;
    border-left: 1px solid #ececec;
}
.center{
    margin: 20px 0;
    border: 1px solid #ececec;
}

.bought-wrapper-mod__seller-container___3dAK3 {
    padding: 10px 20px;
    text-align: center;
}
.bought-wrapper-mod__checkbox___11anQ {
    margin-right: 3px;
}
.bought-wrapper-mod__checkbox-label___3Va60 {
    margin-left: 20px;
}
.td-title{
    text-align: left;
    border-color: #ececec;
    border: none;
    vertical-align: top;
    padding-top: 10px;
    padding-bottom: 10px;
}
.bought-wrapper-mod{
    background: #f1f1f1;
}
.bought-table-mod3{
    width: 12%;
}
.bought-table-mod2{
    width: 5%;
}
.bought-table-mod1{
    width: 10%;
}
.bought-table-mod0{
    width: 38%;
}
.title{
    padding: 10px 0;
    background-color: #f5f5f5;
    border: 1px solid #e8e8e8;

}
.table-head{
    text-align: center;
}
.bought-table-mod {
    width: 100%;
    border-collapse: collapse;
    border-spacing: 0;
    color: #3c3c3c;
    table-layout: fixed;
}
.table-head th{
    padding-top: 5px;
    padding-bottom: 5px;
}
.bought-table-mod th{
    font-weight: 400;
    text-align: center;
}
</style>
