<template>
  <div class="container1">
       <!-- 商品展示容器 -->
      <div class="goods-box2">

        <div class="goods-box-box">
          <div class="goods-list">
            <div class="goods-info-show" v-for="(item, index) in goodsList" :key="index">
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
                已有<span>{{item.remark}}</span>人评价
              </div>
              <div class="goods-show-seller">
                <span>{{item.goodsname}}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
import api from '../../utils/api'
export default {
  name: 'HotCart',
  beforeRouteEnter (to, from, next) {
    window.scrollTo(0, 0);
    next();
  },
  data () {
    return {
        goodsList:[],//商品列
        temfielPath:'http://localhost:8089/img/',

        };
  },
  methods: {
    //获取商品信息
    getGoodsList(){
        var vm=this;
        api.getGoodsList().then((res)=>{
          this.goodsList=res.extend.goodsList.slice(1,11)
        })

    },
  },
  created () {
    this.getGoodsList();
  },
};
</script>

<style>
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
.goods-box2 {
  margin: 15px auto;
  width: 1100px;
  min-width: 1000px;
  display: flex;
  background-color: #fff;
}


/* ---------------商品栏开始------------------- */
.goods-box-box {
  margin-left: 15px;
  /* width: calc(100% - 215px); */
  width:1050px;
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
.goods-info-show{
  /* width: 240px; */
  width:200px;
  padding: 10px;
  margin: 15px 0px;
  border: 1px solid #fff;
  cursor: pointer;
}
.goods-info-show:hover{
  border: 1px solid #ccc;
  box-shadow: 0px 0px 15px #ccc;
}
.goods-show-price{
  margin-top: 6px;
}
.goods-show-detail{
  font-size: 12px;
  margin: 6px 0px;
  text-overflow:-o-ellipsis-lastline;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.goods-show-num{
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
    width: 178px;
    height: 178px;
    /* 218 */
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
