<template>
  <div class="searchContainer">
    <BackTop></BackTop>
   <Affix>
			 <Search  ref="search" @chooseName='getsearchGoods'></Search>
    </Affix>
    <GoodsListNav ref="navList" @chooseType='initData' ></GoodsListNav>
    <!-- getsearchGoods -->
    <div class="container-goodsList-box">
      <div class="bread-crumb">
        <Breadcrumb>
          <BreadcrumbItem to="/">
            <Icon type="ios-home-outline"></Icon> 首页
          </BreadcrumbItem>
          <BreadcrumbItem to="/goodsList?searchData=">
            <Icon type="bag"></Icon> {{searchItem}}
          </BreadcrumbItem>
        </Breadcrumb>
      </div>
      <!-- 商品标签导航 -->
      <GoodsClassNav @chooseName='getsearchGoods' ref="classNav"></GoodsClassNav>
      <!-- 商品展示容器 -->
      <div class="goods-box">

        <div class="goods-list-box2">
          <div class="goods-list-tool">
            <ul>
              <li v-for="(item,index) in goodsTool" :key="index" @click="orderBy(item.en, index)"><span :class="{ 'goods-list-tool-active': isAction[index]}">{{item.title}} <Icon :type="icon[index]"></Icon></span></li>
            </ul>
            <Form ref="priceForm" :model="priceForm"  inline class="form">
              <FormItem >
                  <Input type="text" v-model="priceForm.minPrice" placeholder="￥" class="formItem" >

                  </Input>
              </FormItem>
               <!-- - -->
              <FormItem>
                  <Input type="text" v-model="priceForm.maxPrice" placeholder="￥"class="formItem" >

                  </Input>
              </FormItem>

              <FormItem>
                  <Button type="primary" @click="getsearchGoods">确定</Button>

              </FormItem>
              <FormItem>
                <Button type="primary" @click="reset('priceForm')">重置</Button>
              </FormItem>



            </Form>
          </div>
                    <div class="goods-list">
                      <div class="goods-show-info" v-for="(item, index) in list" :key="index">
                        <div class="goods-show-img">
                          <router-link target="_blank" :to="{path:'/goodsDetail',query:{goodsId:item.id}}"><img :src="temfielPath+item.picture" class="goods-img"/></router-link>
                        </div>

                        <div class="goods-show-seller">
                          <span >{{item.goodsname}}</span>
                        </div>
                        <div class="goods-show-detail">
                          <span>{{item.description}}</span>
                        </div>

                        <div class="goods-show-price">
                          <span>
          <!--                  <Icon type="social-yen text-danger"></Icon>-->
                            <span >{{item.price}}元</span>
                          </span>
                        </div>
                        <div class="goods-show-num">
                          <div>已有<span>{{item.remark}}</span>人评价</div>
                          <div>已售出<span>{{item.soldNum}}</span>件</div>
                        </div>

                      </div>
                    </div>
           <div class="goods-list">
            <div class="v-goods" v-if="list.length==0">
             <p class="no-goods">该品牌下暂无商品</p>
            </div>
          </div>
        </div>

<!--        <div class="as-box">-->
<!--          <div class="item-as-title">-->
<!--            <span>商品精选</span>-->
<!--            <span>广告</span>-->
<!--          </div>-->
<!--          <div class="item-as" v-for="(item,index) in goodsListforForty" :key="index">-->
<!--            <router-link target="_blank" :to="{path:'/goodsDetail',query:{goodsId:item.id}}":key="item.id">-->
<!--         -->
<!--              <div class="item-as-img">-->
<!--                <img :src="temfielPath+item.picture" alt="" class="boxGoodsImg"/>-->
<!--              </div>-->
<!--              <div class="item-as-price">-->
<!--                <span>-->
<!--                  <Icon type="social-yen text-danger"></Icon>-->
<!--                  <span class="seckill-price text-danger">{{item.price}}</span>-->
<!--                </span>-->
<!--              </div>-->
<!--              <div class="item-as-intro">-->
<!--                <span>{{item.description}}</span>-->
<!--              </div>-->
<!--              <div class="item-as-selled">-->
<!--                已有<span>{{item.remark}}</span>人评价-->
<!--              </div>-->
<!--            </router-link>-->
<!--          </div>-->
<!--        </div>-->
      </div>
      <div class="goods-page">
        <Page v-show="total>0" :total="total" :page-size.sync="listQuery.limit"  :current.sync="listQuery.page"
          @on-change="goNext" show-elevator show-total placement="bottom" show-sizer @on-page-size-change="getSizeNext"/>
      </div>
    </div>
    <Spin size="large" fix v-if="isLoading"></Spin>
  </div>
