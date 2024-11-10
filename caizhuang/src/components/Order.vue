<template>
  <div>
    <!-- 订单头部 -->
     <div class="top0">
      <div class="top1">
        <img :src="searchImgurl" />
      </div>
      <div class="top2">
        <Steps :current="current">

          <Step title="进行中" content="确认订单信息" v-if="current<0"></Step>
          <Step title="已完成" content="确认订单信息" v-if="current==0"></Step>

          <Step title="进行中" content="付款到支付宝"v-if="current<1"></Step>
          <Step title="已完成" content="付款到支付宝"v-if="current==1"></Step>

          <Step title="进行中" content="支付完成" v-if="current<2"></Step>
          <Step title="已完成" content="支付完成"v-if="current==2"></Step>

          <Step title="进行中" content="等待发货"></Step>
          <!-- <Step title="已完成" content="等待发货"></Step> -->
        </Steps>
      </div>
     </div>
    <!-- 确认收货地址 -->
    <div class="addCon">
      <div class="add-title">
        <p>确认收货地址</p>
        <router-link target="_blank" to="/home/mySetting/myAddress" ><p style="color:#b13009">管理收货地址</p></router-link>
      </div>
      <hr/>
      <!-- 地址列表 -->
      <div class="addlist">
        <div class="left">
          <Icon type="md-pin" />寄送至
        </div>
        <div class="right">
          <RadioGroup v-model="addressId" vertical @on-change="change">
            <Radio v-for='(item,index) in addlist' :label='item.id' :key='index' :class="[addressId==item.id?'selected':'radio']" >
              <!-- :class="[addStatus==0?'':'form-cart']"    class="radio"-->
              <span>{{item.address}}</span>
              <span>{{item.detail}}</span>
              <span>{{item.detail}}</span>
              <span>(</span><span>{{item.receName}}</span><span>收)</span>
              <span>{{item.recePhone}}</span>
              <span v-if="item.status==1" class="default-text">默认地址</span>


            </Radio>
          </RadioGroup>


        </div>
      </div>
      <!-- 使用新地址 -->
      <div class="bottom-add">
          <Button @click="openAdd">使用新地址</Button>
      </div>

    </div>
    <!-- 确认订单信息 -->
    <div class="addCon">
      <!-- 头部 -->
      <div class="add-title">
        <p>确认订单信息</p>
      </div>
      <div class="item-headers">
        <div class="item-headers-content item-headers-0">店铺宝贝</div>
        <div class="item-headers-content item-headers-1">商品属性</div>
        <div class="item-headers-content item-headers-2">单价</div>
        <div class="item-headers-content item-headers-3">数量</div>
        <div class="item-headers-content item-headers-4">小计</div>
      </div>
      <!-- 商品信息 -->
      <div class="goods-list">
        <div class="order-orderInfo">
          <img :src="searchImgurl" class="img-icon"/>
          <span>店铺：缤色彩妆</span>
        </div>

        <div class="goods-box">

          <div class="goods-info" v-for="(item, index) in listItem" :key="index">
            <div class="order-itemInfo">
              <div class="info-detail info-cell">
                <img :src="temfielPath+item.goods.picture" alt="" class="info-img"/>
                <div class="info-msg">
                  <a class="order-link info-title">
                    {{item.goods.description}}
                  </a>
                  <div>
                    <a><img :src="img1"/></a>
                    <a><img :src="img2"/></a>
                    <a><img :src="img3"/></a>
                  </div>
                  <span class="delivery-time">发货时间：付款后15天内</span>
                </div>
              </div>
              <div class="info-sku">
                <p>
                  <span>颜色分类：</span>
                  <span>默认</span>
                </p>
                <p>
                  <span>商品尺码：</span>
                  <span>{{item.size}}</span>
                </p>
              </div>
              <div class="info-price">￥{{item.goods.sellPrice}}元</div>
            </div>
            <div class="order-quantity">
              <p>{{item.quantity}}件</p>
            </div>
            <div class="item-row__price ">
              <p>￥{{item.goods.sellPrice*item.quantity}}元</p>
            </div>

          </div>

          <!-- 买家 -->
          <div class="order-memo">
            <!-- 留言以及配送方式 -->
            <div style="padding: 0px;border-top: 1px solid rgb(255, 255, 255);display:flex;flex-direction:row;border-bottom: 1px solid rgb(255, 255, 255);">
              <div class="textarea">
                <p class="textarea__title">给卖家留言：</p>
                <div class="textarea__wrapper">
                  <Input v-model="memo" maxlength="100" show-word-limit type="textarea" placeholder="选填，请和商家协商一致" style="width: 350px" />
                </div>
              </div>
              <div class="textarea">
                <div class="textarea__title">运送方式：
                  <Select v-model="delievryMethod" style="width:150px">
                      <Option v-for="item in methods" :value="item.value" :key="item.value">{{ item.label }}</Option>
                  </Select>
                </div>
                <div style="margin-top: 20px;font-size: 13px;">
                  快递：免邮费
                </div>

              </div>
            </div>
            <!-- 商品合计金额 -->
            <div class="totalPrice">
              <p style="margin-right: 20px;">店铺合计：<span class="price-text">￥{{totalPrice}}元</span></p>
            </div>
          </div>
        </div>

      </div>

    </div>

    <!-- 付款实付金额 -->
    <div class="addCon">
       <div class="pay-box">
          <div class="box__wrapper">
            <div class="box__shadow">
              <div style="text-align: right;">
                <span class="realpay--title">实付款：</span>
                <span class="realpay--price-symbol">￥</span>
                <span class="realpay--price">{{totalPrice}}</span>
              </div>
              <div class="order-confirmAddr">
                <div style="text-align: right;">
                  <span style="font-weight:700;color:#333;">寄送至：</span>
                  <span style="margin-top:10px;">{{this.address.address}} {{this.address.detail}}</span>
                </div>
                <div style="text-align: right;">
                  <span style="font-weight:700;color:#333;">收货人:</span>
                  <span>  {{this.address.receName}}  {{this.address.recePhone}}</span>
                </div>
              </div>
            </div>
          </div>
       </div>
       <div class="submitOrder-container">
         <div class="wrapper">
           <router-link to="/showCart" class="go-back">
            <Icon type="ios-undo" />
             <span>返回购物车</span>
           </router-link>
          <div class="go-btn" @click="cartToOrder">提交订单</div>
           <!-- <router-link :to="{path:'/pay',query: {totalPrice: totalPrice,orderId:orderId}}">>
           不在这里跳转了
           </router-link> -->
         </div>
         <div>
           <Icon type="md-bulb" />若价格变动，请在提交订单后联系卖家改价，并查看已买到的宝贝
         </div>
       </div>
    </div>

    <!-- 模态框 -->
    <Modal
        v-model="modal"
        title="新增地址"
        width="600"
        >
        <Form ref="addForm" :model="addForm" :rules="ruleValidate" :label-width="100"  label-position="left"class="form">
          <FormItem label="地址信息:" prop="address" >
             <v-distpicker :placeholders="placeholders" @selected="updateSelect" :province="select.province" :city="select.city" :area="select.area" ></v-distpicker>
          </FormItem>

          <FormItem label="详细地址"prop="detail">
              <Input  v-model="addForm.detail" placeholder="请输入详细地址信息，如道路、门牌号、小区"></Input>
          </FormItem>
          <FormItem label="邮政编码:" prop="postcode" >
              <Input v-model="addForm.postcode" placeholder="请填写编码"></Input>
          </FormItem>
          <FormItem label="收货人姓名:" prop="receName" >
              <Input v-model="addForm.receName" placeholder="长度不超过25个字符"></Input>
          </FormItem>
          <FormItem label="手机号码:" prop="recePhone" >
              <Input v-model="addForm.recePhone" placeholder="请填写手机号码"></Input>
          </FormItem>

          <i-switch v-model="addForm.status" size="small"/>设置为默认地址


      </Form>
      <div slot="footer">
        <Button type="text" size="large" @click="modalCancel">取消</Button>
        <Button type="primary" size="large" @click="modalOk('addForm')">确定</Button>
      </div>
    </Modal>

  </div>
