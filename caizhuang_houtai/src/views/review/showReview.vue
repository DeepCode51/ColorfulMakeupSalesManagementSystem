<template>
  <div class="app-container">
    <!-- 前台：假设订单被删了那么订单明细也被删了 ，订单都被删了 ，哪还有什么追评 但是对于已评价的订单，评价依旧存在 除非主动删除 -->
    <!--  后台：查看订单的评价，存在订单明细ID，可以根据明细Id去查看订单Id，而当前台订单被删除时候，将无法根据明细ID去
    获取订单号-->
    <!-- 头部搜索 -->
    <div class="searchBox">
      <el-form :model="review" :inline="true" size="small" label-width="80px" class="demo-ruleForm" style="overflow:hidden">
        <el-form-item label="订单评价">
          <el-select v-model="review.degree" placeholder="请选择" >
            <el-option
              v-for="item in options1"
              :key="item.salechose"
              :label="item.name"
              :value="item.salechose">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="回复状态">
          <el-select v-model="review.reply" placeholder="请选择">
            <el-option
              v-for="item in options2"
              :key="item.value"
              :label="item.name"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="订单编号">
          <el-input v-model="review.orderId" clearable placeholder="请输入订单编号"></el-input>
        </el-form-item>




        <el-form-item align="right">
          <el-button @click="reset">重置</el-button>
          <el-button @click="getReviewList">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table
     :cell-style="{'text-align':'center'}"
     :header-cell-style="{'text-align':'center'}"
      v-loading="listLoading"
      :data="reviewList"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      @selection-change="handleSelectionChange"
      ref="multipleTable"
    >

      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column align="center" label="序号" width="55">
        <template slot-scope="scope">
          {{ scope.$index+1 }}
        </template>
      </el-table-column>

      <!-- <el-table-column label="评价编号" >
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column> -->

      <el-table-column label="订单编号" >
        <template slot-scope="scope">
          {{ scope.row.orderId }}
        </template>
      </el-table-column>

      <el-table-column label="被评商品" show-overflow-tooltip>
        <template slot-scope="scope">
          {{ scope.row.goods.description }}
        </template>
      </el-table-column>


      <el-table-column label="购买参数" show-overflow-tooltip>
        <template slot-scope="scope">
          {{ scope.row.goodsSize }}
        </template>
      </el-table-column>

       <el-table-column label="等级" >
        <template slot-scope="scope">
          <!-- {{ scope.row.degree}} -->
          <p v-if="scope.row.degree==0">好评</p>
          <p v-if="scope.row.degree==1">中评</p>
          <p v-if="scope.row.degree==2">差评</p>
        </template>
      </el-table-column>

      <el-table-column label="评价内容"show-overflow-tooltip>
        <template slot-scope="scope">
          {{ scope.row.content }}
        </template>
      </el-table-column>


      <el-table-column label="评价时间" width="170px" >
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" @click="toDetail(scope.row)">
            查看详情
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getReviewList" />

  </div>
</template>

<script>
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { sendServer, sendServerGet } from '@/utils/common';
export default {
   components: { Pagination },
  data(){
    return{
      reviewList:[],
      review:{
        reply:'',
        degree:'',
        orderId:'',
      },
      options2:[
        {
          "value":"0",
          "name":"未回复"
        },
         {
          "value":"1",
          "name":"已回复"
        }

      ],
      options1:[

          {
          "salechose":"0",
          "name":"好评"
          },
          {
          "salechose":"1",
          "name":"中评"
          },
          {
          "salechose":"2",
          "name":"差评"
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
    this.getReviewList()
  },
  methods:{
    toDetail(item){
      this.$router.push(
        {path:'/review/reviewDetail',query:{item:JSON.stringify(item)}}
      )
    },
    handleSelectionChange(val) {
        this.multipleSelection = val;
    },
    getReviewList(){
      let params={},send={}
      send.pageNum=this.listQuery.page,
      send.pageSize=this.listQuery.limit;
      if(this.review.degree){
        send.degree=this.review.degree
      }
      if(this.review.reply){
        send.isReply=this.review.reply
      }
      if(this.review.orderId){
        send.orderId=this.review.orderId
      }
      params.send=send;
      params.url="review/showBgReview";
      sendServerGet(params,(commRes)=>{
        if(commRes.code==100){
          this.reviewList=commRes.extend.pageInfo.list
          this.total=commRes.extend.pageInfo.total
          this.listLoading = false
        }
      })
    },
    reset() {
        this.review.degree = "";
        this.review.reply = "";
        this.review.orderId="";
        this.getReviewList();
    },
  }
}
</script>

<style scoped>
.searchBox{
  margin-top: 30px;
}


</style>