</template>

<script>
import Qs from 'qs';
import Search from '@/components/Search';
import GoodsListNav from '@/components/nav/GoodsListNav';
import GoodsClassNav from '@/components/nav/GoodsClassNav';
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
      indexId:this.$route.query.id,//从首页传过来的分类参数
      temfielPath:'http://localhost:8089/img/',
      searchItem: '',
      list:'',
      total:0,
      listQuery: {
        page: 1,
        limit: 28,
      },
      chooseId:'',
      priceForm:{
        maxPrice:'',
        minPrice:''
      },

      isAction: [ true, false, false ],
      icon: [ 'arrow-up-a', 'arrow-down-a', 'arrow-down-a' ],
      goodsTool: [
        {title: '综合', en: 'sale'},
        {title: '评论数', en: 'remarks'},
        {title: '价格', en: 'price'}
      ]
    };
  },
  computed: {

    ...mapState(['asItems', 'isLoading','goodsListforForty','searchName']),

    //...mapGetters(['orderGoodsList'])
  },
  methods: {
    goNext(data){
      console.log(data)
      this.listQuery.page=data
      this.getsearchGoods();
    },
    getSizeNext(data){
      console.log(data)
      this.listQuery.limit=data
      this.getsearchGoods();
    },
    // this.$refs.search.goSearch=function(){

    // },
    reset(){
      this.priceForm.minPrice = ""
      this.priceForm.maxPrice = ""
      // let maxPrice=this.priceForm.maxPrice;
      // let minPrice=this.priceForm.minPrice;
      this.getsearchGoods()


    },
    initData(){
      this.searchItem='';
      let searchname=this.$store.state.searchName;
      console.log(searchname)
      if(searchname){
        this.$store.state.searchName='';
        console.log(this.$refs.classNav.choosedName)
        this.$refs.classNav.choosedName='';

      }
      this.getsearchGoods();

    },
    getsearchGoods(){
      let vm=this;
      let searchname=this.$store.state.searchName;
      let maxPrice=this.priceForm.maxPrice;
      let minPrice=this.priceForm.minPrice;
      let typeId=this.$store.state.typeId || this.indexId;//如果首页传值了 就用首页的值来胖墩这个分类参数，同时改变选中状态
      if(this.indexId){
        this.$refs.navList.chooseId=this.indexId;
      }
      console.log("typeId的值是什么的呢哈哈圣诞节暗室逢灯"+typeId)
      let send={}
      send.pageNum=this.listQuery.page,
      send.pageSize=this.listQuery.limit;
      send.typeId=typeId,
      send.searchname=searchname,
      send.maxPrice=maxPrice,
      send.minPrice=minPrice;


      if(typeId=='all'){
        typeId='',
        send.typeId=typeId
      }
      console.log(send)

      // let searchname=this.$refs.search.searchData

      this.axios({
        method:'POST',
        url:'http://localhost:8089/hf/goods/getsearchGoods',
        // ?pageNum=1&pageSize=40&searchname=&typeId=&maxPrice=&minPrice=
        transformRequest:[function(data){
          return Qs.stringify(data)
        }],
        data:send,
        // data:{
        //   pageNum:1,
        //   pageSize:28,
        //   searchname:searchname,
        //   typeId:typeId,
        //   maxPrice:maxPrice,
        //   minPrice:minPrice

        // }
      }).then(function(res){
        console.log(res.data)
        if(vm.$store.state.searchName){
           vm.searchItem = vm.$store.state.searchName
          //  if(vm.searchItem){
          //    vm.$refs.search.searchData=''
          //  }
        }

        vm.list=res.data.extend.pageInfo.list
        vm.total=res.data.extend.pageInfo.total
      });

    },

    //...mapActions(['loadGoodsList']),//,'getGoodsForty'
    //...mapMutations(['SET_GOODS_ORDER_BY']),//,'SET_GOODSFORTY_INFO'
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
     console.log('路由进去会执行吗')
    // this.searchItem = this.$route.query.searchData;
    // console.log(this.searchItem)=====>>>>这一步是可以打印出来的
    this.$nextTick(() => {
      // this.$refs.search.searchData=this.searchItem;
      // console.log(this.$refs.search.searchData)
      // 在此处执行你要执行的函数
      // console.log(this.$refs.search)
      this.getsearchGoods();
      console.log(this.$store.state.typeId)


    });





    // this.loadGoodsList();
    this.$store.dispatch('getGoodsForty')
    // console.log(this.$store.state.userInfo.username)
    //搜索查询

  },
  mounted () {
    console.log('路由进去会执行吗')
    console.log(this.$store.state.searchName)
    this.searchItem = this.$store.state.searchName
    // this.$route.query.searchData;
    this.$refs.search.searchData=this.$store.state.searchName
    console.log(this.$refs.search.searchData)
    // console.log(this.$refs.search)

  },
  //  watch: {
  //   $route: "",

  // },

  components: {
    Search,
    GoodsListNav,
    GoodsClassNav
  },
  store
};
</script>