</template>

<script>
import VDistpicker from 'v-distpicker'
import api from '../utils/api'
import { sendServer } from '../utils/common';
export default {
  components:{
    VDistpicker
  },
  data(){
    const validatePhone = (rule, value, callback) => {
      let myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
        if (value === '') {
            callback(new Error('请输入11位手机号'));
        }else if(!myreg.test(value)){
            callback(new Error('手机号格式错误'));
        }else{
            callback()
        }
    };
    return{
      // orderId:'',//生成的订单号
      temfielPath:'http://localhost:8089/img/',
      imgtest:require('../../static/img/index/hanfu/5.jpg'),
      img1:require('../../static/img/index/eat/order1.jpg'),
      img2:require('../../static/img/index/eat/xcard.png'),
      img3:require('../../static/img/index/eat/order3.png'),
      current:-1,
      searchImgurl:require('../../static/img/index/logo.png'),
      addlist:[],
      addressId:'',//选择的地址
      modal:false,
      addForm:{
        address:'',
        detail:'',
        postcode:'',
        receName:'',
        recePhone:'',
        status:'',
        id:'',
      },

      ruleValidate: {
          address: [
              { required: true, message: '地址不可以为空！', trigger: 'blur' }
          ],
          detail: [
              { required: true, message: '详细地址不可以为空', trigger: 'blur' },
          ],
          postcode: [
              { required: true, message: '邮编不能为空！', trigger: 'blur' }
          ],
          receName: [
              { required: true, message: '收件人姓名不能为空', trigger: 'blur' }
          ],
          recePhone: [
              { required: true, message: '手机号码不能为空', trigger: 'blur' },
              { validator: validatePhone, trigger: 'blur' }
          ]

      },
      userInfo:JSON.parse(localStorage.getItem('userdata')),
      placeholders: {
          province: '------- 省 --------',
          city: '--- 市 ---',
          area: '--- 区 ---',
      },
      select:{
        province:'',
        city:'',
        area:''
      },
      //商家备注
      memo:"",
      delievryMethod:'',
      methods:[

        {
          value:'普通邮寄',
          label:'普通邮寄'
        },
         {
          value:'特快专递',
          label:'特快专递'
        },
        {
          value:'EMS专递方式',
          label:'EMS专递方式'
        }
      ],
      listItem:[],
      totalPrice:'',
      address:{}//选定的地址

    }
  },
  created(){
    this.getAddList()
    this.listItem=JSON.parse(decodeURIComponent(this.$route.query.listItem))
    console.log(this.listItem)
    this.totalPrice=this.$route.query.totalPrice

  },
  mounted(){
    // this.change(this.addressId);
  },
  methods:{
    //生成订单
    cartToOrder(){
      //两个页面不一样，直接购买不经过购物车的，可以在商品页面多传递一个参数，如果有参数就代表直接购买，调用那个接口
      let params={},send={}
      var time=new Date().getTime()
      send.userId=this.userInfo.id;
      send.addId=this.addressId;
      send.totalprice=this.totalPrice;
      send.deliverymethod=this.delievryMethod
      send.createTime=time
      send.memo=this.memo;
      send.paymethod='',//默认为空，表示未选择付款方式
      send.status=0;//默认为0 表示未付款
      params.send=send;
      console.log(send)
      if(this.$route.query.isDetail){
        console.log('详情页直接结算')
        let listItem=this.listItem[0]
        console.log(listItem)
        //商品图片，价格，数量，尺码，订单ID，这些是明细所需要的东西，
        let item=[]
        item[0]={};
        item[0].orderGoodsId=listItem.goodsId;
        item[0].quantity=listItem.quantity;
        item[0].goodsBuyPrice=listItem.goods.sellPrice;
        item[0].size=listItem.size
        params.url='order/cartToOrder?ids&item='+encodeURIComponent(JSON.stringify(item))
        sendServer(params,(commRes)=>{
          let orderId='';
          if(commRes.code==100){
            orderId=commRes.extend.orderId
            this.$Message.success('订单提交成功，请尽快支付');
            this.$router.push(
              {path:'/pay',query: {totalPrice:send.totalprice,orderId:orderId}}
            )
          }else{
            this.$Message.error('网络延迟，请稍后');
          }
        })

      }else{
        console.log('购物车叶子直接结算')
        // let itemList=JSON.stringify(this.listItem);
        //转还成字符串形式的参数 传递给后端，妈呀！！（（已废弃该方法）这个方法不好
        //只要传递商品ID即可
        let ids=[];
        this.listItem.forEach(item=>{
          ids.push(item.id)
        })
        params.url='order/cartToOrder?ids='+ids
        sendServer(params,(commRes)=>{
          console.log(commRes)
          let orderId='';
          if(commRes.code==100){
            orderId=commRes.extend.orderId
            this.$Message.success('订单提交成功，请尽快支付');
            this.$router.push(
              {path:'/pay',query: {totalPrice:send.totalprice,orderId:orderId}}
            )
          }else{
            this.$Message.error('网络延迟，请稍后');
          }
        })
      }


    },

    getAddList(){
      api.getAddList().then((res)=>{
        this.addlist=res.extend.addressList
        console.log(this.addlist)
        this.addlist.forEach(item=>{
          if(item.status==1){
            this.addressId=item.id//进入这个页面的默认地址为选中状态
          }
        })
        this.change(this.addressId)
      })
    },

    modalCancel(){
      this.modal=false
    },
    modalOk(name){
      this.$refs[name].validate((valid) => {
          if (valid) {
            let params={},send={};
            let status=0
            let addForm=this.addForm;
            send.userId=this.userInfo.id;
            send.address=addForm.address
            send.detail=addForm.detail
            send.postcode=addForm.postcode
            send.receName=addForm.receName
            send.recePhone=addForm.recePhone
            //状态为1 是默认地址
            if(addForm.status){
              status=1
            }
            send.status=status
            params.send=send;
            params.url="address/addAddress"
            console.log(params)
            sendServer(params,(commRes)=>{
              if(commRes.code==100){
                  this.getAddList()
                  this.$Message.success('新增成功!');
                  this.addForm={}
                  this.select.province='------- 省 --------',
                  this.select.city='--- 市 ---',
                  this.select.area='--- 区 ---'
              }else{
                  this.$Message.error('新增失败，请稍后再试');
              }
            })
          } else {
            this.$Message.error('表单不能为空');
          }
      })
      this.modal=false
    },
     updateSelect(data){
      let province=data.province.value,
      city=data.city.value,
      area=data.area.value
      let address=province+city+area
      this.addForm.address=address
   },
    change(data){//返回选中的id
      this.addlist.forEach(item=>{
        if(item.id==data){
          this.address=item
        }
      })
      console.log(this.address)
    },
    openAdd(){
      this.modal=!this.modal
      this.addForm={};
    }

  }

}
</script>
<style>

