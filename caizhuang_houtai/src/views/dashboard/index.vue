<template>
  <div class="dashboard-container">
    <div class="index">
      <div class="dashboard-text">
        缤色彩妆后台管理系统
        <span style="font-size:15px;float:right">当前登录用户：{{userInfo.nickName}}</span>
      </div>
      <div class="total-layout">
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="total-frame">
                <img :src="img_home_order" class="total-icon">
                <div class="total-title">今日订单总数</div>
                <div class="total-value">{{todayOrderLength}}</div>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="total-frame">
                <img :src="img_home_today_amount" class="total-icon">
                <div class="total-title">今日销售总额</div>
                <div class="total-value">￥{{todayPrice}}</div>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="total-frame">
                <img :src="img_home_yesterday_amount" class="total-icon">
                <div class="total-title">昨日销售总额</div>
                <div class="total-value">￥{{yesterdayPrice}}</div>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="total-frame">
                <svg-icon icon-class="total-week" class="total-icon">
                </svg-icon>
                <div class="total-title">近7天销售总额</div>
                <div class="total-value">￥{{sevenPrice}}</div>
              </div>
            </el-col>
          </el-row>
      </div>

      <div class="un-handle-layout">
        <div class="layout-title">待处理事务</div>
        <div class="un-handle-content">
          <el-row :gutter="20">
            <el-col :span="8">
              <div class="un-handle-item">
                <span class="font-medium">待发货订单</span>
                <span style="float: right;color:#f40" class="color-danger" v-if="diafaiLength!=0">({{diafaiLength}})</span>
                <span style="float: right" class="color-danger" v-if="diafaiLength==0">({{diafaiLength}})</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="un-handle-item">
                <span class="font-medium">已发货订单</span>
                <span style="float: right" class="color-danger">({{yifaiLength}})</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="un-handle-item">
                <span class="font-medium">已确认收货订单</span>
                <span style="float: right" class="color-danger">({{yishouhuoLength}})</span>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <div class="un-handle-item">
                <span class="font-medium">已关闭订单</span>
                <span style="float: right" class="color-danger">({{closeLength}})</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="un-handle-item">
                <span class="font-medium">买家已评价订单</span>
                <span style="float: right" class="color-danger">({{reviewLength}})</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="un-handle-item">
                <span class="font-medium">交易完成订单</span>
                <span style="float: right" class="color-danger">({{bgreviewLength}})</span>
              </div>
            </el-col>
          </el-row>
        </div>
      </div>
      <div class="overview-layout">
        <el-row :gutter="20">
          <el-col :span="10">
            <div class="out-border">
              <div class="layout-title">商品总览</div>
              <div style="padding: 40px">
                <el-row>
                  <el-col :span="6" class="color-danger overview-item-value">{{totalGoods.xiajiaLength}}</el-col>
                  <el-col :span="6" class="color-danger overview-item-value">{{totalGoods.shangjiaLength}}</el-col>
                  <el-col :span="6" class="color-danger overview-item-value">{{totalGoods.stockLength}}</el-col>
                  <el-col :span="6" class="color-danger overview-item-value">{{totalGoods.totalLength}}</el-col>
                </el-row>
                <el-row class="font-medium">
                  <el-col :span="6" class="overview-item-title">已下架</el-col>
                  <el-col :span="6" class="overview-item-title">已上架</el-col>
                  <el-col :span="6" class="overview-item-title">库存紧张</el-col>
                  <el-col :span="6" class="overview-item-title">全部商品</el-col>
                </el-row>
              </div>
            </div>
            <!-- 用户数量怎么看，查看订单里面的所有用户 select * from tb_customer  -->
            <div class="out-border" style="margin-top:10px">
              <div class="layout-title">用户总览</div>
              <div style="padding: 40px">
                <el-row>
                  <el-col :span="6" class="color-danger overview-item-value">{{userTotal.todayUserLength}}</el-col>
                  <el-col :span="6" class="color-danger overview-item-value">{{userTotal.yesterUserLength}}</el-col>
                  <el-col :span="6" class="color-danger overview-item-value">{{userTotal.monthUserLength}}</el-col>
                  <el-col :span="6" class="color-danger overview-item-value">{{userTotal.totalLength}}</el-col>
                </el-row>
                <el-row class="font-medium">
                  <el-col :span="6" class="overview-item-title">今日新增</el-col>
                  <el-col :span="6" class="overview-item-title">昨日新增</el-col>
                  <el-col :span="6" class="overview-item-title">本月新增</el-col>
                  <el-col :span="6" class="overview-item-title">会员总数</el-col>
                </el-row>
              </div>
            </div>
          </el-col>
           <el-col :span="14">
            <div class="out-border">
              <div class="layout-title">商品分类-数量</div>
                <div style="padding: 40px">
                  <div id="main" style="height:250px;"></div>
                </div>
            </div>
          </el-col>
        </el-row>
      </div>

      <div class="statistics-layout">
      <div class="layout-title"><router-link to="/form/showOrder" @click.native="hsadfhbsd">订单统计</router-link></div>
      <el-row>
        <el-col :span="4">
          <div style="padding: 20px">
            <div>
              <div style="color: #909399;font-size: 14px">本月订单总数</div>
              <div style="color: #606266;font-size: 24px;padding: 10px 0">{{monthNum}}</div>
              <div>
                <span class="color-success" style="font-size: 14px">+10%</span>
                <span style="color: #C0C4CC;font-size: 14px">同比上月</span>
              </div>
            </div>
            <div style="margin-top: 20px;">
              <div style="color: #909399;font-size: 14px">本周订单总数</div>
              <div style="color: #606266;font-size: 24px;padding: 10px 0">{{sevenNum}}</div>
              <div>
                <span class="color-danger" style="font-size: 14px">-10%</span>
                <span style="color: #C0C4CC;font-size: 14px">同比上周</span>
              </div>
            </div>
            <div style="margin-top: 20px;">
              <div style="color: #909399;font-size: 14px">本月销售总额</div>
              <div style="color: #606266;font-size: 24px;padding: 10px 0">{{monthPrice}}</div>
              <div>
                <span class="color-success" style="font-size: 14px">+10%</span>
                <span style="color: #C0C4CC;font-size: 14px">同比上月</span>
              </div>
            </div>
            <div style="margin-top: 20px;">
              <div style="color: #909399;font-size: 14px">本周销售总额</div>
              <div style="color: #606266;font-size: 24px;padding: 10px 0">{{sevenPrice}}</div>
              <div>
                <span class="color-danger" style="font-size: 14px">-10%</span>
                <span style="color: #C0C4CC;font-size: 14px">同比上周</span>
              </div>
            </div>
          </div>
        </el-col>
        <el-col :span="20">
           <!-- format="yyyy 年 MM 月 dd 日" -->
          <div style="padding: 10px;border-left:1px solid #DCDFE6">
             <el-date-picker
              style="float: right;z-index: 1"
              v-model="value2"
              type="datetimerange"
              :picker-options="pickerOptions"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
              @change="getTime">
            </el-date-picker>

            <div style="margin-top:45px">
                <ve-line :data="chartData" :settings="chartSettings"></ve-line>
            </div>
          </div>
        </el-col>
      </el-row>
      </div>


    </div>
  </div>
