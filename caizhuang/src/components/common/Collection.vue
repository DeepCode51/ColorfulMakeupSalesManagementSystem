<template>
  <div class="container1">
       <!-- 商品展示容器 -->
      <div class="goods-box2">

        <div class="goods-box-box">
          <div class="goods-list">
            <div class="goods-info-show" v-for="(item, index) in collectList" :key="index">
              <div class="goods-show-img" v-on:mouseover="deleteLike(item)" v-on:mouseout="hideDelete(item)">
                <img :src="temfielPath+item.goods.picture" class="goods-img" />
                <button v-if="item.seen" class="btn" id="btn"> <i class="el-icon-delete"></i></button>
                <div class="a" @click="del" @mousemove="showBtn" :data-likeId='item.id' :data-goodsId='item.goodsId'></div>
              </div>

              <div class="goods-show-price">
                <span>
                  <Icon type="social-yen text-danger"></Icon>
                  <span class="seckill-price text-danger">{{item.goods.sellPrice}}</span>
                </span>
              </div>
              <div class="goods-show-detail">
                 <router-link title="商品专区" target="_blank":to="{path:'/goodsDetail',query:{goodsId:item.goodsId}}">
                  <span>{{item.goods.description}}</span>
                 </router-link>
              </div>
              <div class="goods-show-num">
                已有<span>{{item.goods.remark}}</span>人评价
              </div>
              <div class="goods-show-seller">
                <span>{{item.goods.goodsname}}</span>
                <!-- choseItem 表示删除操作时候 有木有被选择 给他一个判断，当为真的时候表示被选择  变成红色  -->
                <div v-if="chose"  :class="item.choseItem?'mark':'btn-chose'" @click="choseItem(item)"><Icon type="md-checkmark" /></div>
              </div>
            </div>
            <div v-if="collectList.length==0 && searchData=='' "class="no-list">
              <div>
                <img :src="noGoods" class="no-goods"/>
              </div>
              <div  class="no-text">
                <p style="margin-top:30px">您暂时还未收藏任何商品。</p>
                <p>请前往<router-link to="/">商品区</router-link>进行收藏吧</p>
              </div>
            </div>
            <!-- 搜索结果为空时候 -->
            <div v-if="collectList.length==0 && searchData!=''&& searchData!=null"class="no-list">
              <div>
                <img :src="noGoods" class="no-goods"/>
              </div>
              <div>
                <p class="no-text">您的收藏夹，木有与"{{searchData}}"相关的宝贝商品哦</p>
                <p>看看输入的文字是否有误</p>
                <p>去掉不必要的字或词，如“的”、“什么”等</p>
                <p>调整关键字，如“移动充值”改为“移动 充值”或“移动”</p>
                <p>去看看其他<router-link to="/">商品吧</router-link></p>
              </div>
            </div>

            <!-- 猜你喜欢 -->
          </div>
        </div>
      </div>
  </div>
</template>

<script>

import api from '../../utils/api'
import { sendServer } from '../../utils/common';
export default {
  name: 'HotCart',
  beforeRouteEnter (to, from, next) {
    window.scrollTo(0, 0);
    next();
  },
  props:{
    a:{
      type:String,
      default: "0",
    },
    searchData:{
      type:String
    }
  },
  data () {
    return {
        noGoods:require('../../../static/img/no-goods.png'),
        chose:false,//当点击批量操作的时候 显示这个，以此来选择被删除的商品的ID
        collectList:[],//收藏列表
        temfielPath:'http://localhost:8089/img/',
        };
  },
  mounted () {
    if(this.a!=="0"){
      this.getCollection();
    }else{
      this.getAllCollection();
    }
  },
  methods: {

    choseItem(item){
      item.choseItem=!item.choseItem
    },
    showBtn(e){
      let div=document.getElementById('btn')
      if(e.offsetX<50&&e.offsetY<50){
        div.style.backgroundColor="red";
      }else{
        div.style.backgroundColor="#EFEFEF";
      }
    },
    del(e){
      let goodsId=e.srcElement.dataset.goodsid;
      if(e.offsetX<50&&e.offsetY<50){
        console.log("12312412")
        let likeId=e.srcElement.dataset.likeid
        this.$confirm('是否取消收藏该商品?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let params={},send={}
          send.id=likeId;
          params.send=send;
          params.url="like/cancelLike";
          console.log(params)
          sendServer(params,(commRes)=>{
            console.log(commRes)
            if(commRes.code==100){
              let collectList=this.collectList
              for(let i= 0; i< collectList.length;i++){
                if(collectList[i].id==likeId){
                  collectList.splice(i,1)
                  this.collectList=collectList
                }
              }
              this.$message({
                type: 'success',
                message: '操作成功!'
              });
            }
          })

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消操作'
          });
        });

      }else{
        this.$router.push({path: '/goodsDetail', query: {goodsId: goodsId}})
      }
    },

    hideDelete(item){
      item.seen=false
    },
    deleteLike(item){
      item.seen = true
    },
    //获取商品信息
    getCollection(name = null){
        api.getAllCollection().then((res)=>{
          this.collectList=res.extend.likeList.slice(0,10)
        })

    },
    getAllCollection(name = null){
        api.getAllCollection(name).then((res)=>{
          console.log(name)
          console.log(this.searchData)
          let a=res.extend.likeList;
          a.forEach(item=>{
            item.seen=false;
            item.choseItem=false
          })
          this.collectList = a
          console.log(this.collectList)
        })
    }
  },
  // 当一进去这个页面 获取所有的商品 searchname==''

};
</script>

<style scoped>
.no-goods{
  width: 90px;
  height: 90px;
  border-radius: 50%;
}
.no-text{
  font-size: 14px;
  font-weight: 600;
}
.no-list{
  margin: 0 auto;
  margin-top: 50px;
  display: flex;
  flex-direction: row;
}
.no-list a{
 color: #f40;
}
.mark{
  color: #fff;
  font-size: 20px;
  background-color: red;
}
.btn-chose{
  color: #495060;
  font-size: 20px;
}
.a{
  position: absolute;
  left: 0;
  right:0;
  top: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0);
  z-index: 100;
}
.btn{
  position:absolute;
  left: 0;
  top: 0;
  z-index: 99;
  border-radius: 0;
  /* background-color: red; */

}
.goods-show-img .btn:hover{
  background-color: red;
}
.goods-show-detail a{
  color: #495060;
}
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
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.goods-page {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}
.goods-img{
    width: 178px;
    height: 178px;
}
.boxGoodsImg{
  width: 160px;
  height: 160px;
}
.item-as a{
  color: #495060;
}
.goods-show-img{
  position: relative;
}

/* ---------------商品栏结束------------------- */

</style>
