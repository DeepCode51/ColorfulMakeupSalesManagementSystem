<template>
  <div>
    <Search ></Search>
    <div class="line"></div>
    <!-- <hr/> -->
    <!-- <GoodsListNav></GoodsListNav> -->
    <!-- 添加成功提示 -->
    <div class="add-info-box-container">
      <div class="add-info-box">
        <div class="add-info-detail">
          <div class="add-info-title">
            <p>
              <Icon type="ios-cart" /> 商品已成功加入购物车！</p>
          </div>
          <div class="add-info-box-row">
            <div class="add-info-img">
              <img :src="tempfilePath+cartInfo.img" alt="">
            </div>
            <div class="add-info-intro">
              <p>{{cartInfo.desc}} </p>
              <p class="add-info-intro-detail">尺码{{cartInfo.size}}/ 数量：{{cartInfo.quantity}}件</p>
            </div>
          </div>
        </div>
        <div class="car-btn-group">
          <div></div>
          <div class="car-btn-row">
            <router-link :to="{path:'/goodsDetail',query:{goodsId:cartInfo.goodsId}}">
              <button class="btn-car btn-car-to-detail">查看商品详情</button>
            </router-link>
            <router-link to="/showcart">
              <button class="btn-car btn-car-to-pay">去购物车结算 > </button>
            </router-link>
          </div>
        </div>
      </div>
    </div>
    <div class="other-user-buy-box">
      <div class="other-user-buy-title">
        <p>可以顺便看下其他商品哦 ~</p>
      </div>
      <div class="flex-box">
        <div class="other-user-buy-row" v-for="(item,index) in recommend" :key="index">
          <router-link :to="{path:'/goodsDetail',query:{goodsId:item.id}}">
          <div class="other-user-buy-item-box">
            <div class="other-user-buy-item-img">

               <el-popover placement="right" title="" trigger="hover">
                <img :src="tempfilePath+item.picture" alt=""  style="width:196px;height:196px" class="img-cart">
                <img slot="reference" :src="tempfilePath+item.picture" alt="simage_url" style="width:96px;height:96px">
                <!-- style="height: 50px;width: 130px" -->
               </el-popover>

            </div>
            <div class="other-buy-detail-box">
              <div class="other-buy-title">
                <router-link :to="{path:'/goodsDetail',query:{goodsId:item.id}}">
                  <p>{{item.description}}</p>
                </router-link>
              </div>
              <div class="other-buy-price">
                <p>￥{{item.sellPrice}}</p>
              </div>
              <div class="other-buy-btn-box">
                <button class="other-buy-btn"><Icon type="ios-cart"></Icon> 加入购物车</button>

              </div>
            </div>
          </div>
          </router-link>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import Search from '@/components/Search';
import GoodsListNav from '@/components/nav/GoodsListNav';
import store from '@/vuex/store';
import { mapState, mapActions } from 'vuex';
export default {
  name: 'ShoppingCart',
  data(){
    return{
      tempfilePath:'http://localhost:8089/img/',
      cartInfo:[],
      // JSON.parse(decodeURIComponent(this.$route.query.cartInfo))
    }
  },
  beforeRouteEnter (to, from, next) {
    window.scrollTo(0, 0);
    next();
  },
  created () {

    this.getGoodsForty();

    this.cartInfo=JSON.parse(decodeURIComponent(this.$route.query.cartInfo))
    console.log(this.cartInfo)

  },
  computed: {
    ...mapState(['recommend'])
  },
  methods: {
    ...mapActions(['getGoodsForty'])
  },

  components: {
    Search,
    GoodsListNav
  },

};
</script>

<style scoped>
/****************************加入购物车页面开始*****************************/
.add-info-box-container{
  width: 100%;
  background-color: #F5F5F5;
}
.add-info-box{
  width: 90%;
  margin: 0px auto;
  padding: 15px 0px;
  display: flex;
    flex-direction: row;
    justify-content: space-between;
}
.add-info-detail{
  display: flex;
    flex-direction: column;
}
.add-info-title{
  font-size: 25px;
  color: #71b247;
}
.add-info-box-row{
  display: flex;
    flex-direction: row;
    margin-top: 15px;
}
.add-info-img{
  width: 56px;
  height: 56px;
  margin-right: 15px;
}
.add-info-img img{
  width: 100%;
}
.add-info-intro{
  display: flex;
    flex-direction: column;
    justify-content: center;
}
.add-info-intro-detail{
  font-size: 12px;
  color: #999999;
}
.car-btn-group{
  display: flex;
    flex-direction: column;
    justify-content: space-between;
}
/*按钮*/
.btn-car{
  padding: 8px 10px;
  font-size: 16px;
  border-radius: 0px;
  border:none;
  margin-right: 15px;
}
.btn-car-to-detail{
  background-color: #fff;
  color: #e4393c;
  border:1px solid #fff;
}
.btn-car-to-detail:hover{
border:1px solid #e4393c;
}
.btn-car-to-pay{
  background-color: #e4393c;
  color: #fff;
  border:1px solid #e4393c;
}
.btn-car-to-pay:hover{
  background-color: #c91623;
  border:1px solid #c91623;
}
/*其他用户购买*/
.other-user-buy-box{
  width: 90%;
  margin: 0px auto;
  display: flex;
    flex-direction: column;
}
.other-user-buy-title{
  margin-top: 25px;
  font-size: 14px;
  color: #666;
  font-weight: bold;
}
.other-user-buy-row{
  margin-top: 25px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 21%;
}
.other-user-buy-item-box{
  display: flex;
    flex-direction: row;
}
.other-user-buy-item-img{
  width: 96px;
  height: 96px;
}
.other-user-buy-item-img img{
  width: 100%;
}
.other-buy-detail-box{
  width: 160px;
  margin-left: 15px;
  display: flex;
    flex-direction: column;
}
.other-buy-title{
  font-size: 12px;
}
.other-buy-title a{
  color: #2c2c2c;
  text-decoration: none;
}
.other-buy-price{
  font-size: 12px;
  font-weight: bold;
  color: #E4393C;
}
.other-buy-btn{
  padding: 3px 10px;
  color: #E4393C;
  font-size: 12px;
  border: 1px solid #E4393C;
  border-radius: 0px;
  background-color: #fff;
}
/* .other-buy-btn:hover{

} */
.other-buy-btn:hover{
  color: #fff;
  border: 1px solid #c91623;
  background-color: #c91623;
}
.line{
  margin-top: 5px;
  width: 100%;
  height: 1px;
  border:1px solid #ff5000 ;
  /* border-left:1px solid #7e040c;
  border-right:1px solid #7e040c;
  border-bottom: 1px solid #7e040c; */
}
.img-cart{
  width:96px;
  height: 96px;
}
.flex-box{
  width: 100%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-around;
  align-items: center;

}

/****************************加入购物车页面结束*****************************/
</style>
