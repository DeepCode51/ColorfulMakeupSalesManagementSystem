<template>
<!-- // 查看购物车 -->
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
    <!-- 导航条 -->
     <div class="search-nav">
      <div class="search-nav-container">
        <ul>
          <li>全部商品</li>
          <li>库存不足</li>
          <li>降价商品</li>
        </ul>
      </div>
    </div>


    <div class="goods-list-container">
      <div id="cart">
        <section class="cartMain" v-show="cartList.length ==0">
          <div class="no-cart">
            <p class="no-cart-text">您的购物车当前没有商品
            <router-link to="/">立即购买</router-link></p>
          </div>
        </section>
        <section class="cartMain" id="cartMain" v-show="cartList.length !=0">
          <!-- 头部 -->
            <div class="cartMain_hd">
                <ul class="order_lists cartTop">
                    <li class="list_chk" >
                        <!--所有商品全选-->
                        <input type="checkbox" id="all" class="whole_check">
                        <label for="all" @click="cartchoose()" :class="cartListChosed?'mark':''" ></label> <!--  -->
                        全选
                    </li>
                    <li class="list_con">商品信息</li>
                    <li class="list_info">商品参数</li>
                    <li class="list_price">单价</li>
                    <li class="list_amount">数量</li>
                    <li class="list_sum">金额</li>
                    <li class="list_op">操作</li>

                </ul>
            </div>

            <div class="cartBox" id='cartBox'>
                <!-- 店铺名称 -->
                <div class="shop_info">
                  <!-- Redis好难懂啊啊啊 哭了 -->
                    <!-- <div class="all_check">
                        <input type="checkbox" id="shop_a" class="shopChoice">
                        <label for="shop_a" class="shop" :class="item.check?'mark':''" @click="shopchoose(item)"></label>
                    </div> -->
                    <div class="shop_name">
                        店铺：<router-link to="/aboutUs">缤色彩妆</router-link>
                    </div>
                </div>

                <div class="order_content">
                    <ul class="order_lists list-border" v-for="(item,index) in cartList" :key="index">
                        <!-- 选择按钮 -->
                        <li class="list_chk">
                            <input type="checkbox" id="checkbox_2" class="son_check">
                            <label for="checkbox_2" :class="item.choosed?'mark':''" :data-goodsId="item.id" @click="choose(item)"></label>
                        </li>

                          <!-- 图片 -->
                        <li class="list_con">

                            <div class="list_img"><a href="javascript:;">
                              <el-popover placement="right" title="" trigger="hover">
                                 <img :src="tempfilePath+item.goods.picture"  alt=""  style="width:200px;height:200px" class="img-cart">
                                <img slot="reference" :src="tempfilePath+item.goods.picture" alt="" style="width:80px;height:80px">
                              </el-popover>
                            </a>
                            </div>


                            <div class="list_text">
                              <router-link target="_blank" :to="{path:'/goodsDetail',query:{goodsId:item.goods.id}}">
                                {{item.goods.goodsname}}
                              </router-link>
                            <span class="list_custom">定制</span>
                            </div>
                        </li>
                        <li class="list_info">
                            <p>规格：默认</p>
                            <p>尺码：{{item.size}}</p>
                        </li>
                        <li class="list_price">
                            <p class="price">￥{{item.goods.sellPrice}}</p>
                            <div class="charge">
                                <p>更多促销</p>

                            </div>
                        </li>
                        <li class="list_amount">
                            <div class="amount_box">
                                <a href="javascript:;" class="reduce reSty" @click="reduce(item)">-</a>
                                <input type="number"  v-model="item.quantity" class="sum" v-on:input="calculate(item)" max="50" min="1" minlength="1">
                                <!--  -->
                                <a href="javascript:;" class="plus" @click="add(item)">+</a>
                            </div>
                        </li>
                        <li class="list_sum">
                            <p class="sum_price">￥{{item.sum}}</p>
                            <!-- ,{{item.quantity*item.goods.sellPrice}} -->
                        </li>
                        <li class="list_op">
                            <p class="collect" @click="cartToLike(item)"><a  class="colBtn">移到收藏夹</a></p>
                            <p class="del"><el-button type="text" @click="open(item.id)"><a class="delBtn">删除商品</a></el-button></p>
                        </li>
                    </ul>
                </div>
            </div>
            <!--底部-->
            <div class="bar-wrapper" id="bar-bottom">

                <div class="bar-left">

                  <div class="left-chose"><input type="checkbox" id="forAll" ><label for="forAll" @click="cartchoose()" :class="cartListChosed?'mark':''" ></label></div>
                  <div class="left-all"> 全选</div>
                  <div class="left-del"><el-button type="text" @click="deleteByBatch()"><a class="colBtn">删除</a></el-button></div>
                  <div class="left-op"><a href="javascript:;" class="colBtn">移到收藏夹</a></div>
                  <div class="left-op"><el-button type="text" @click="clearOpen()"><a href="javascript:;" class="colBtn">清空购物车</a></el-button></div>

                </div>
                 <div class="bar-right">
                    <div class="piece">已选商品<strong class="piece_num">{{allNum}}</strong>件</div>
                    <div class="totalMoney">共计: <strong class="total_text">￥{{allPrice}}</strong></div>
                    <div class="calBtn"><a :class="allPrice!=0?'btn_sty':''" @click="toPay()">结算</a></div>
                    <!--    :class="cartListChosed?'mark':''"   -->
                </div>
            </div>

        </section>


      </div>
    </div>


    <!-- 热爱 -->
    <div class="last">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="掌柜热卖" name="first">
          <!--  宝贝专区-->
		      <hot-cart></hot-cart>
        </el-tab-pane>
        <el-tab-pane label="最近收藏" name="second">
          <!--  最近收藏 前10条-->
		      <collection a="a"></collection>
        </el-tab-pane>
      </el-tabs>
    </div>

  </div>