.ivu-steps-item.ivu-steps-status-process .ivu-steps-head-inner {
    border-color: #b13009;
    background-color: #b13009;
}
.ivu-radio-group {
    display: inline-block;
    font-size: 12px;
    vertical-align: middle;
    width: 970px;
}
</style>
<style scoped>

.go-btn {
    display: inline-block;
    width: 182px;
    height: 39px;
    position: relative;
    vertical-align: middle;
    line-height: 39px;
    cursor: pointer;
    text-align: center;
    font-size: 14px;
    font-weight: 700;
    background: #dd2727;
    /* background-color: rgb(255, 80, 0); */
    color: #fff;
}
.submitOrder-container .go-back {
    color: #36c;
    text-decoration: none;
    margin-right: 50px;
    line-height: 34px;
}
.submitOrder-container .wrapper {
    display: inline-block;
    padding: 0 0 5px 5px;
}
.submitOrder-container {
    text-align: right;
    margin-top: -1px;
    margin-bottom: 60px;
}
.order-confirmAddr {
    line-height: 1.6;
    margin-top: 10px;
    margin-bottom: 20px;

}
.realpay--price {
    /* color: rgb(255, 80, 0); */
    color: #dd1010;
    font: 700 26px tahoma;
}
.realpay--price-symbol {
    font-size: 26px;
    margin-right: 4px;
    color: #999;
}
.realpay--title {
    font-weight: 700;
    color: #333;
}
.box__shadow {
    border: 3px solid #fff0e8;
    min-width: 230px;
    padding: 10px 5px 10px 20px;
    display: flex;
    flex-direction: column;
}
.box__wrapper {
    display: inline-block;
    border: 1px solid #ff0036;
    width:350px;
}
.pay-box{
  /* margin: 15px; */
  margin: 15px 0 0 0;
  display: flex;
  justify-content: flex-end;
}
.totalPrice{
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: flex-end;
  margin: 0px;
  padding: 10px 0px;
  font-weight: 600;

}
.totalPrice .price-text{
  color: #dd1010;
   font-size: 15px;
}

