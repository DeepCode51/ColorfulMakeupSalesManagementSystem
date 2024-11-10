<template>
  <div>
    <order-title :current="current"></order-title>
    <div class="pay-done-box">
      <img src="static/img/pay-success.png">
      <p class="pay-text">
        <span style="padding-right:30px"><router-link to="/home/showOrder">查看订单</router-link></span>
        回<router-link to="/">首页</router-link>继续逛逛
      </p>
    </div>
  </div>
</template>

<script>
import OrderTitle from '@/components/common/OrderTitle';
export default {
  name: 'PayDone',
  components:{
    OrderTitle
  },
  data(){
    return{
      current:2
    }
  }
};
</script>

<style scoped>
.pay-text{
  font-size: 16px;
  margin-top: 30px;
  font-weight: 700;
}
.pay-text a{
  color: #515a6e;;
}
.pay-text a:hover{
  color: #f40;;
}

.pay-done-box {
  height: 600px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>
