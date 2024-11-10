<template>
  <div>
    <div class="item-detail-show">
      <div class="item-detail-left">
        <div class="item-detail-big-img">
          <!-- <img :src="tempfilePath+goodsInfo.picture" alt=""> -->
           <pic-zoom :url="tempfilePath+goodsInfo.picture" ></pic-zoom>
        </div>
        <div class="item-detail-like">
          <div v-if="islike==true" @click="Collection"><Icon type="ios-heart-outline" />收藏商品</div>
          <div v-if="islike==false" @click="Collection"><Icon type="ios-heart" />已收藏</div>
        </div>
        <!-- <div class="item-detail-img-row">
          <div class="item-detail-img-small" v-for="(item, index) in goodsInfo1.goodsImg" :key="index" @mouseover="showBigImg(index)">
            <img :src="tempfilePath+item" alt="">
          </div>
        </div> -->
      </div>
      <div class="item-detail-right">
        <div class="item-detail-title">
          <p>
            <span class="item-detail-express">顺丰配送</span> {{goodsInfo.goodsname}}</p>
        </div>
        <div class="item-detail-tag">
          <p>
            <span v-for="(item,index) in goodsInfo.tags" :key="index">【{{item}}】</span>
          </p>
        </div>
        <div class="item-detail-price-row">
          <div class="item-price-left">

            <div class="item-price-row">
              <p>
                <span class="item-oldprice-title">原价</span>
                <span class="item-oldprice"><del>￥{{goodsInfo.price}}</del></span>
              </p>
            </div>

            <div class="item-price-row">
              <p>
                <span class="item-price-title">折扣 价</span>
                <span class="item-price">￥{{goodsInfo.sellPrice}}</span>
              </p>
            </div>
            <div class="item-price-row">
              <p>
                <span class="item-price-title">优 惠 价</span>
                <span class="item-price-full-cut" v-for="(item,index) in goodsInfo1.discount" :key="index">{{item}}</span>
              </p>
            </div>
            <div class="item-price-row">
              <p>
                <span class="item-price-title">促&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;销</span>
                <span class="item-price-full-cut" v-for="(item,index) in goodsInfo1.promotion" :key="index">{{item}}</span>
              </p>
            </div>
          </div>
          <div class="item-price-right">
            <div class="item-remarks-sum">
              <div class="sold">已售出<a>{{goodsInfo.soldNum}}</a> 件</div>
              <div class="remark">累计评价<a>{{goodsInfo.remark}} </a>条</div>
            </div>
          </div>
        </div>
        <div :class="[addStatus==0?'':'form-cart']" >
          <!-- 运费 -->
          <div class="item-select">
            <div class="item-select-title">
              <p>运费</p>
            </div>
            <div>
              <p>{{goodsInfo.producingArea}} 到杭州 快递：0.0元</p>
            </div>
          </div>
          <!-- 尺码 -->
          <div class="item-select">
            <div class="item-select-title">
              <p>选择类型</p>
            </div>
            <div class="item-select-row">
              <div :class="[choosedSize==item.type?'item-selected':'item-select-class']"  v-for="(item,index) in chima" :key="index" @click="choseSize"
               :data-size="item.type">
                <Tooltip :content="item.tooltip" placement="top-start">
                  <span>{{item.type}}</span>
                </Tooltip>
              </div>
            </div>
          </div>
          <!-- 数量 -->
          <div class="item-select">
            <div class="item-select-title">
              <p>数量</p>
            </div>
            <div class="add-buy-car-box">
              <div class="add-buy-car">
                <InputNumber :min="1" v-model="count" size="large"></InputNumber>
              <!-- <Button type="error" size="large" @click="addShoppingCartBtn()">加入购物车</Button> -->
              </div>
            </div>
            <div>
              <p>库存：{{goodsInfo.stock}}件</p>
            </div>
          </div>
        </div>

        <!-- 白条分期 -->
        <div class="item-select">
          <div class="item-select-title">
            <p>花   呗</p>
          </div>
          <div class="item-select-row">
            <div :class="[choosedPay==item.type?'item-selected':'item-select-class']" v-for="(item,index) in hirePurchase"
            :key="index"  @click="chosePay"  :data-pay="item.type">
              <Tooltip :content="item.tooltip" placement="top-start">
                <span>{{item.type}}</span>
              </Tooltip>
            </div>
          </div>
        </div>

        <!-- 购物车 -->
        <div class="item-select">
          <div class="box-cart cart" @click="addToCart">加入购物车</div>
          <div class="box-cart shop" @click="goToPay">立即购买</div>


        </div>
        <!-- <div class="add-buy-car-box">
          <div class="add-buy-car">
            <InputNumber :min="1" v-model="count" size="large"></InputNumber>
            <Button type="error" size="large" @click="addShoppingCartBtn()">加入购物车</Button>
          </div>
        </div> -->
         <br>
      </div>
    </div>
  </div>
