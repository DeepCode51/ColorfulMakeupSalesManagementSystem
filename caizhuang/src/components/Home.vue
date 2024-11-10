<template>
  <div>
    <!-- 头部 -->
    <div class="home-top">
      <div class="top1">
        <p class="top-text">我的彩妆</p>
      </div>
      <div class="top3">
        <div>
         <p class="top3-home"><router-link to="/home/"> 首页</router-link></p>
        </div>
        <div>
         <router-link to="/home/mySetting"><p class="top3-setting">账户设置</p></router-link>
        </div>
      </div>
      <div class="top2">
        <Input  v-model="searchData" size="large" class="sousuo" placeholder="输入你想查找的商品" style="outline:none"  >
          <Button slot="append" icon="ios-search" @click="goSearch" class="searchBtn"></Button>
        </Input >
      </div>
    </div>
     <!-- 头部结束 -->
    <div>
      <router-view></router-view>
    </div>


  </div>
</template>

<script>
export default {
  name: 'Home',
  data () {
    return {
        searchData:'',

    }
  },
  methods:{
  //搜索框
    goSearch(){

    },
  }


};
</script>

<style scoped>
.home-top{
  width: 100%;
  height: 80px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin: 0 auto;
  margin-bottom: 10px;
  background-color: #7a040e;
}
.home-top .top1{
  /* background-color: blueviolet; */
  width: 180px;
  height: 80px;
  font-size: 26px;
  color: #fff;
  font-weight: 600;
  line-height: 80px;
  margin-left: 60px;
}
.top2{
  /* background-color: rgb(30, 158, 158); */
  width: 350px;
  margin-right: 60px;
  float: right;
}
.top3{
  /* background-color: rgb(54, 177, 23); */
  width: 650px;
  height: 80px;
  line-height: 80px;
  margin-left: 20px;
  font-size: 16px;
  color: #fff;
  display: flex;
  flex-direction: row;
  align-items: center;
}
.top3-home{
  width: 60px;
  /* background-color: cadetblue; */
  margin-right: 10px;
}
.top3 a{
  color:#fff;
}
.sousuo {
  margin: 5px 0px;
  height: 36px;
}

.sousuo >>> .ivu-input:focus{
    outline:none;
    border:none;
    box-shadow: 0 0 0 2px transparent;
    border-bottom: 1.3px solid gainsboro;
}


</style>