.textarea .textarea__wrapper .textarea__input {
    vertical-align: top;
    width: 328px;
    resize: none;
    text-indent: 4px;
    border: 1px solid #ccc;
    outline: none;
    overflow: auto;
}
.textarea .textarea__wrapper {
    display: inline-block;
}
.textarea .textarea__title {
    vertical-align: top;
    display: inline-block;

}
.textarea{
  padding: 10px;
  width: 570px;
  border-left: 1px dotted #fff;
  /* height: 200px; */
}
.order-memo{
  display: flex;
  flex-direction: column;
  grid-template-columns: 1fr 1fr;
  margin: 0px;
  background-color: rgb(242, 247, 255);
  border-bottom: 1px dotted rgb(128, 178, 255);
}
.goods-box{
  display: flex;
  flex-direction: column;
  width:1050px;

}
.item-row__price p{
  font-weight: bold;
  font-style: normal;
  text-decoration: none;
  color: #dd1010;
  font-size: 14px;
  min-width: 100px;
}
.item-row__price {
    display: inline-block;
    width: 130px;
    text-align: right;
    padding-right: 5px;
    margin-top: 25px;
    margin-left: 50px;
}
.order-quantity {
    display: inline-block;
    vertical-align: top;
    text-align: center;
    width: 120px;
    padding: 10px 0;
    margin-right: 1px;
    margin-left: 30px;
    margin-top: 20px;
}
.order-itemInfo .info-price {
    color: #3c3c3c;
    vertical-align: top;
    display: inline-block;
    width: 120px;
    text-align: center;
    padding: 10px 0;
    margin-top: 20px;
}
.order-itemInfo .info-sku p {
    word-break: break-all;
}
.order-itemInfo .info-sku {
    margin-top: 10px;
    margin-left: 70px;
    color: #6c6c6c;
    width: 180px;
    display: inline-block;
    vertical-align: top;
    padding: 10px 0;
}
.info-img{
  width: 70px;
  height: 70px;
}
.order-itemInfo .delivery-time {
    color: #6c6c6c;
    display: inline-block;
    width: 180px;
}
.order-itemInfo .info-icon-list {
    width: 180px;
}
.order-itemInfo .info-title {
    color: #3c3c3c;
    width: 180px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    display: block;
}
.order-itemInfo .info-msg {
    padding-left: 10px;
    vertical-align: top;
    margin-top: 10px;
}