<style scoped>
.searchContainer{
  margin:0 auto;
  width:1200px;

}
.container-goodsList-box {
  margin: 15px auto;
  /* width: 93%; */
  width:100%;
  min-width: 1000px;
}
.text-danger {
  color: #A94442;
}
.seckill-price{
  margin-right: 5px;
  font-size: 25px;
  font-weight: bold;
}
.goods-box {
  display: flex;
}
/* ---------------侧边广告栏开始------------------- */
.as-box {
  margin-left: 15px;
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
.goods-list-box2 {
  /* margin-left: 15px; */
  width: calc(100% );
}
.goods-list-tool{
  width: 100%;
  height: 38px;
  border: 1px solid #ccc;
  background-color: #F1F1F1;
}
.goods-list-tool ul{
  padding-left: 15px;
  list-style: none;
}
.goods-list-tool li{
  cursor: pointer;
  float: left;
}
.goods-list-tool span{
  padding: 5px 8px;
  border: 1px solid #ccc;
  border-left: none;
  line-height: 36px;
  background-color: #fff;
}
.goods-list-tool span:hover{
  border: 1px solid #E4393C;
}
.goods-list-tool i:hover{
  color: #E4393C;
}
.goods-list-tool-active {
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
  font-size: 15px;
  color:#E4393C;
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
  font-size:20px;
  color:#black;
}
.goods-page {
  margin-top: 20px;
  display: flex;
  /* justify-content: flex-end; */
}
.goods-page ul{
  margin: 0 auto;

}
.boxGoodsImg{
  width: 160px;
  height: 160px;
}
.goods-img{
    width: 218px;
    height: 218px;
}
.no-goods{
  margin: 50px;

  font-size: 20px;
}
.v-goods{
  margin: 0 auto;
}
.form{
  margin-left: 200px;
}
.ivu-form-item {
    margin-bottom: 24px;
    vertical-align: top;
    zoom: 1;
    width: 80px;
}
.ivu-btn-primary {
    color: #fff;
    height: 25px;
    background-color: #a73333;
    border-color: #a73333;
}
.formItem >>>.ivu-input{
  height: 25px;
}
.formItem >>>.ivu-input:hover {
    border-color: #19191a;
}
.formItem >>>.ivu-input:focus {
    border-color: #19191a;
}
.item-as a{
  color: #495060;
}

/* ---------------商品栏结束------------------- */
</style>
