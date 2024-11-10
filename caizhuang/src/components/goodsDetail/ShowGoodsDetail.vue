<template>
  <div>
    <div class="item-intro-show">
      <div class="item-intro-recommend">
        <div class="item-recommend-title">
          <p>店铺热销</p>
        </div>
        <div class="item-intro-recommend-column">
          <div class="item-recommend-column"  v-for="(item, index) in hotList" :key="index">
            <router-link target="_blank" :to="{path:'/goodsDetail',query:{goodsId:item.id}}">
            <div class="item-recommend-img">
              <img :src="tempfilePath+item.picture" alt="">
            </div>
            <div class="item-recommend-intro">
              <span>
                <span class="item-recommend-top-num">{{index + 1}}</span> 热销{{item.soldNum}}件</span>
              <span class="item-recommend-price">￥{{item.sellPrice}}</span>
            </div>
            </router-link>
          </div>
        </div>
      </div>

      <div class="item-intro-detail" ref="itemIntroDetail">
        <div class="item-intro-nav item-tabs">
          <Tabs ref="tabs" >
            <TabPane label="商品介绍"  style="height:900px;overflow-y:auto;overflow-x:hidden;">
              <div class="remarks-title">
                <span>商品介绍:</span>
              </div>
               <p class="desc-p text">包邮 收藏加购可以优先发货</p>
              <p class="desc-p">{{goodsDetail.description}}</p>


              <div class="item-intro-img" ref="itemIntroGoods">
                <img v-if="goodsDetail.picture!=undefined" :src="tempfilePath+goodsDetail.picture" alt="">
              </div>

            </TabPane>
            <TabPane label="规格参数">
              <div class="remarks-title">
                <span>规格参数</span>
              </div>

              <div class="item-param-container">

                <span class="item-param-box">
                  <span class="item-param-title">商品名称:</span>
                  <span class="item-param-content">{{goodsDetail.goodsname}}</span>
                </span>
                 <span class="item-param-box">
                  <span class="item-param-title">商品产地:</span>
                  <span class="item-param-content">{{goodsDetail.producingArea}}</span>
                </span>


                <span class="item-param-box" v-for="(item,index) in GoodsSize" :key="index">
                  <span class="item-param-title">{{item.title}}: </span>
                  <span class="item-param-content">{{item.content}}</span>
                </span>
              </div>
            </TabPane>
            <TabPane label="售后保障">
              <ShowProductWarranty></ShowProductWarranty>
            </TabPane>
            <TabPane label="商品评价" style="height:800px;overflow-y:auto;overflow-x:hidden;">
              <div class="remarks-container">
                <div class="remarks-title">
                  <span>商品评价</span>
                </div>

                <div class="remarks-analyse-box" v-if="sview.goodsRating">
                  <div class="remarks-analyse-goods">
                    <i-circle :percent="sview.goodsRating" stroke-color="#e4393c">
                      <span class="remarks-analyse-num">{{sview.goodsRating}}%</span>
                      <p class="remarks-analyse-title">好评率</p>
                    </i-circle>
                  </div>
                  <div class="remarks-analyse-tags">
                    <Tag checkable :color="tagsColor[index % 4]" v-for="(item,index) in remarks.remarksTags" :key="index">{{item}}</Tag>
                  </div>
                </div>


                <div class="remarks-bar">
                  <el-tabs v-model="activeName"class="tabs">
                    <el-tab-pane name="first">
                      <span slot="label">所有评价<span style="color:#3c3c3c">({{sview.totalLength}})</span></span>
                      <show-review c="all" ref="sview"></show-review>
                    </el-tab-pane>

                    <el-tab-pane  name="two">
                       <span slot="label">好评<span style="color:#3c3c3c">({{sview.goodLenght}})</span></span>
                      <show-review c="0"></show-review>
                    </el-tab-pane>

                    <el-tab-pane  name="second">
                      <span slot="label">中评<span style="color:#3c3c3c">({{sview.middleLength}})</span></span>
                      <show-review c="1"></show-review>
                    </el-tab-pane>


                    <el-tab-pane name="six">
                       <span slot="label">差评<span style="color:#3c3c3c">({{sview.lowLength}})</span></span>
                       <show-review c="2"></show-review>
                    </el-tab-pane>
                  </el-tabs>
                </div>

              </div>

            </TabPane>
          </Tabs>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ShowReview from '@/components/goodsDetail/ShowReview';
import ShowProductWarranty from '@/components/goodsDetail/ShowProductWarranty';
import store from '@/vuex/store';
import { mapState } from 'vuex';
import { sendServer } from '../../utils/common';
export default {
  name: 'ShowGoodsDetail',
  components: {
    ShowProductWarranty,
    ShowReview
  },
  data () {
    return {
      sview:{},
      activeName:'first',
      tempfilePath:'http://localhost:8089/img/',
      hotGoodsList:'',//this.$store.state.goodsListforForty || '',
      GoodsSize:[
       { title: '适用人群',content: '通用'},
        {title:'店铺',content:'缤色彩妆'},
      ],
      remarks: {
          remarksTags: [ '颜色多样', '实惠优选', '不错，是正品', '质量没话说', '国货优选', '完美品质', '正品正品', '包装有档次',],
          remarksNumDetail: [ 2000, 3000, 900, 1 ],

     },
      tagsColor: [ 'blue', 'green', 'red', 'yellow' ]
    };
  },
  computed: {
    ...mapState(['hotList','goodsDetail'])
  },
  created(){
    this.$store.dispatch('getGoodsForty')
  },
  mounted(){
    this.sview=this.$refs.sview
    console.log(this.sview)
  },
  methods: {

  },
};
</script>

