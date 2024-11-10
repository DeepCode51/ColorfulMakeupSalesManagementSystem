<template>
  <div>
    <div class="search-nav">
      <div class="search-nav-container">
        <ul>
          <!-- <li :class="[choose?'selected':'']">全部商品</li> -->
          <!-- :class="[isActive?'active':'']" -->
          <li v-for="(item,index) in GoodsType" :key="index" @click="choseType"  
          :class="[chooseId==item.id?'selected':'li-a']"
          :data-id="item.id">{{item.goodstypename}}</li>
          <!-- <li><router-link to="/">首页</router-link></li>
          <li><router-link to="/">服装城</router-link></li>
          <li><router-link to="/">美妆馆</router-link></li>
          <li><router-link to="/">BIT超市</router-link></li>-->
         
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { sendServer } from '../../utils/common';
export default {
  name: 'GoodsListNav',
  data(){
    return{
      GoodsType:[
        {
          id:'all',
          goodstypename:'全部宝贝'
        }
      ],
      chooseId:'all',
      bb:[1,2,3,4,5]
    }
  },
  beforeCreate(){
     
  },
  created(){
    this.getGoodsType();
  },
  mounted(){
    // this.choseGoods(this.chooseId)

    //  console.log(this.bb)这样是可以获取的
    //  console.log(this.GoodsType)这样是获取不到的，goodsType还未获取到
    // setTimeout(()=> {

    // }, 1000);

  },
  methods:{
    getGoodsType(){
      let params={};
      params.url="goods/getGoodsType"
      sendServer(params,(commRes)=>{
        console.log(commRes)
        if(commRes.code==100){
          // this.GoodsType=commRes.extend.list
          this.GoodsType=this.GoodsType.concat(commRes.extend.list)
          console.log(this.GoodsType)
          // this.GoodsType.push(commRes.extend.list)
        }
      })

    },
    choseType(options){
      console.log(options)
      let id=options.srcElement.dataset.id || this.chooseId
      console.log(id)
      this.chooseId=id
      // console.log(this.chooseId)
      this.$store.commit('getTypeId',this.chooseId)
      console.log(this.$store.state.typeId)
      this.$emit('chooseType')//,this.chooseId

    },
    // choseGoods(id){
    //   this.chooseId=id
    //   console.log("emit")
    //   this.$emit('chooseType',this.chooseId)

    // }

  }
};
</script>

<style scoped>
.search-nav{
  width: 100%;
  height: 64px;
  border-bottom: 2px solid #B1191A;
}
.search-nav-container{
  /* width: 80%; */
  width:100%;
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

/* .search-nav-container li{
  cursor: pointer;
  
  margin-right: 30px;
  line-height: 64px;
  color: #C81623;
  font-size: 18px;
 
  float: left;

} */
.li-a{
  cursor: pointer;
  /* margin-left: 30px; */
  margin-right: 30px;
  line-height: 64px;
  color: #C81623;
  font-size: 18px;
  /*font-weight: bold;*/
  float: left;

}
.search-nav-container a{
  color: #C81623;
  text-decoration: none;
}
/* .search-nav-container li:first-child{
  padding: 0px 38px;
  background:#B1191A;
  margin: 0px;
  color: #fff;
} */
.selected{
  cursor: pointer;
  margin-right: 30px;
  line-height: 64px;
  font-size: 18px;
  float: left;

  padding: 0px 38px;
  background:#B1191A;
  /* margin: 0px; */
  color: #fff;
}
</style>
