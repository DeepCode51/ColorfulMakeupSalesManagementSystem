<template>
  <div class="container1">
    <!-- <Search></Search> --> <!-- 商品搜索 -->
    <!-- <HomeNav></HomeNav> --> <!-- 商品分类 -->
    <!-- 商品显示区域 -->
    <div class="content">
      <!-- 秒杀 -->
      <div class="seckill">
        <!-- 头部 -->
        <div class="seckill-head">
          <div class="seckill-icon">
            <img src="static/img/index/clock.png">
          </div>
          <div class="seckill-text">
            <span class="seckill-title">限时秒杀</span>
            <span class="seckill-remarks">总有你想不到的低价</span>
          </div>
          <div class="count-down">
            <span class="count-down-text">当前场次</span>
            <span class="count-down-num count-down-hour">{{ seckillsHours }}</span>
            <span class="count-down-point">:</span>
            <span class="count-down-num count-down-minute">{{ seckillsMinutes }}</span>
            <span class="count-down-point">:</span>
            <span class="count-down-num count-down-seconds">{{ seckillsSeconds }}</span>
            <span class="count-down-text">后结束抢购</span>
          </div>
        </div>
        <!-- 内容 -->
        <div class="seckill-content">
          <div class="seckill-item" v-for="(item, index) in seckills.goodsList" :key="index">
            <div class="seckill-item-img">
              <router-link target="_blank" :to="{path:'/goodsDetail',query:{goodsId:item.id}}">
                <img :src="tempfilePath+item.picture">
              </router-link>
            </div>
            <div class="seckill-item-info">
              <p>{{item.description}}</p>
              <p>
                <span class="seckill-price text-danger"><Icon type="social-yen"></Icon>{{item.sellPrice}}</span>
                <span class="seckill-old-price"><s>{{item.price}}</s></span>
              </p>
            </div>
          </div>
        </div>
      </div>
      <!-- 配饰 -->
      <div class="item-class" id="peishi" name="peishi">
        <div class="item-class-head">
          <span class="item-class-title">配饰</span>
          <ul>
            <li v-for="(item, index) in peishi.link" :key="index">
              <router-link to="/goodsList">{{item}}</router-link>
            </li>
          </ul>
        </div>
        <div class="item-class-content" v-for="(item, index) in peishi.detail" :key="index">
          <div class="item-content-top">
            <div class="item-big-img">
              <router-link target="_blank":to="{path:'/goodsDetail',query:{goodsId:item[0].id}}">
                <img :src="tempfilePath+item[0].picture" alt="" class="subItem">
              </router-link>
            </div>
            <div class="item-four-img">
              <div class="item-four-detail" v-for="(subItem, index) in item.itemFour" :key="index">
                <div class="item-four-detail-text">
                  <p class="pt_bi_tit">{{subItem.goodsname}}</p>
                  <p class="pt_bi_promo">更多优惠，期待多多</p>
                </div>
                <div class="item-four-detail-img">
                  <router-link target="_blank":to="{path:'/goodsDetail',query:{goodsId:subItem.id}}">
                    <img :src="tempfilePath+subItem.picture" alt="" >
                  </router-link>
                </div>
              </div>
            </div>
          </div>
          <div class="item-content-bottom">
            <div class="item-content-bottom-img" v-for="(subImg, index) in item.itemContent" :key="index">
              <router-link to="/goodsList">
                <img :src="subImg" class="subImg">
              </router-link>
            </div>
          </div>
        </div>
      </div>
      <!-- 汉服 -->
      <div class="item-class" id="hanfu">
        <div class="item-class-head item-class-eat-head">
          <span class="item-class-title">汉服</span>
          <ul>
            <li v-for="(item, index) in hanfu.link" :key="index">
              <router-link to="/goodsList">{{item}}</router-link>
            </li>
          </ul>
        </div>
        <div class="item-class-content" v-for="(item, index) in hanfu.detail" :key="index">
          <div class="item-content-top">
            <div class="item-big-img">
              <router-link target="_blank":to="{path:'/goodsDetail',query:{goodsId:item[0].id}}">
                <img :src="tempfilePath+item[0].picture" alt="" class="subItem">
              </router-link>
              <!-- <img :src="item.bigImg" alt=""> -->
            </div>
            <div class="item-four-img">
              <div class="item-four-detail" v-for="(subItem, index) in item.itemFour" :key="index">
                <div class="item-four-detail-text">
                  <p class="pt_bi_tit pt_bi_tit-eat">{{subItem.goodsname}}</p>
                  <p class="pt_bi_promo">缤色彩妆，值得优选</p>
                </div>
                <div class="item-four-detail-img">
                  <router-link target="_blank":to="{path:'/goodsDetail',query:{goodsId:subItem.id}}">
                    <img :src="tempfilePath+subItem.picture" alt="">
                  </router-link>
                </div>
              </div>
            </div>
          </div>
          <div class="item-content-bottom">
            <div class="item-content-bottom-img" v-for="(subImg, index) in item.itemContent" :key="index">
              <router-link to="/goodsList">
                <img :src="subImg" class="subImg">
              </router-link>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import api from '../utils/api'
