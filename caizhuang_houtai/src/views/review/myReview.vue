<template>
  <div class="app-container">
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

          <el-form-item label="用户ID">
            <el-input v-model="review.userId" clearable placeholder="请输入用户ID"></el-input>
          </el-form-item>

          <el-form-item align="right">
            <el-button @click="reset">重置</el-button>
            <el-button @click="getMyReview">查询</el-button>
          </el-form-item>
        </el-form>
     </div>
     <el-table
     :cell-style="{'text-align':'center'}"
     :header-cell-style="{'text-align':'center'}"
      v-loading="listLoading"
      :data="bgReviewList"
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
      <el-table-column label="订单编号" width="200px">
        <template slot-scope="scope">
          {{ scope.row.orderid }}
        </template>
      </el-table-column>

      <el-table-column label="用户ID" >
        <template slot-scope="scope">
          {{ scope.row.userId }}
        </template>
      </el-table-column>
      <el-table-column label="已购商品" show-overflow-tooltip>
        <template slot-scope="scope">
          {{ scope.row.goods.description }}
        </template>
      </el-table-column>

       <el-table-column label="等级" >
        <template slot-scope="scope">
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


      <el-table-column label="评价时间" width="170px" show-overflow-tooltip>
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" @click="del(scope.row.id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getMyReview" />


  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import { sendServerGet } from '@/utils/common';
export default {
  components: { Pagination },
  data(){
    return{
      bgReviewList:[],
      review:{
        userId:'',
        degree:'',

      },
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
    this.getMyReview()
  },
  methods:{
    //删除该条评价
del(id){
      let params={},send={};
      send.ids=id;

      params.send=send;
      params.url="review/deleteByBatch";
      sendServerGet(params,(commRes)=>{
        console.log(commRes);
        if(commRes.code==100){
          this.$message({
            type: 'success',
            message: '删除成功!',
          });
        }
      })
    },
    handleSelectionChange(val) {
        this.multipleSelection = val;
    },
    reset() {
        this.review.degree = "";
        this.review.userId = "";
        this.getMyReview();
    },
    getMyReview(){
      let params={},send={};
      if(this.review.userId){
        send.userId=this.review.userId;
      }
      if(this.review.degree){
          send.degree=this.review.degree
      }
      if(this.degree=='all'){
          send.degree=null
      }
      send.pageSize=this.listQuery.limit
      send.pageNum=this.listQuery.page
      params.send=send;
      params.url="review/reviewByBg";
      sendServerGet(params,(commRes)=>{
          if(commRes.code==100){
              this.bgReviewList=commRes.extend.pageInfo.list
              this.total=commRes.extend.pageInfo.total
              this.listLoading = false
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