<style scoped>
/***************商品详情介绍和推荐侧边栏开始***************/
.item-intro-show{
  /* width: 65%; */
  width:1000px;
  margin: 15px auto;
  display: flex;
  flex-direction: row;
  background-color: #fff;
}
.item-intro-recommend{
  /* width:280px; */
  width: 180px;
  display: flex;
  flex-direction: column;
}
.item-intro-recommend-column{
  display: flex;
  flex-direction: column;
  box-shadow: 0px 0px 5px #999;
}
.item-recommend-title{
  width: 100%;
  height: 38px;
  font-size: 16px;
  line-height: 38px;
  color: #fff;
  background-color: #E4393C;
  box-shadow: 0px 0px 5px #E4393C;
  text-align: center;
}
.item-recommend-column{
  margin-top: 15px;
}
.item-recommend-intro{
  padding: 5px 15px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  font-size: 12px;
  color: #999;
  cursor: pointer;
}
.item-recommend-img{
  width: 80%;
  margin: 0px auto;
  cursor: pointer;
}
.item-recommend-img img{
  width: 100%;
}
.item-recommend-top-num{
  color: #fff;
  margin: 0px 2px;
  padding: 1px 5px;
  border-radius: 12px;
  background-color: #E4393C;
}
.item-recommend-price{
  color: #E4393C;
  font-weight: bolder;
}
.item-intro-detail{
  margin-left: 30px;
  /* width:800px; */
  /* width:700px; */
  width: calc(1000px - 220px);
}
.item-intro-nav{
  width: 100%;
  height: 38px;
  background-color: #F7F7F7;
  border-bottom: 1px solid #E4393C;
}
.item-intro-nav ul{
  margin: 0px;
  padding: 0px;
  list-style: none;
}
.item-intro-nav li{
  float: left;
  height: 100%;
  width: 120px;
  line-height: 38px;
  text-align: center;
  color: #E4393C;
}
.item-intro-nav li:first-child{
  background-color: #E4393C;
  color: #fff;
}
.item-intro-img{
  width: 100%;
  max-width:100%;
  height: auto;

  /* max-width: 730px; */

}
.item-intro-img img{
  width: 100%;
  max-width:100%;


  /* max-width: 800px; */
}
/************* 商品参数 *************/
.item-param-container {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: space-between;
}
.item-param-box {
  padding: 5px;
  padding-left: 30px;
  width: 240px;
  height: 36px;
  font-size: 14px;
  /* text-align: center; */
  /* background-color: #ccc; */
}
.item-param-title {
  color: #232323;
}
.item-param-content {
  color: #999;
}
.remarks-title {
  padding-left: 15px;
  height: 36px;
  font-size: 16px;
  font-weight: bolder;
  line-height: 36px;
  color: #666666;
  background-color: #F7F7F7;
}
.remarks-analyse-box {
  padding: 15px;
  display: flex;
  align-items: center;
}
.remarks-analyse-goods {
  margin-left: 15px;
  margin-right: 15px;
}
.remarks-analyse-num {
  font-size: 26px;
}
.remarks-analyse-title {
  font-size: 12px;
  line-height: 20px;
}
.remarks-bar {
  padding-left: 15px;
  height: 36px;
  line-height: 36px;
  color: #666666;
  background-color: #F7F7F7;
}
.remarks-bar span {
  margin-right: 15px;
}
.remarks-box {
  padding: 15px;
  display: flex;
  flex-direction: row;
  border-bottom: 1px #ccc dotted;
}
.remarks-user {
  width: 180px;
}
.remarks-user-name {
  padding-left: 15px;
}
.remarks-content-box {
  width: calc(100% - 180px);
}
.remarks-star {
  background-color: #fff;
}
.remarks-content {
  font-size: 14px;
  color: #232323;
  line-height: 28px;
}
.remarks-sub {
  margin-top: 15px;
  color: #ccc;
}
.remarks-time {
  margin-left: 15px;
}
.remarks-page {
  margin: 15px;
  display: flex;
  justify-content:flex-end;
}
/***************商品详情介绍和推荐侧边栏结束***************/
</style>

<style>
/* 改变便签页样式 */
.el-tabs__item:hover {
  color: #f40;

}
.el-tabs__item.is-active {
    color: #f40;

}
.el-tabs__active-bar {
    position: absolute;
    bottom: 0;
    left: 0;
    height: 2px;
    background-color: #f40;
    z-index: 1;
}
.ivu-tabs-ink-bar {
  background-color: #E4393C !important;
}
.item-tabs > .ivu-tabs > .ivu-tabs-bar .ivu-tabs-tab{
  border-radius: 0px;
  color: #999;
  height: 38px;
  background: #F7F7F7;
}
.item-tabs > .ivu-tabs > .ivu-tabs-bar .ivu-tabs-tab-active{
  color: #fff;
  background-color: #E4393C;
}
.item-tabs > .ivu-tabs > .ivu-tabs-bar .ivu-tabs-tab-active:before{
  content: '';
  display: block;
  width: 100%;
  height: 1px;
  color: #fff;
  background: #F7F7F7;
  position: absolute;
  top: 0;
  left: 0;
}
.ivu-rate-star-full:before, .ivu-rate-star-half .ivu-rate-star-content:before {
  color: #E4393C;
}
.desc-p{
  margin-top: 2px;
  margin-bottom: 5px;
  font-size: 15px;
  margin-left: 15px;
}
.text{
  color: #E4393C;
  font-weight: 600;

}
</style>
