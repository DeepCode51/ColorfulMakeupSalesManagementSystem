<template xmlns:c="http://www.w3.org/1999/html">
  <div class="app-container">
    <!-- 头部搜索 -->
    <div class="searchBox">
      <el-form :model="order" :inline="true" size="small" label-width="80px" class="demo-ruleForm" style="overflow:hidden">
        <el-form-item label="订单编号">
          <el-input v-model="order.orderId" clearable placeholder="请输入订单编号"></el-input>
        </el-form-item>

        <el-form-item label="订单状态">
          <el-select v-model="order.status" placeholder="请选择" >
            <el-option
              v-for="item in options1"
              :key="item.salechose"
              :label="item.name"
              :value="item.salechose">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item align="right">
          <el-button @click="reset">重置</el-button>
          <el-button @click="getOrderList">查询</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-table
      :cell-style="{'text-align':'center'}"
      :header-cell-style="{'text-align':'center'}"
      v-loading="listLoading"
      :data="orderLists"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      @selection-change="handleSelectionChange"
      ref="multipleTable"
    >

      <el-table-column align="center" label="序号" width="55" >
        <template slot-scope="scope" v-if="scope.row.status >= 7">
          {{ scope.$index+1 }}
        </template>
      </el-table-column>

      <el-table-column label="订单编号" width="195">
        <template slot-scope="scope" v-if="scope.row.status >= 7">
          {{ scope.row.orderId }}
        </template>
      </el-table-column>
      <!-- <el-table-column label="用户ID" width="55">
        <template slot-scope="scope">
          {{ scope.row.userId }}
        </template>
      </el-table-column> -->

      <el-table-column label="收件人" width="75">
        <template slot-scope="scope" >
          {{ scope.row.receName }}
        </template>
      </el-table-column>


      <el-table-column label="订单总价" show-overflow-tooltip>
        <template slot-scope="scope">
          {{ scope.row.totalprice }}
        </template>
      </el-table-column>

      <el-table-column label="运送方式">
        <template slot-scope="scope">
          {{ scope.row.deliverymethod }}
        </template>
      </el-table-column>


      <el-table-column label="买家备注" >
        <template slot-scope="scope">
          {{ scope.row.memo }}
        </template>
      </el-table-column>


      <el-table-column label="创建时间">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>

      <el-table-column label="付款方式">
        <template slot-scope="scope">
          {{ scope.row.paymethod }}
        </template>
      </el-table-column>



      <el-table-column show-overflow-tooltip label="订单状态">
        <template slot-scope="scope">
          <!-- 显示商家已评价的状态 -->
          <p v-if="scope.row.status==1">待发货</p>
          <p v-if="scope.row.status==2">已发货</p>
          <p v-if="scope.row.status==3">确认收货</p>
          <p v-if="scope.row.status==4">订单关闭</p>
          <p v-if="scope.row.status==5">买家已评</p>
          <p v-if="scope.row.status==6">交易完成</p>
          <p v-if="scope.row.status==7">用户退货</p>
          <p v-if="scope.row.status==8">退货完成</p>
        </template>
      </el-table-column>




      <el-table-column label="操作" align="center" width="250px" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" @click="toDetail(scope.row.orderId)">
            查看订单
          </el-button>
          <el-button type="primary" size="mini" v-if="scope.row.status==7" @click="returnOrder(scope.row.orderId)">
            订单退货
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getOrderList" />

  </div>
</template>

<script>
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { sendServer, sendServerGet } from '@/utils/common';
export default {
  components: { Pagination },
  data(){
    return{
      order:{
        orderId:'',
        status:'',

      },
      orderList:[],
      orderLists:[],
      options1:[
        {
          "salechose":1,
          "name":"待发货"
        },
        {
          "salechose":2,
          "name":"已发货"
        },
        {
          "salechose":3,
          "name":"已确认收货"
        },
        {
          "salechose":4,
          "name":"已关闭"
        },
        {
          "salechose":5,
          "name":"买家已评价"
        },
        {
          "salechose":6,
          "name":"交易完成"
        },
        {
          "salechose":7,
          "name":"请求退货"
        },
        {
          "salechose":8,
          "name":"退货完成"
        }
      ],
      listLoading: true,
      total: 0,
      listQuery: {
        page: 1,
        limit: 10,
      },


    }
  },
  created(){
    this.getOrderList();
  },
  methods:{
    //查看订单详情
    toDetail(orderId){
      let routeData=this.$router.resolve('/orderDetail/index/'+orderId)
      // this.$router.push('/orderDetail/index/'+orderId)
      window.open(routeData.href, '_blank')
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    //获取所有的订单信息
    getOrderList(){
      let params={},send={}
      this.listLoading = true;
      if(this.order.orderId){
        send.orderId=this.order.orderId;
      }
      if(this.order.status){
        send.status=this.order.status;
      }

      send.pageNum=this.listQuery.page,
        send.pageSize=this.listQuery.limit,
        params.send=send;
      console.log(send)
      params.url="bgorder/showOrder"
      sendServerGet(params,(commRes)=>{
        if(commRes.code==100){
          console.log(commRes)
          this.orderList=commRes.extend.pageInfo.list;
          console.log(this.orderList)
          this.orderList.map((item,index)=>{
            if(Number(item.status) >= 7){
              this.orderLists.push(item);
            }
          });
          this.total=this.orderLists.length
          this.listLoading = false
        }
      })

    },
    /**
     * 清空条件查询，并刷新数据
     */
    reset() {
      this.order.orderId = "";
      this.order.status = "";
      this.getOrderList();
    },
    //进行发货处理，改变订单的status==2表示已发货
    updataStatus(orderId){
      this.$confirm('是否要进行发货操作?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.fahuo(orderId);
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消发货'
        });
      });
    },
    fahuo(orderId){
      let params={},send={};
      send.orderId=orderId;
      send.status=2;//表示发货
      params.send=send;
      params.url="bgorder/updateStatus";
      sendServer(params,(commRes)=>{
        if(commRes.code==100){
          let params={},send={};
          send.orderId=orderId;
          send.status=2;//前台订单status==2 表示已发货
          params.send=send;
          params.url="order/updateStatus";
          sendServer(params,(commRes)=>{
            if(commRes.code==100){
              this.$message({
                type: 'success',
                message: '发货成功!'
              });
              this.getOrderList()
            }
          })

        }
      })
    },
  //进行退货处理，改变订单的status==8表示已退货
  returnOrder(){
    this.$confirm('是否确认收到用户的退货?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {
      this.tuihuo();
    }).catch(() => {
    });
  },
  tuihuo(){
    let params={},send={};
    let orderId=this.$route.params.orderId;
    send.orderId=orderId
    send.status=8;//表示已退货
    params.send=send;
    params.url="bgorder/updateStatus";
    sendServer(params,(commRes)=>{
      if(commRes.code==100){
        let params={},send={};
        send.orderId=orderId;
        send.status=8;//前台订单status==8 表示退货成功
        params.send=send;
        params.url="order/updateStatus";
        sendServer(params,(commRes)=>{
          if(commRes.code==100){
            this.$message({
              type: 'success',
              message: '退货成功!'
            });
            this.bgOrder.status=8;
          }
        })

      }
    })
  },
  }

}
</script>

<style scoped>
.searchBox{
  margin-top: 30px;
}


</style>
