<template>
  <div>
     <Row class="index-row">
        <Col span="6" class="span-info">
            <img :src="tempfilePath+userInfo.img" class="info-img"/>
            <p>{{userInfo.realName}} ({{userInfo.userName}})</p>
        </Col>
        <Col span="6"><router-link to="/home/mySetting/myAddress">我的收货地址</router-link></Col>
        <Col span="6">我的优惠信息</Col>
        <Col span="6"><router-link to="/home/mySetting/myAccount">我的账户信息</router-link></Col>
    </Row>
    <Row class="action">
        <Col span="6" class="action-border"><router-link to="/home/showOrder">待付款</router-link> </Col>
        <Col span="6"class="action-border"><router-link to="/home/showOrder">待发货</router-link></Col>
        <Col span="6"class="action-border"><router-link to="/home/showOrder">待收货</router-link></Col>
        <Col span="6" ><router-link to="/home/showOrder">待评价</router-link></Col>
    </Row>
     <Row class="wuliu">
        <Col span="24">
            <p class="wuliu-p"><i class="el-icon-truck"></i>我的物流</p>
        </Col>
    </Row>
    <Row class="wuliu1">
        <Col span="24">
         <!-- 查找所有订单status==2的订单信息 -->
            <div>
                <div v-for="(item,index) in wuliList" :key="index" style="margin-bottom:20px;border: 1px solid #e4eaee;">
                    <div class="wuli-box">
                        <div style="border-right:1px solid #e4eaee; width:70px">
                            <img :src="tempfilePath+item.orderItemList[0].goods.picture" class="img-60"/>
                        </div>
                        <div style="line-height:60px;width:670px;text-align:center;border: 1px solid #e4eaee;">
                            订单编号：{{item.orderId}} 下单时间：{{item.createTime}}
                        </div>

                        <div  style="line-height:60px;width:180px;text-align:center;border: 1px solid #e4eaee;">
                            <router-link :to="{path:'/orderDetail',query:{orderId:item.orderId}}" target="_blank"><Button size="small">查看详情</Button></router-link>
                            <Button size="small" @click="confirmOrder(item.orderId)">确认收货</Button>
                        </div>

                    </div>
                </div>
            </div>
        </Col>
    </Row>
    <!-- <div class="cai-like">
        <p class="like-p">猜你喜欢</p>
        <hr style="height:2px;width:1050px;border:none;color:#E4E7ED;background-color:#E4E7ED;"/>
        <div>
            <hot-cart></hot-cart>
        </div>
    </div> -->
  </div>
</template>

<script>
import api from '../../utils/api';
import HotCart from '@/components/common/HotCart';
import { sendServer } from '../../utils/common';
export default {
    components:{
        HotCart
    },
    data(){
        return{
            imgUrl:require("../../../static/img/index/susu.png"),
            wuliList:[],
            tempfilePath:'http://localhost:8089/img/',
            userInfo:JSON.parse(localStorage.getItem('userdata')),
        }
    },
    created(){
        this.getWuli();
    },
    methods:{
       confirmOrder(orderId){
           this.$confirm('是否确认收货', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '暂时不',
                type: 'warning'
            }).then(() => {
                this.confirm(orderId);
            }).catch(() => {

            });
       },
       confirm(orderId){
            let status="3";
            api.updateStatus(orderId,status).then((res)=>{
                if(res.code==100){
                    this.$Message.success("收货成功")
                    this.getWuli();
                }
            })
        },
        getWuli(){
            let params={},send={};
            send.userId=this.userInfo.id;
            send.status=2;
            params.send=send;
            params.url="order/showOrder";
            sendServer(params,(commRes)=>{
                if(commRes.code==100){
                    console.log(commRes)
                    this.wuliList=commRes.extend.orderList

                }
            })
        },

    }

}
</script>

<style scoped>
.like-p{
    font-size: 15px;
    font-weight: 700;
    color: #f40;
    margin-left: 70px;
    border: 1px solid #f40;
    text-align: center;
    width: 80px;
    height: 30px;
    line-height: 30px;
}
.cai-like{
    margin-top: 40px;
}
.wuli-box{
    padding:5px;
    display: flex;
    flex-direction: row;
    /* justify-content: space-around; */
}
.img-60{
    width:60px;
    height: 60px;
}
.wuliu-p{
    font-size: 14px;
    font-weight: 600;
    padding-left: 30px;
}
.span-text{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}
.wuliu{
    width: 900px;
    margin: 20px auto;
    line-height: 30px;
    border: 1px solid #e4eaee;
    background-color: #F5F8FA;
}
.wuliu1{
    width: 900px;
    margin: 20px auto;
     /* border: 1px solid #e4eaee; */
}
.action{
    width: 900px;
    margin: 0 auto;
    text-align: center;
    border: 1px solid #e4eaee;
    line-height: 60px;
    background-color: #FFF;
    padding: 10px;
}
.action a{
 color:#3c3c3c;
}
.action a:hover{
 color:#f40;
}
.action-border{
    border-right: 1px solid #e4eaee;
}

.index-row{
    width: 100%;
    margin: 0 auto;
    padding: 10px 100px;
    text-align: center;
    /* border: 1px solid #e4eaee; */
    line-height: 60px;
    background-color: #F5F8FA;
}
.index-row a{
    color: #515A6E;
}.index-row a:hover{
    color: #f40;
}
.info-img{
    width: 60px;
    height: 60px;
    border-radius: 50%;
    margin-left: 30px;
}
.span-info{
    display: flex;
    flex-direction: row;
}
.span-info p{
    text-align: center;
    margin-left: 10px;
    line-height: 60px;
}
</style>