import Search from '@/components/Search';
import HomeNav from '@/components/nav/HomeNav';
import store from '@/vuex/store';
import { mapState, mapActions, mapGetters, mapMutations } from 'vuex';
export default {
  name: 'Index',
  data(){
    return{
      peishi:{
        link: [ '璎珞', '宫绦', '簪', '发带', '褙子扣', '禁步', '步摇', '玉簪花', '狄髻' ,'荷包','>>>>'],
        detail:[
        ]
      },
      hanfu:{
        link: [ '唐圆领袍', '深衣', '直裾', '道袍', '直身', '直裰', '明圆领袍', '曳撒', '贴里', '罩甲', '短打' ,'>>>>'],
        detail:[

        ]
      },
      tempfilePath:'http://localhost:8089/img/',
      setIntervalObj: null
    }
  },
  created () {
    this.getGoodsList()
    this.getGoodsForty();//加载秒杀的信息

    // this.loadPeishi();//加载配饰信息
    // this.loadHanfu();//加载汉服信息
    // this.loadSeckillsInfo();
    // this.loadCarouselItems();
    // this.loadShoppingCart();
  },
  mounted () {
    const father = this;
    this.setIntervalObj = setInterval(function () {
      father.REDUCE_SECKILLS_TIME();
    }, 1000);
  },
  methods: {
    ...mapActions(['loadSeckillsInfo', 'getGoodsForty','loadPeishi',,'loadHanfu']),
    ...mapMutations(['REDUCE_SECKILLS_TIME']),

    //获取商品信息
    getGoodsList(){
        var vm=this;
        api.getGoodsList().then((res)=>{
          let goodsList=res.extend.goodsList
          let tuanshan=[],yuzan=[],hanfu=[]
          goodsList.forEach(item=>{
            if(item.goodstypeId==3){
              tuanshan.push(item)
            }
            if(item.goodstypeId==4){
              yuzan.push(item)
            }
            if(item.goodstypeId==1){
              hanfu.push(item)
            }
          })
          /**汉服 arr.reverse()*/
          this.hanfu.detail[0]=hanfu.slice(0,1)
          this.hanfu.detail[1]=hanfu.slice(1,2)
          this.hanfu.detail[0].itemFour=hanfu.slice(2,6)
          this.hanfu.detail[1].itemFour=hanfu.slice(6,10)
          this.hanfu.detail[0].itemContent= [
            'static/img/index/hanfu/b1.png',
            'static/img/index/hanfu/b2.png',
            'static/img/index/hanfu/b3.png'
          ]
          this.hanfu.detail[1].itemContent=[
            'static/img/index/hanfu/b4.png',
            'static/img/index/hanfu/b5.png',
            'static/img/index/hanfu/b6.png'
          ]

          /**配饰 */
          this.peishi.detail[0]=tuanshan.slice(0,1)
          this.peishi.detail[0].itemFour=tuanshan.slice(1,5)
          this.peishi.detail[1]=yuzan.slice(0,1)
          this.peishi.detail[1].itemFour=yuzan.slice(1,5)
          this.peishi.detail[0].itemContent=[
            'static/img/index/peishi/b2.png',
            'static/img/index/peishi/b3.png',
            'static/img/index/peishi/b4.png'
          ]
           this.peishi.detail[1].itemContent=[
            'static/img/index/peishi/b5.png',
            'static/img/index/peishi/b6.png',
            'static/img/index/peishi/b7.png'
          ]
          console.log(this.hanfu.detail)
        })
    },




  },
  computed: {
    ...mapState([ 'seckills']),//'peishi',  'hanfu'
    ...mapGetters([ 'seckillsHours', 'seckillsMinutes', 'seckillsSeconds' ])
  },
  components: {
    Search,
    HomeNav
  },
  destroyed () {
    clearInterval(this.setIntervalObj);
  },
  store
};
</script>