</template>

<script>
import PicZoom from 'vue-piczoom';
import store from '@/vuex/store';
import { mapState, mapActions } from 'vuex';
import { sendServer } from '../../utils/common';
export default {
  name: 'ShowGoods',
  components: {
    PicZoom
  },
  data () {
    return {
      addStatus:0,//为1的时候 表示提交表单 存在尺码未选，数量未选的状态
      islike:true,
      tempfilePath:'http://localhost:8089/img/',
      goodsInfo:[],
      goodsInfo1:{
        tags: ['满69-20元', '关注产品★送小样', 'BIT配次日达'],
        discount: ['满148减10', '满218减20', '满288减30'],
        promotion: ['跨店满减', '多买优惠'],
      },
      chima:[{type:'基本款',tooltip:'日常通勤'},{type:'定制款',tooltip:'个性选择'},{type:'礼盒装',tooltip:'送礼优选'},{type:'其他类型',tooltip:'更多选择'}],
      choosedSize:'',//尺码
      choosedPay:'',//分期
      count: 1,//购买数量

      // price: 0,
      selectBoxIndex: 0,
      imgIndex: 0
    };
  },
  created(){
    this.getGoodsById();
    this.getIsLike();
    // console.log(this.$route.query.goodsId)
    // this.$store.dispatch('getAllCollection')
  },
  computed: {
    ...mapState(['collection']),
    hirePurchase () {
      const three = this.goodsInfo.sellPrice * this.count / 3;
      const sex = this.goodsInfo.sellPrice * this.count / 6;
      const twelve = this.goodsInfo.sellPrice * this.count / 12 * 1.0025;
      const twentyFour = this.goodsInfo.sellPrice * this.count / 24 * 1.005;
      return [
        {
          tooltip: '无手续费',
          type: '不分期'
        },
        {
          tooltip: '无手续费',
          type: `￥${three.toFixed(2)} x 3期`
        },
        {
          tooltip: '无手续费',
          type: `￥${sex.toFixed(2)} x 6期`
        },
        {
          tooltip: '含手续费：费率0.25%起，￥0.1起×12期',
          type: `￥${twelve.toFixed(2)} x 12期`
        },
        {
          tooltip: '含手续费：费率0.5%起，￥0.1起×12期',
          type: `￥${twentyFour.toFixed(2)} x 24期`
        }
      ];
    }
  },
  methods: {
      //   "userId":1,
      //  "goodsId":2,
      //  "quantity":9，
      //   "size":""
    //添加商品到购物车
    addToCart(){
      let vm=this;
      let userId=this.$store.state.userInfo.id;
      // if(userId){
        let count=this.count;
        let size=this.choosedSize;
        let params={},send={}
        send.userId=userId;
        send.goodsId=this.$route.query.goodsId;
        if(size == null || size==''){
          this.addStatus=1
          this.$Message.error("未选择类型！！")
          return false;
        }
        send.quantity=count
        send.size=size
        params.send=send;
        console.log(send)
        params.url="cart/addCart";
        sendServer(params,(commRes)=>{
          console.log(commRes)
          if(commRes.code==100){
            vm.$Message.success('商品已成功加入购物车！')
            // vm.$router.push('/shoppingCart');
            let img=vm.goodsInfo.picture
            let desc=vm.goodsInfo.description
            send.img=img;
            send.desc=desc;
            console.log(send)
            vm.$router.push({
              path:'/shoppingCart',
              query:{
                cartInfo:JSON.stringify(send)
              }
            })

          }
        })

      // }else{
      //   this.$Message.error('请先登录，谢谢')
      //   this.$router.push('/login')

      // }
    },
     //立即购买，去结账页面，并把参数传过去，可以把参数存在本地 然后在结账页面去获取
    goToPay(){
      let listItem = []
      let count=this.count;
      let size=this.choosedSize;
      if(size == null || size==''){
        this.addStatus=1
        this.$Message.error("未选择类型！！")
        return false;
      }

      let goodsId=this.$route.query.goodsId;
      let img=this.goodsInfo.picture
      let description=this.goodsInfo.description
      let sellPrice=this.goodsInfo.sellPrice
      listItem[0]={};
      console.log(count ,size)
      listItem[0].quantity=count;
      listItem[0].size=size;
      listItem[0].goods={};
      listItem[0].goods.sellPrice=sellPrice
      listItem[0].goods.picture=img
      listItem[0].goods.description=description
      listItem[0].goodsId=goodsId;
      console.log(listItem)
      let allPrice=count*sellPrice;



      this.$router.push({
          path:'/order',
          query:{
            listItem:JSON.stringify(listItem),
            totalPrice:allPrice,
            isDetail:0,
          }
      })
    },

    chosePay(options){
      let pay=options.srcElement.innerText
      this.choosedPay=pay;
    },
    //选择类型
    choseSize(options){
      let size=options.srcElement.innerText
      console.log(size)
      this.choosedSize=size;
      this.addStatus=0//选择类型之后，就选中的状态更新，即可以提交到购物车，或者直接去订单界面结算。红色的框用来强调。
    },

    //获取商品详情
    getGoodsById(){
      let params={},send={}
      send.id=this.$route.query.goodsId;
      params.send=send;
      params.url="goods/getProductById";
      console.log(params)
      sendServer(params,(commRes)=>{
        console.log(commRes)
        if(commRes.code==100){
          this.goodsInfo=commRes.extend.goods
          this.$store.dispatch('getGoodsDetail',commRes.extend.goods)
          console.log(this.$store.state.goodsDetail)
        }
      })
    },
    //判断是否收藏
    getIsLike(){
      let userId=this.$store.state.userInfo.id;
      if(userId){
        let params={},send={}
        send.goodsId=this.$route.query.goodsId;
        send.userId=userId;
        params.send=send;
        params.url="like/isLiked"
        sendServer(params,(commRes)=>{
          if(commRes.code==100){
            console.log(commRes)
            console.log(commRes.extend.isLike)
            if(commRes.extend.isLike==0){
              this.islike=true
            }else if(commRes.extend.isLike==1){
              this.islike=false
              console.log(this.islike)
            }
            // this.islike=commRes.extend.islike
          }
        })

      }


    },
    //收藏商品
    Collection(){
      console.log('执行了吗collection')
      let vm=this;
      let userId=this.$store.state.userInfo.id;
      if(userId){
        let params={},send={}
        send.userId=userId;
        send.goodsId=this.$route.query.goodsId;
        params.send=send;
        params.url="like/addLike"
        if(this.islike){
          sendServer(params,(commRes)=>{
            console.log('isllike为true')
            if(commRes.code==100){
              vm.islike=false;
              vm.$Message.success('收藏成功');
            }
          });
        }else{
           sendServer(params,(commRes)=>{
            console.log('islike false')
            if(commRes.code==100){
              vm.islike=true;
              vm.$Message.success('取消收藏');
            }
          });

        }


      }else{
        this.$Message.error('请先登录，谢谢')
        this.$router.push('/login')

      }



    },
    // ...mapActions(['addShoppingCart']),
    select (index1, index2) {
      this.selectBoxIndex = index1 * 3 + index2;
      this.price = this.goodsInfo.setMeal[index1][index2].price;
    },
    // showBigImg (index) {
    //   this.imgIndex = index;
    // },
    addShoppingCartBtn () {
      const index1 = parseInt(this.selectBoxIndex / 3);
      const index2 = this.selectBoxIndex % 3;
      const date = new Date();
      const goodsId = date.getTime();
      const data = {
        goods_id: goodsId,
        title: this.goodsInfo.title,
        count: this.count,
        package: this.goodsInfo.setMeal[index1][index2]
      };
      this.addShoppingCart(data);
      this.$router.push('/shoppingCart');
    }
  },
  mounted () {
    // const father = this;
    // setTimeout(() => {
    //   father.price = father.goodsInfo.setMeal[0][0].price || 0;
    // }, 300);
  },
  store
};
</script>