</template>

<script>
import api from '../utils/api'
import HotCart from '@/components/common/HotCart';
import Collection from '@/components/common/Collection';
import store from '@/vuex/store';
import { sendServer } from '../utils/common';
export default {
  name: 'ShowCart',
  components: {
   HotCart,
   Collection
  },
  data () {
    return {
      activeName:'first',
      searchData:'',
      tempfilePath:'http://localhost:8089/img/',
      searchImgurl:require('../../static/img/index/logo.png'),
      cartList:[],
      cartListChosed:false,//全选的状态 为真的时候全选
      cartchoseNum:0,//选中购物车商品的量，判断是否与整个购物车商品的数量相同，相同则把购物车的全选状态变为true
      allNum:0,//一共选择了多少件商品
      allPrice:0,//一共多少钱，to.fixed(2)保留两位小数
      // chosedList:[],//选中购物车的ID
      // okBtn:'',
      // list:[]
    }
  },
  beforeRouteEnter (to, from, next) {
    window.scrollTo(0, 0);
    next();
  },
  created () {
    //查询购物车
    this.getCartList();
  },
  mounted(){
    window.onscroll=function(e){
      // 获取当前窗口的宽度(包含滚动条)，当浏览器宽度调整时，这个值也会跟着变化。
			let a=window.innerHeight;
			// console.log(a)
			// 获取滚动条距离当前距离顶部的高度
			var scrolltop=document.documentElement.scrollTop||document.body.scrollTop;
			// console.log(scrolltop)
			var all=document.body.scrollHeight
      // console.log(all)
      var box=document.getElementById('bar-bottom');
      if(box){
        if(all-scrolltop-390-800<a){
				box.style.position='relative'
        }else{
          box.style.position='fixed'
        }

      }

    }
  },
  methods: {
    //移入到收藏夹
    cartToLike(item){
      let params={},send={}
      send.userId=item.userId;
      send.id=item.id;
      send.goodsId=item.goodsId;
      params.send=send;
      params.url="cart/cartToLike"
      sendServer(params,(commRes)=>{
        if(commRes.code==100){
          this.$Message.success('移动成功！')
          this.getCartList()

        }else{
          this.$Message.error('移动失败！')
        }
      })
    },
    //去结算,跳转到订单页面，去结账，并传递相应的参数,
    toPay(){
      //跳转到订单页面 我要给你什么东西 呢？
      //用户id，以及和订单相关的东西，商品 ===》商品ID，图片，数量，金额 要在购物车页面展示，那我要把打包成一个数组 发过去
      let listItem = []
      this.cartList.forEach(item => {
          if(item.choosed){
            listItem.push(item)
          }
      })
      // console.log(listItem)
      // console.log(JSON.stringify(listItem))
      // this.$router.push('/order');
      this.$router.push({
          path:'/order',
          query:{
            listItem:JSON.stringify(listItem),
            totalPrice:this.allPrice
          }
      })

    },
    clearOpen(){
      this.$confirm('确定清空购物车吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this. clearCart();
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消清空'
          });
        });

    },
    //清空购物车
    clearCart(){
      let userId=JSON.parse(localStorage.getItem('userdata')).id;
      let params={},send={}
      send.userId=userId;
      params.send=send;
      params.url="cart/clearCart"
      sendServer(params,(commRes)=>{
        if(commRes.code==100){
          this.cartList=[]
          this.$message({
            type: 'success',
            message: '清空成功!'
          });
        }
      })
    },
    deleteByBatch(){
      let list = []
      this.cartList.forEach(item => {
          if(item.choosed){
            list.push(item.id)
          }
      })
      this.$confirm('是否删除这些商品?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.delByBatch(list);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
    },
    //批量删除
    delByBatch(ids){
      console.log(ids)
      let vm=this;
      this.axios({
        method:"GET",
        url:'http://localhost:8089/hf/cart/deleteByBatch?ids='+ids
      }).then((res)=>{
        console.log(res)
        if(res.data.code==100){
          //删除成功的时候刷新一下购物车
          this.getCartList()
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }else{
          this.$message({
            type: 'error',
            message: '删除失败!'
          });

        }

      })


    },

    choose(item){
      // let j = null;
      // for(let i =0 ;i<this.list.length;i++){
      //   if(this.list[i]===item.id){
      //      j = i;
      //   }
      // }
      // if(j!==null){
      //    this.list.splice(j,1);
      // }else{
      //   this.list.push(item.id)
      // }
      // console.log(this.list)
      // ---------------------
      if(item.choosed){
        this.chooseItemfalse(item)
      }else{
        this.chooseItemtrue(item)
      }
    },
    chooseItemtrue(item){
      item.choosed=true;
      ++this.cartchoseNum===this.cartList.length?this.cartListChosed=true:this.cartListChosed=false
      this.allNum+=parseInt(item.quantity)
      this.allPrice+=parseInt(item.sum)
    },
    chooseItemfalse(item){
      item.choosed=false;
      --this.cartchoseNum;

      this.cartListChosed=false
      this.allNum-=parseInt(item.quantity)
      this.allPrice-=parseInt(item.sum)
    },
    choosefalse(item){
      this.cartList.forEach(item=>{
        item.choosed==true && this.chooseItemfalse(item)
      })
    },
     choosetrue(item){
      this.cartList.forEach(item=>{
        item.choosed==false && this.chooseItemtrue(item)
      })
    },
    cartchoose(){
       this.cartListChosed=!this.cartListChosed
       if(this.cartListChosed){
        //  this.list=[]
         this.cartList.forEach(item=>{
           this.choosetrue(item)
          //  this.list.push(item.id)
         })
       }else{
         this.cartList.forEach(item=>{
           this.choosefalse(item)
         })
       }
    },
     open(id) {
       console.log(id)
        this.$confirm('是否删除该商品?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let params={},send={}
          send.id=id;
          params.send=send;
          params.url="cart/deleteOne";
          sendServer(params,(commRes)=>{
            if(commRes.code==100){
              let cartListArray=this.cartList
              for(let i= 0; i< cartListArray.length;i++){
                if(cartListArray[i].id==id){
                  cartListArray.splice(i,1)
                  this.cartList=cartListArray
                }
              }
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
            }
          })

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
    calculate(pro){
      let oldsum=pro.sum//之前的总价pro.sum
      let oldnum=oldsum/pro.goods.sellPrice//之前的数量 pro.num
      pro.quantity=parseInt(pro.quantity)
      if(pro.quantity==0){
        this.$Message.error('数量不可以为0')
        pro.quantity=oldnum
      }else{
        pro.sum=pro.quantity*pro.goods.sellPrice
      }

      if(pro.quantity>50){
        this.$Message.error('最多只能添加50个哦')
        pro.quantity=50
        pro.sum= pro.quantity*pro.goods.sellPrice
      }
      let diffsum=pro.sum-oldsum//差价
      let diffnum=pro.quantity-oldnum//差量
      if(pro.choosed){//如果商品被选中
          this.allPrice+=diffsum//计算总价
          this.allNum+=diffnum//计算总量
      }
      let id=pro.id
      let quantity =pro.quantity
      api.updateCart(id,quantity).then((res)=>{
      })
    },
    //减少按钮
     reduce(pro){
        if(pro.quantity===1) return
        pro.quantity--
        pro.sum-= pro.goods.sellPrice
        if(pro.choosed){
            this.allNum--
            this.allPrice-=pro.goods.sellPrice
        }
        let id=pro.id
        let quantity =pro.quantity
        api.updateCart(id,quantity).then((res)=>{

        })

    },
    //增加按钮
    add(pro){
        if(pro.quantity===50) return
        pro.quantity++
        pro.sum+=pro.goods.sellPrice
        if(pro.choosed){
          console.log('i am chosed')
          this.allNum++
          this.allPrice+=pro.goods.sellPrice
        }
        let id=pro.id
        let quantity =pro.quantity
        api.updateCart(id,quantity).then((res)=>{

        })
    },
    handleClick(tab, event) {
    },

    //查询购物车商品
    getCartList(){
      let params={},send={};
      let userId=JSON.parse(localStorage.getItem('userdata')).id;
      console.log(userId)
      send.userId=userId;
      if(userId !=null || userId != undefined){
        params.send=send;
        params.url="cart/showCart"
        sendServer(params,(commRes)=>{
          console.log(commRes)
          if(commRes.code==100){
            // 返回的id，一个是商品goodsId ，一个是购物车id，还有一个商品id在goods里面 还得第三层获取，区分一下商品id还是购物车id
            commRes.extend.cartList.forEach((item)=>{
              item.choosed=false;//给每个商品添加一个choosed属性，来判断是否需要拿到
              item.sum=item.quantity*item.goods.sellPrice
            })
            this.cartList=commRes.extend.cartList
          }
        })
      }else{
        this.$router.push('/loign')//去登录
      }

    },
    //查询商品
    goSearch(){

    },
    // ...mapActions(['loadAddress']),
    // select (selection, row) {
    //   console.log(selection);
    //   this.goodsCheckList = selection;
    // },
    // changeAddress (data) {
    //   const father = this;
    //   this.address.forEach(item => {
    //     if (item.addressId === data) {
    //       father.checkAddress.name = item.name;
    //       father.checkAddress.address = `${item.name} ${item.province} ${item.city} ${item.address} ${item.phone} ${item.postalcode}`;
    //     }
    //   });
    // }
  },

};
</script>

<style scoped>
@import "../../static/css/cart.css";
@import "../../static/css/reset.css";
.last{
  width: 1050px;
  margin: 30px auto;
  height: 800px;
}



.lable1{
  margin-top: 20px;
}
.search-nav{
  /* width: 80%; */
  width: 1050px;
  height: 50px;
  border-bottom: 2px solid #B1191A;
  margin: 0 auto;
}
.search-nav-container{
  /* width: 80%; */
  /* width:100%; */
  width: 1050px;
  min-width: 1000px;
  height: 64px;
  margin: 0px auto;
}
.search-nav-container-90{
  width: 90%;
}
.search-nav-container ul{
  margin: 0px;
  padding-left: 0px;
  list-style: none;
}
.search-nav-container li{
  cursor: pointer;
  margin-right: 30px;
  line-height: 64px;
  /* color: #C81623; */
  color: black;
  font-size: 14px;
  float: left;
  font-weight: 600;
}
/* .search-nav-container li:first-child{
  padding: 0px 20px;
  background:#B1191A;
  margin: 0px;
  color: #fff;
}  */
.goods-list-container {
  margin: 15px auto;
  width: 80%;
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
.no-cart{
 margin:80px auto;
 font-size: 20px;
 text-align: center;

}
.no-cart-text{
  margin: 0 auto;
}
</style>
<style>
.el-tabs__item:hover {
  color: #f40;
  /* background-color: rgb(158, 28, 24); ; */
}
.el-tabs__item.is-active {
    color: #f40;
    /* background-color: rgb(158, 28, 24);; */
}
.el-tabs__active-bar {
    position: absolute;
    bottom: 0;
    left: 0;
    height: 2px;
    background-color: #f40;
    z-index: 1;
}
</style>