<style scoped>
.container1 {
  background-color: #F6F6F6;
  height: 1320px;
}
.content {
  /* width: 1008px; */
  width: 1200px;
  margin: 0px auto;
}
/****************************秒杀专栏开始*****************************/
/*秒杀专栏*/
.seckill {
  width: 100%;
  height: 330px;
  margin: 15px auto;
  background-color: #fff;
}
.seckill-head {
  width: 100%;
  height: 50px;
  background-color: #7a040e;
}
.seckill-icon {
  width: 68px;
  height: 100%;
  float: left;
}
.seckill-icon img {
  width: 35px;
  height: 35px;
  margin-top: 6px;
  margin-left: 15px;
  animation-name: shake-clock;
  animation-duration: 0.3s;
  animation-iteration-count: infinite; /*设置无线循环*/
}
/*定义闹钟震动动画关键帧*/
@keyframes shake-clock {
  0% {
    transform: rotate(-8deg);
  }
  50% {
    transform: rotate(8deg);
  }
  100% {
    transform: rotate(-8deg);
  }
}
.seckill-text {
  width: 300px;
  height: 100%;
  float: left;
}
.seckill-text .seckill-title {
  font-size: 22px;
  line-height: 50px;
  color: #fff;
}
.seckill-text .seckill-remarks {
  margin-left: 5px;
  font-size: 10px;
  color: #fff;
}
/*倒计时*/
.count-down {
  height: 100%;
  margin-right: 30px;
  line-height: 50px;
  float: right;
}
.count-down-text {
  color: #fff;
}
.count-down-num {
  padding: 3px;
  border-radius: 5px;
  background-color: #440106;
  font-size: 26px;
  font-weight: bold;
  color: #f90013;
}
.count-down-point {
  font-size: 26px;
  font-weight: bold;
  color: #440106;
}

.seckill-content {
  width: 100%;
  height: 280px;
}
.seckill-item {
  width: 183px;
  height: 250px;
  margin-top: 15px;
  margin-left: 15px;
  box-shadow: 0px 0px 8px #ccc;
  cursor: pointer;
  float: left;
}
.seckill-item-img {
  width: 160px;
  height: 160px;
  margin: 0px auto;
  overflow: hidden;
  border-bottom: 1px solid #ccc;
  background-color: #fff;
}
.seckill-item-img img {
  width: 130px;
  height: 130px;
  margin-left: 15px;
  margin-top: 15px;
  transition: margin-top 0.3s;
}
.seckill-item-img:hover img {
  margin-top: 6px;
  transition: margin-top 0.3s;
}
.seckill-item-info {
  padding: 5px;
  padding-left: 15px;
  padding-right: 15px;
  font-size: 12px;
  color: #009688;
}
.seckill-item-info i:first-child {
  font-size: 14px;
}
.seckill-price {
  margin-right: 5px;
  font-size: 25px;
  font-weight: bold;
}
/*****************************秒杀专栏结束*****************************/