<style scoped>
/******************商品图片及购买详情开始******************/
.item-detail-show {
  /* width: 65%; */
  /* width:80%; */
  width:1000px;
  margin: 15px auto;
  display: flex;
  flex-direction: row;
  background-color: #fff;
}
.item-detail-left {
  width: 350px;
  margin-right: 30px;
}
.item-detail-big-img {
  width: 350px;
  height: 350px;
  box-shadow: 0px 0px 8px #ccc;
  cursor: pointer;
}
.item-detail-big-img img {
  width: 100%;
}
.item-detail-img-row {
  margin-top: 15px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.item-detail-img-small {
  width: 68px;
  height: 68px;
  box-shadow: 0px 0px 8px #ccc;
  cursor: pointer;
}
.item-detail-img-small img {
  width: 100%;
}
/*商品选购详情*/
.item-detail-right {
  width:750px;
  display: flex;
  flex-direction: column;
}
.item-detail-title p {
  color: #666;
  font-size: 20px;
}
.item-detail-express {
  font-size: 14px;
  padding: 2px 3px;
  border-radius: 3px;
  background-color: #e4393c;
  color: #fff;
}
/*商品标签*/
.item-detail-tag {
  font-size: 12px;
  color: #e4393c;
}
/*价格详情等*/
.item-detail-price-row {
  padding: 5px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background-color: #f3f3f3;
}
.item-price-left {
  display: flex;
  flex-direction: column;
}
.item-price-title {
  color: #999999;
  font-size: 14px;
  margin-right: 15px;
}
.item-oldprice-title{
  color: #999999;
  font-size: 12px;
  margin-right: 15px;

}
.item-price-row {
  margin: 5px 0px;
}
.item-price {
  color: #e4393c;
  font-size: 20px;
  cursor: pointer;
}
.item-oldprice{
  color: #302e2e;
  font-size: 15px;
  cursor: pointer;
}
.item-price-full-cut {
  margin-right: 3px;
  padding: 3px;
  color: #e4393c;
  font-size: 12px;
  background-color: #ffdedf;
  border: 1px dotted #e4393c;
  cursor: pointer;
}
.item-remarks-sum {
  display: flex;
  flex-direction: row;
  padding-left: 8px;
  /* border-right: 1px solid #ccc; */
}
.item-remarks-sum p {
  color: #999999;
  font-size: 12px;
  line-height: 10px;
  text-align: center;
}
.item-remarks-num {
  line-height: 18px;
  color: #005eb7;
}
.item-select {
  display: flex;
  flex-direction: row;
  margin-top: 15px;
}
.item-select-title {
  color: #999999;
  font-size: 14px;
  margin-right: 15px;
}
.item-select-column {
  display: flex;
  flex-direction: column;
}
.item-select-row {
  display: flex;
  flex-direction: row;
  margin-bottom: 8px;
}
.item-select-box {
  display: flex;
  flex-direction: row;
  align-items: center;
}
.item-select-img {
  width: 36px;
}
.item-select-box {
  padding: 5px;
  margin-right: 8px;
  background-color: #f7f7f7;
  border: 0.5px solid #ccc;
  cursor: pointer;
}
.item-select-box:hover {
  border: 0.5px solid #e3393c;
}
.item-select-box-active {
  border: 0.5px solid #e3393c;
}
.item-select-img img {
  width: 100%;
}
.item-select-intro p {
  margin: 0px;
  padding: 5px;
}
.item-select-class {
  padding: 5px;
  margin-right: 8px;
  background-color: #f7f7f7;
  border: 0.5px solid #ccc;
  cursor: pointer;
}
.item-selected{
  padding: 5px;
  margin-right: 8px;
  background-color: rgb(128, 23, 23);
  color: #fff;
  border: 0.5px solid #ccc;
  cursor: pointer;

}
.item-select-class:hover {
  border: 0.5px solid #e3393c;
}
.add-buy-car-box {
  /* width: 90%;
  margin-top: 15px;
  border-top: 1px dotted #ccc; */
  margin-right: 20px;
}
/*
.add-buy-car {
  margin-top: 15px;
} */
.box-cart{
  width: 180px;
  height: 45px;
  margin-right: 30px;
  border: 1px solid red;
  line-height: 45px;
  text-align: center;
  font-size: 16px;
  color: red;

}
.cart{
  background-color: rgb(128, 23, 23);
  color: #fff;
}
.shop{
  background-color: #ffeded;

}
.sold{
  border-right: 2px solid #ccc;
  padding-right: 10px;
}
.sold p{
  color: #005eb7;
}
.remark p{
  color: #005eb7;
}
.remark{
  padding-left: 5px;
}
/* .item-detail-big-img:hover{
  transform: scale(1.5);
  transition: all .3s;

} */
.item-detail-like{
  margin-top: 50px;
  font-size: 12px;
  margin-left: 20px;
  border: 1px solid silver;
  width: 80px;
  height: 30px;
  line-height: 30px;
  padding-left: 10px;

}
.item-detail-like:hover{
  background-color: rgb(102, 25, 25);
  color: #fff;
}
.form-cart{
  border: 2px solid rgb(230, 21, 21);
}

/******************商品图片及购买详情结束******************/
</style>
