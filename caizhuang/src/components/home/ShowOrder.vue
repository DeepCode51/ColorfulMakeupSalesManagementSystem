<template>
  <div class="order-box">
    <!-- <div class="action">
        <p><Icon type="ios-trash" />订单回收站</p>
    </div> -->
    <el-tabs v-model="activeName" @tab-click="handleClick" class="tabs">
        <el-tab-pane label="所有订单" name="first">
            <order-template a="0"></order-template>
        </el-tab-pane>
        <el-tab-pane label="待付款" name="second">
            <order-template a="1"></order-template>
        </el-tab-pane>
        <el-tab-pane label="待发货" name="third">
            <order-template a="2"></order-template>
        </el-tab-pane>
        <el-tab-pane label="待收货" name="fourth">
            <order-template a="3"></order-template>
        </el-tab-pane>
        <el-tab-pane label="待评价" name="five">
            <order-template a="4"></order-template>
        </el-tab-pane>
         <el-tab-pane label="交易关闭" name="seven">
            <order-template a="6"></order-template>
        </el-tab-pane>
        <el-tab-pane  name="six">
            <span slot="label"><i class="el-icon-delete"></i>订单回收站</span>
            <order-template a="5"></order-template>
        </el-tab-pane>
    </el-tabs>

  </div>
</template>

<script>
import OrderTemplate from '../../components/home/OrderTemplate';
export default {
    components:{
        OrderTemplate
    },
    data(){
        return{
             activeName:'first',
        }
    },
    methods:{
         handleClick(tab, event) {
            if(tab.label=='全部宝贝'){
                this.$refs.coll.getAllCollection();
                this.searchData=null
            }
        },
    }

}
</script>
<style>
/* .el-tabs__item:last-child{
 margin-left: 520px;

}
.el-tabs__active-bar:last-child{
    margin-left: 520px;
    position: absolute;
    bottom: 0;
    left: 0;
    height: 2px;
    background-color: #f40;
    z-index: 1;
} */
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
</style>

<style scoped>

.action:hover{
    color:#f40;
}
.action{
    right: 40px;
    position: absolute;
    z-index: 2;
    display: flex;
    justify-content: flex-end;
    font-size: 13px;
    text-align: right;
    margin-top: 20px;
}
.order-box{
    width: 100%;
}
.tabs{
    padding: 10px;
}

</style>
