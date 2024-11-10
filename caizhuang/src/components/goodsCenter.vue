<template>
  <div class="container1">
       <!-- 商品展示容器 -->
      <div class="goods-box1">
        <div class="as-box">
          <div class="item-as-title">
            <span>商品精选</span>
            <span>广告</span>
          </div>
          <div class="item-as" v-for="(item,index) in boxGoods" :key="index">
            <router-link target="_blank" :to="{path:'/goodsDetail',query:{goodsId:item.id}}">
              <div class="item-as-img">
                <img :src="temfielPath+item.picture" class="boxGoodsImg"/>
              </div>
              <div class="item-as-price">
                <span>
                  <Icon type="social-yen text-danger"></Icon>
                  <span class="seckill-price text-danger">{{item.price}}</span>
                </span>
              </div>
              <div class="item-as-intro">
                <span>{{item.description}}</span>
              </div>
              <div class="item-as-selled">
                已有<span>{{item.remark}}</span>人评价
                <!-- 已售出<span>{{item.soldNum}}</span>件 -->
              </div>
            </router-link>
          </div>
        </div>
        <div class="goods-list-box">
          <div class="goods-list-tool1">
            <ul>
              <li v-for="(item,index) in goodsTool" :key="index" @click="orderBy(item.en, index)"><span :class="{ 'goods-list-tool1-active': isAction[index]}">{{item.title}} <Icon :type="icon[index]"></Icon></span></li>
            </ul>
          </div>
          <div class="goods-list">
            <div class="goods-show-info" v-for="(item, index) in goodsList" :key="index">
              <div class="goods-show-img">
                <router-link target="_blank":to="{path:'/goodsDetail',query:{goodsId:item.id}}"><img :src="temfielPath+item.picture" class="goods-img"/></router-link>
              </div>
              <div class="goods-show-price">
                <span>
                  <Icon type="social-yen text-danger"></Icon>
                  <span class="seckill-price text-danger">{{item.price}}</span>
                </span>
              </div>
              <div class="goods-show-detail">
                <span>{{item.description}}</span>
              </div>
              <div class="goods-show-num">
                <div>已有<span>{{item.remark}}</span>人评价</div>
                <div>已售出<span>{{item.soldNum}}</span>件</div>
              </div>
              <div class="goods-show-seller">
                <span>{{item.goodsname}}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- <div class="goods-page">
        <Page :total="100" show-sizer></Page>
      </div> -->
  </div>
</template>

<script>
import api from '../utils/api'
import {sendServer} from '../utils/common'
import store from '@/vuex/store';
import { mapState, mapActions, mapGetters, mapMutations } from 'vuex';
export default {
  name: 'GoodsList',
  beforeRouteEnter (to, from, next) {
    window.scrollTo(0, 0);
    next();
  },
  data () {
    return {
        goodsList:[],//商品列表
        boxGoods:[],
        temfielPath:'http://localhost:8089/hf/img/',


        searchItem: '',
        isAction: [ true, false, false ],
        icon: [ 'arrow-up-a', 'arrow-down-a', 'arrow-down-a' ],
        goodsTool: [
            {title: '综合', en: 'sale'},
            {title: '评论数', en: 'remark'},
            {title: '价格', en: 'sellPrice'}
        ]
        };
  },
  computed: {
    ...mapState(['asItems', 'isLoading']),
    ...mapGetters(['orderGoodsList'])
  },
  methods: {
    //获取商品信息
    getGoodsList(){
        var vm=this;
        api.getGoodsList().then((res)=>{
          this.goodsList=res.extend.goodsList.slice(0,40)
          this.boxGoods=res.extend.goodsList.slice(10,24)
        })
    },


    ...mapActions(['loadGoodsList']),
    ...mapMutations(['SET_GOODS_ORDER_BY']),

    orderBy (data, index) {
      console.log(data);
      this.icon = [ 'arrow-down-a', 'arrow-down-a', 'arrow-down-a' ];
      this.isAction = [ false, false, false ];
      this.isAction[index] = true;
      this.icon[index] = 'arrow-up-a';
      this.SET_GOODS_ORDER_BY(data);
    }
  },
  created () {
    // this.loadGoodsList();
    this.getGoodsList();
  },
  mounted () {
    this.searchItem = this.$route.query.searchData;
  },
  store,

};
</script>

<style scoped>
.container1 {
  background-color: #F6F6F6;
}
.text-danger {
  color: #A94442;
}
.seckill-price{
  margin-right: 5px;
  font-size: 25px;
  font-weight: bold;
}
.goods-box1 {
  margin: 15px auto;
  width: 1200px;
  min-width: 1000px;
  display: flex;
  background-color: #fff;
}
/* ---------------侧边广告栏开始------------------- */
.as-box {
  width: 200px;
  border: 1px solid #ccc;
}
.item-as-title{
  width: 100%;
  height: 36px;
  color: #B1191A;
  line-height: 36px;
  font-size: 18px;
}
.item-as-title span:first-child{
  margin-left: 20px;
}
.item-as-title span:last-child{
  float: right;
  margin-right: 15px;
  font-size: 10px;
  color: #ccc;
}
.item-as{
  width: 160px;
  margin: 18px auto;
}
.item-as-img{
  width: 160px;
  height: 160px;
  margin: 0px auto;
}
.item-as-price span{
  font-size: 18px;
}
.item-as-intro{
  margin-top: 5px;
  font-size: 12px;
}
.item-as-selled{
  margin-top: 5px;
  font-size: 12px;
}
.item-as-selled span{
  color: #005AA0;
}
/* ---------------侧边广告栏结束------------------- */

/* ---------------商品栏开始------------------- */
.goods-list-box {
  margin-left: 15px;
  width: calc(100% - 215px);
}
.goods-list-tool1{
  width: 100%;
  height: 38px;
  border: 1px solid #ccc;
  background-color: #F1F1F1;
}
.goods-list-tool1 ul{
  padding-left: 15px;
  list-style: none;
}
.goods-list-tool1 li{
  cursor: pointer;
  float: left;
}
.goods-list-tool1 span{
  padding: 5px 8px;
  border: 1px solid #ccc;
  border-left: none;
  line-height: 36px;
  background-color: #fff;
}
.goods-list-tool1 span:hover{
  border: 1px solid #E4393C;
}
.goods-list-tool1 i:hover{
  color: #E4393C;
}
.goods-list-tool1-active {
  color: #fff;
  border-left: 1px solid #ccc;
  background-color: #E4393C !important;
}

.goods-list {
  display: flex;
  flex-wrap: wrap;
  /* justify-content: space-between; */
}
.goods-show-info{
  width: 240px;
  padding: 10px;
  margin: 15px 0px;
  border: 1px solid #fff;
  cursor: pointer;
}
.goods-show-info:hover{
  border: 1px solid #ccc;
  box-shadow: 0px 0px 15px #ccc;
}
.goods-show-price{
  margin-top: 6px;
}
.goods-show-detail{
  font-size: 12px;
  margin: 6px 0px;
/*以下css显示两行文字 多余的用。。。。 */
  text-overflow:-o-ellipsis-lastline;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.goods-show-num{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  font-size: 12px;
  margin-bottom: 6px;
  color: #009688;
}
.goods-show-num span{
  color: #005AA0;
  font-weight: bold;
}
.goods-show-seller{
  font-size: 12px;
  color:#E4393C;
}
.goods-page {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}
.goods-img{
    width: 218px;
    height: 218px;
}
.boxGoodsImg{
  width: 160px;
  height: 160px;
}
.item-as a{
  color: #495060;
}

/* ---------------商品栏结束------------------- */

</style>