/*****************************商品专栏开始*****************************/
.item-class {
  width: 100%;
  height: 470px;
  margin-top: 15px;
  background-color: #fff;
}
.item-class-head {
  width: 100%;
  height: 50px;
  border-top: 1px dashed;
  border-bottom: 1px dashed;
  /* background-color: #4488a7; */
  /* background-color: #5f5c5c; */
}
/* .item-class-eat-head {
  background-color: #ecb226;
} */
.item-class-head ul {
  list-style: none;
  float: right;
  margin-right: 30px;
  line-height: 50px;
}
.item-class-head a {
  padding: 6px;
  margin-left: 15px;
  font-size: 12px;
  background-color: #6da6be;
  border: 1px solid #6da6be;
  text-decoration: none;
  color: #fff;
}
.item-class-eat-head a {
  background-color: #eeb955;
  border: 1px solid #eeb955;
}
.item-class-head a:hover {
  border: 1px solid #fff;
}
.item-class-head li {
  float: left;
}
.item-class-title {
  font-size: 25px;
  line-height: 50px;
  /* color: #fff; */
  margin-left: 15px;
}
.item-class-content {
  width: 49%;
  cursor: pointer;
  border-right: 1px solid #ccc;
  margin-left: 0.9%;
  /*background-color: #cff;*/
  float: left;
}
.item-class-content:nth-child(odd) {
  border: none;
}
.item-content-top {
  width: 100%;
  height: 260px;
}
.item-big-img {
  /* width: 180px; */
  width: 200px;
  height: 260px;
  overflow: hidden;
  float: left;
}
.subItem{
    width: 200px;
    height: 250px;
}
.item-big-img img {
  margin-left: 0px;
  transition: margin-left 0.3s;
}
.item-big-img:hover img {
  margin-left: -15px;
  transition: margin-left 0.3s;
}
.item-four-img {
  /* width: 370px; */
  margin-left: 8px;
  /* float: left; */
  font-size: 12px;
}
.item-four-detail img {
  margin-left: 0px;
  transition: margin-left 0.3s;
}
.item-four-detail:hover img {
  margin-left: -6px;
  transition: margin-left 0.3s;
}
.item-four-detail {
  /* width: 152px; */
  width: 185px;
  height: 130px;
  margin-left: -1px;
  float: left;
}
.item-four-detail:first-child {
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}
.item-four-detail:last-child {
  border-top: 1px solid #ccc;
  border-left: 1px solid #ccc;
}
.item-four-detail-text {
  width: 50px;
  margin-left: 5px;
  margin-top: 15px;
  float: left;
}
.item-four-detail-img {
  /* width: 96px; */
  margin-top: 15px;
  overflow: hidden;
  float: left;
}
.item-four-detail-img img {
  margin-left: 5px;
  width: 90px;
}
.pt_bi_tit {
  font-weight: bold;
  font-size: 12px;
  color: #4488a7;
}
.pt_bi_tit-eat {
  color: #ecb127;
}
.pt_bi_promo {
  color: #00695c;
}
.item-content-bottom {
  /* width: 100%; */
  width: 587px;
  height: 120px;
  margin-top: 8px;
}
.item-content-bottom-img {
  /* width: 156px;
  margin-right: 8px; */

  width: 170px;
  margin-right: 25px;
  overflow: hidden;
  float: left;
}
.item-content-bottom-img img {
  margin-left: 0px;
  transition: margin-left 0.3s;
}
.item-content-bottom-img:hover img {
  margin-left: -15px;
  transition: margin-left 0.3s;
}
.subImg{
  width:170px;
  height: 100px;
}
/*****************************商品专栏结束*****************************/
</style>
