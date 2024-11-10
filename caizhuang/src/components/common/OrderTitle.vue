<template>
  <div>
    <div class="top0">
      <div class="top1">
          <!-- {{current}} -->
        <img :src="searchImgurl" />
      </div>
      <div class="top2">
        <Steps :current="current">

          <Step title="进行中" content="确认订单信息" v-if="current<0"></Step>
          <Step title="已完成" content="确认订单信息" v-if="current>=0"></Step>

          <Step title="进行中" content="付款到支付宝"v-if="current<1"></Step>
          <Step title="已完成" content="付款到支付宝"v-if="current>=1"></Step>

          <Step title="进行中" content="支付完成" v-if="current<2"></Step>
          <Step title="已完成" content="支付完成"v-if="current==2"></Step>

          <Step title="进行中" content="等待发货"></Step>
          <!-- <Step title="已完成" content="等待发货"></Step> -->
        </Steps>
      </div>
     </div>
  </div>
</template>

<script>
export default {
    props:{
        current:Number,
        required:true
    },
    data(){
        return{
            searchImgurl:require('../../../static/img/index/logo.png'),
            // current:-1,
        }

    }

}
</script>
<style>
.ivu-steps-item.ivu-steps-status-process .ivu-steps-head-inner {
    border-color: #b13009;
    background-color: #b13009;
}
</style>

<style scoped>
.top0{
  /* width: 80%; */
  width: 1050px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin: 5px auto;
}
.top1 img{
  width: 210px;
  height: 100px;
}
.top2{
  width: 50%;
}


</style>