.order-itemInfo .info-detail {
    width: 310px;
    padding: 10px 0 10px 10px;
}
.order-itemInfo .info-cell {
    /* display: inline-block; */
    display: flex;
    flex-direction: row;
    box-sizing: content-box;
    margin-right: 1px;
}
.order-itemInfo {
  /* display: inline-block; */
  display: flex;
  flex-direction: row;
  vertical-align: top;
}
.goods-info{
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: stretch;
  margin: 0px;
  padding: 0px;
  background-color: rgb(251, 252, 255);
  border-bottom: 1px dotted rgb(221, 221, 221);
}
.img-icon{
  width:40px;
  height: 20px;
}
.order-orderInfo {
    position: relative;
    padding-bottom: 5px;
    margin-top: 25px;
    border-bottom: 1px dotted #80b2ff;
    height: 27px;
    width: 100%;
}
.goods-list{
  border: 0px solid black;
  position: relative;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  align-content: flex-start;
  flex-shrink: 0;
}
.item-headers{
    margin-top: 15px;
    color: #6c6c6c;
}
.item-headers-0 {
    width: 320px;
    margin-left: 0;
}
.item-headers-1 {
    width: 230px;
}
.item-headers-2, .item-headers-3 {
    width: 150px;
}
.item-headers-4 {
    width: 180px;
}
.item-headers-content {
    display: inline-block;
    height: 24px;
    line-height: 24px;
    text-align: center;
    border-bottom: 3px solid #b2d1ff;
    font-size: 12px;
    margin-left: 1px;
    vertical-align: bottom;
}
.bottom-add{
  margin-top: 10px;
  margin-left: 100px;
}
.default-text{
  padding-left: 20px;
  color: #dd1010;
}
.radio{
  margin-top: 10px;
}
.selected{
  margin-top: 10px;
  width: 100%;
  font-size:14px;
  font-weight:700;
  border: solid 1px #f40;
  background-color: #fff0e8;
  box-shadow: 5px 5px 0 #f3f3f3;
}

label:hover{
  /* border: solid 1px #f40; */
  background-color: #fff0e8;
  box-shadow: 5px 5px 0 #f3f3f3;
}
.addlist{
  display: flex;
  flex-direction: row;

}
.left{
  width: 80px;
  font-size: 15px;
  padding-top: 12px;
  color: #dd1010;

}
.right{

  width: 900px;
  /* border: 1px solid rgb(47, 40, 145) ; */
}

label{
  padding-left: 20px;
  width: 100%;
  color: #515A6E;
  font-weight: 400;
}
.add-title{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  font-size: 14px;
  font-weight: 600;
  margin-top: 20px;
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
.addCon{
  width: 1050px;
  margin: 5px auto;
  /* border: solid 1px red; */
}

.top1 img{
  width: 210px;
  height: 100px;
}
.top2{
  width: 50%;
}
</style>