</template>

<script>
import * as echarts from "echarts";
import img_home_order from '@/assets/img/home_order.png';
import img_home_today_amount from '@/assets/img/home_today_amount.png';
import img_home_yesterday_amount from '@/assets/img/home_yesterday_amount.png';
import { sendServer, sendServerGet } from '@/utils/common';
export default {
  name: 'Dashboard',
  data(){
     this.chartSettings = {}
    return{
      //需要一个某天的日期，当天的数量，以及订单金额
      /**
       *
        SELECT create_time ,COUNT(create_time) AS  totalNum, SUM(totalPrice) AS sumPrice FROM tb_bg_order
        WHERE create_time BETWEEN '2021-03-28 15:21:29'AND '2021-03-31 11:22:33'
        GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d')
        SELECT * FROM tb_order WHERE DATEDIFF(create_time,NOW())=0
       */
      chartData: {
        columns: ['日期', '订单数量', '订单金额'],
        rows:[]
      },
      timeOrderList:[],
      img_home_order,
      img_home_today_amount,
      img_home_yesterday_amount,
      userInfo:JSON.parse(localStorage.getItem('userdata')),
      image_url:require('../../assets/bg2.jpg'),
      totalLength:'',//订单总数
      diafaiLength:'',
      yifaiLength:'',
      yishouhuoLength:'',
      closeLength:'',
      reviewLength:'',
      bgreviewLength:'',
      todayOrderLength:'',//今日订单的数目
      todayPrice:'',
      yesterdayPrice:'',
      sevenPrice:'',
      totalGoods:{},
      goodsList:[],
      typeList:[],
      userTotal:{},
      orderCountDate: '',
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      value2: '',
      sevenNum:'',
      monthPrice:'',
      monthNum:'',


    }
  },
  created(){
    this.getOrderList()
    this.getGoodsType()
    this.getGoodsList()
    this.getUserList()
    this.getCharData()
  },
  mounted(){

  },
  methods:{
    hsadfhbsd(){
      console.log("jjjjjjjjj")
    },
    getTime(data){
      let start=new Date(+data[0]+8*3600*1000)
      let startTime=start.toISOString().slice(0, 19).replace('T', ' ');
      let end=new Date(+data[1]+8*3600*1000)
      let endTime=end.toISOString().slice(0, 19).replace('T', ' ');
      console.log(startTime,endTime)

      let params={},send={}
      send.startTime=startTime
      send.endTime=endTime
      params.send=send;
      params.url="bgorder/timeOrder";
      sendServerGet(params,(commRes)=>{
        if(commRes.code==100){
          console.log(commRes)
          let list=commRes.extend.bgOrderList
          let charData=[]
          list.forEach(item=>{
            let time=item.createTime.slice(0,10)
            let price=item.sumPrice.toFixed(2)
            charData.push({'日期':time,'订单数量':item.totalNum,'订单金额':price})
          })
          this.chartData.rows=charData
          console.log(this.chartData.rows)
        }
      })
    },
    /**
     * getCharData
     */
    getCharData(){
      let params={},send={}
      let start=new Date(new Date(new Date().toLocaleDateString()).getTime() -6*24 * 60 * 60 * 1000+1+8*3600*1000)
      console.log(start)
      let end=new Date(+new Date()+8*3600*1000);
      console.log(end)
      // let end1=end.toLocaleDateString()
      let end2= end.toISOString().slice(0, 19).replace('T', ' ');
      let start1=start.toISOString().slice(0, 19).replace('T', ' ');

      send.startTime=start1
      send.endTime=end2
      params.send=send;
      params.url="bgorder/timeOrder";
      console.log(send)
      sendServerGet(params,(commRes)=>{
        if(commRes.code==100){
          console.log(commRes)
          let list=commRes.extend.bgOrderList
          let charData=[]
          list.forEach(item=>{
            let time=item.createTime.slice(0,10)
            let price=item.sumPrice.toFixed(2)
            charData.push({'日期':time,'订单数量':item.totalNum,'订单金额':price})
          })
          this.chartData.rows=charData
        }
      })

    },
    handleDateChange(){
      this.getData();
    },
    /**
     *  获取用户列表
     */
    getUserList(){
      //当天时间的0点
      let a=new Date(new Date(new Date().toLocaleDateString()).getTime()).getTime(); // 当天0点

      //当天时间的24点
      let c=new Date(new Date(new Date().toLocaleDateString()).getTime() + 24 * 60 * 60 * 1000 - 1).getTime();

      //上一天时间的0点
      let d=new Date(new Date(new Date().toLocaleDateString()).getTime() -24 * 60 * 60 * 1000+1).getTime();

      //上一天时间的24点
      let e=new Date(new Date(new Date().toLocaleDateString()).getTime() -1).getTime();

      //前7天的0点-
      let f=new Date(new Date(new Date().toLocaleDateString()).getTime() -6*24 * 60 * 60 * 1000+1).getTime();

      let params={}
      params.url="customer/getAlluser"
      sendServer(params,(commRes)=>{
        if(commRes.code==100){
          console.log(commRes)
          let userList=commRes.extend.customerList
          let userTotal={}
          let todayUser=[],yesterUser=[],monthUser=[]


          userList.forEach(item=>{
            let b=new Date(item.createTime).getTime()
            if(a<b&&b<c){
              todayUser.push(item)
            }
            if(d<b&&b<e){
              yesterUser.push(item)
            }
            if(f<b && b<c){
              monthUser.push(item)
            }

          })
          userTotal.totalLength=userList.length;
          userTotal.todayUserLength=todayUser.length;
          userTotal.yesterUserLength=yesterUser.length;
          userTotal.monthUserLength=monthUser.length
          this.userTotal=userTotal//用户总数

        }
      })


    },
    /**
     *  画图表商品类别
     */
    drawChart(){
       let myChart =echarts.init(document.getElementById("main"));
       let option = {
            tooltip: {
                trigger: 'item'
            },
            legend: {
                top: '5%',
                left: 'center'
            },
            series: [
                {
                    name: '商品分类-数量',
                    type: 'pie',
                    radius: ['40%', '70%'],
                    avoidLabelOverlap: false,
                    itemStyle: {
                        borderRadius: 10,
                        borderColor: '#fff',
                        borderWidth: 2
                    },
                    label: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        label: {
                            show: true,
                            fontSize: '40',
                            fontWeight: 'bold'
                        }
                    },
                    labelLine: {
                        show: false
                    },
                    data:this.typeList
                }
            ]
        };
        myChart.setOption(option);
        console.log(option)
    },
    //获取所有分类；
     getGoodsType(){
      let urlParams={}
      urlParams.url='goods/getGoodsCount',
      sendServer(urlParams,(commRes)=>{
        console.log(commRes)
        if(commRes.code=100){
          console.log(commRes)
          let list=[]
          commRes.extend.list.forEach((item,index)=>{
            // list[index]={}
            // console.log(index,item)
            // list[index].value=item.count
            // list[index].name=item.goodstypename
            // list.push(list[index])
            // console.log(list[index])======》这样写不对
            list.push({name:item.goodstypename,value:item.count})
          })
          console.log(list)
          this.typeList=list
          this.drawChart();
        }
      })

    },
    //获取所有的订单信息
    getOrderList(){
        //当天时间的0点
        let a=new Date(new Date(new Date().toLocaleDateString()).getTime()).getTime(); // 当天0点

        //当天时间的24点
        let c=new Date(new Date(new Date().toLocaleDateString()).getTime() + 24 * 60 * 60 * 1000 - 1).getTime();

        //上一天时间的0点
        let d=new Date(new Date(new Date().toLocaleDateString()).getTime() -24 * 60 * 60 * 1000+1).getTime();

        //上一天时间的24点
        let e=new Date(new Date(new Date().toLocaleDateString()).getTime() -1).getTime();

        //前7天的0点-今天的24点的订单
        let f=new Date(new Date(new Date().toLocaleDateString()).getTime() -6*24 * 60 * 60 * 1000+1).getTime();

        //前一个月的0点
        let g=new Date(new Date(new Date().toLocaleDateString()).getTime() -30*24 * 60 * 60 * 1000+1).getTime();
        let params={},send={}
        send.pageNum=1;
        send.pageSize=50000;
        params.send=send;
        params.url="bgorder/showOrder"
        sendServerGet(params,(commRes)=>{
            if(commRes.code==100){
                console.log(commRes)
                let orderList=commRes.extend.pageInfo.list;
                let diafai=[],yifai=[],yishouhuo=[],close=[],review=[],bgreview=[],todayOrder=[],yesterOrder=[],sevenOrder=[],monthOrder=[];
                orderList.forEach(item=>{
                  if(item.status==1){
                    diafai.push(item)
                  }
                  if(item.status==2){
                    yifai.push(item)
                  }
                  if(item.status==3){
                    yishouhuo.push(item)
                  }
                  if(item.status==4){
                    close.push(item)
                  }
                  if(item.status==5){
                    review.push(item)
                  }
                  if(item.status==6){
                    bgreview.push(item)
                  }
                  let b=new Date(item.createTime).getTime()
                  //比较时间大小，前一天时间和此时时间之内 创建的订单是今日订单
                  if(a<b&&b<c){//今日订单
                    todayOrder.push(item)
                  }
                  if(d<b&&b<e){//昨日订单
                    yesterOrder.push(item)
                  }

                  if(f<b && b<c){//7天内的订单
                    sevenOrder.push(item)
                  }
                  if(g<b && b<c){//近一个月
                    monthOrder.push(item)
                  }

                })
                this.diafaiLength=diafai.length;
                this.yifaiLength=yifai.length;
                this.yishouhuoLength=yishouhuo.length;
                this.closeLength=close.length;
                this.reviewLength=review.length;
                this.bgreviewLength=bgreview.length;
                this.todayOrderLength=todayOrder.length;

                let todayPrice=0;
                todayOrder.forEach(item=>{
                  todayPrice=todayPrice+item.totalprice
                })
                this.todayPrice=todayPrice

                let yesterdayPrice=0
                yesterOrder.forEach(item=>{
                  yesterdayPrice=yesterdayPrice+item.totalprice
                })
                this.yesterdayPrice=yesterdayPrice

                let sevenPrice=0;
                let sevenNum=sevenOrder.length;
                sevenOrder.forEach(item=>{
                  sevenPrice=sevenPrice+item.totalprice
                })
                this.sevenPrice=sevenPrice
                this.sevenNum=sevenNum


                let monthPrice=0
                let monthNum=monthOrder.length;
                monthOrder.forEach(item=>{
                  monthPrice=monthPrice+item.totalprice
                })
                this.monthPrice=monthPrice
                this.monthNum=monthNum





            }
        })
    },
    //获取所有商品的信息
    getGoodsList(){
      let params={},send={}
      send.pageNum=1;
      send.pageSize=50000;
      params.send=send;
      params.url="goods/getlistByPage";
      sendServerGet(params,(commRes)=>{
        if(commRes.code==100){
          console.log(commRes)
          let goodsList=commRes.extend.pageInfo.list;
          this.goodsList=goodsList;
          let xiajia=[],shangjia=[],stock=[]
          goodsList.forEach(item=>{
            if(item.onsale==0){//下架商品
              xiajia.push(item)
            }
            if(item.onsale==1){//上架商品
              shangjia.push(item)
            }
            if(item.stock<=5){
              stock.push(item)
            }
          })
          let totalGoods={}
          totalGoods.totalLength=goodsList.length;
          totalGoods.xiajiaLength=xiajia.length;
          totalGoods.shangjiaLength=shangjia.length;
          totalGoods.stockLength=stock.length;
          this.totalGoods=totalGoods;

        }
      })




    }


  },
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
.total-layout {
    margin-top: 20px;
  }

  .total-frame {
    border: 1px solid #DCDFE6;
    padding: 20px;
    height: 100px;
  }

  .total-icon {
    color: #409EFF;
    width: 60px;
    height: 60px;
  }

  .total-title {
    position: relative;
    font-size: 16px;
    color: #909399;
    left: 70px;
    top: -50px;
  }

  .total-value {
    position: relative;
    font-size: 18px;
    color: #606266;
    left: 70px;
    top: -40px;
  }

  .un-handle-layout {
    margin-top: 20px;
    border: 1px solid #DCDFE6;
  }

  .layout-title {
    color: #606266;
    padding: 15px 20px;
    background: #F2F6FC;
    font-weight: bold;
  }

  .un-handle-content {
    padding: 20px 40px;
  }

  .un-handle-item {
    border-bottom: 1px solid #EBEEF5;
    padding: 10px;
  }

  .overview-layout {
    margin-top: 20px;
  }

  .overview-item-value {
    font-size: 24px;
    text-align: center;
  }

  .overview-item-title {
    margin-top: 10px;
    text-align: center;
  }

  .out-border {
    border: 1px solid #DCDFE6;
  }

  .statistics-layout {
    margin-top: 20px;
    border: 1px solid #DCDFE6;
  }
  .mine-layout {
    position: absolute;
    right: 140px;
    top: 107px;
    width: 250px;
    height: 235px;
  }
  .address-content{
    padding: 20px;
    font-size: 18px
  }
  .index {
    margin-top: 20px;
    margin-left: 70px;
    margin-right: 120px;
  }
</style>
