<template>
  <div class="app-container">
    <!-- 头部搜索 -->
    <div class="searchBox">
      <el-form :model="customer" :inline="true" size="small" label-width="80px" class="demo-ruleForm" style="overflow:hidden">
        <el-form-item label="用户帐号">
          <el-input v-model="customer.userName" clearable placeholder="请输入用户帐号"></el-input>
        </el-form-item>

        <el-form-item label="用户状态">
          <el-select v-model="customer.passwordQuestion" placeholder="请选择" >
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
<!--          <el-button @click="getOrderList">查询</el-button>-->
          <el-button >查询</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-table
      :cell-style="{'text-align':'center'}"
      :header-cell-style="{'text-align':'center'}"
      v-loading="listLoading"
      :data="customerList"
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

      <el-table-column label="用户帐号" width="195">
        <template slot-scope="scope">
          {{ scope.row.userName }}
        </template>
      </el-table-column>
      <!-- <el-table-column label="用户ID" width="55">
        <template slot-scope="scope">
          {{ scope.row.userId }}
        </template>
      </el-table-column> -->

      <el-table-column label="创建时间">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>


      <el-table-column show-overflow-tooltip label="订单状态">
        <template slot-scope="scope">
          <p v-if="scope.row.passwordQuestion==1">正常使用</p>
          <p v-if="scope.row.passwordQuestion==2">禁止使用</p>
        </template>
      </el-table-column>




      <el-table-column label="操作" align="center" width="250px" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="scope.row.passwordQuestion==2" size="mini" @click="updatePswQuestion(scope.row.userName,scope.row.passwordQuestion)">
            解除封禁
          </el-button>
          <el-button v-if="scope.row.passwordQuestion==1" size="mini" type="danger"  @click="updatePswQuestion(scope.row.userName,scope.row.passwordQuestion)">
            封禁账号
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getAlluser" />

  </div>
</template>

<script>
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { sendServer, sendServerGet} from '@/utils/common';
export default {
  components: { Pagination },
  data(){
    return{
      order:{
        orderId:'',
        status:'',

      },
      customer:{
        userName:'',
        createTime:'',
        passwordQuestion:''
      },
      orderList:[],

      customerList:[],

      options1:[
        {
          "salechose":1,
          "name":"正常使用"
        },
        {
          "salechose":2,
          "name":"禁止使用"
        }
      ],

      total: 0,
      listQuery: {
        page: 1,
        limit: 10,
      },


    }
  },
  created(){

    this.getAlluser();
  },
  methods:{
    //获取用户信息
    getAlluser(){
      let params={},send={}
      params.send=send;
      params.url="customer/getAlluser";
      sendServer(params,(commRes)=>{
        if(commRes.code==100) {
          // console.log(commRes)
          this.customerList=commRes.extend.customerList
        }
      });
    },

    //查看订单详情
    toDetail(orderId){
      let routeData=this.$router.resolve('/orderDetail/index/'+orderId)
      // this.$router.push('/orderDetail/index/'+orderId)
      window.open(routeData.href, '_blank')
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

    //修改用户权限
    updatePswQuestion(username,passwordQuestion){
      console.log(username)
      console.log(passwordQuestion)
      if (passwordQuestion == 1){
        passwordQuestion = 2
      }else {
        passwordQuestion = 1
      }

      let params={},send={}
      send.username=username
      send.passwordQuestion=passwordQuestion
      params.send=send;
      params.url="customer/updatePswQuestion";
      sendServerGet(params,(commRes)=>{
        console.log(commRes)
        if(commRes.code==100) {
          this.$message({
            type: 'info',
            message: '修改成功'
          });
          location.reload()
        }else {
          this.$message({
            type: 'info',
            message: '修改失败'
          });
        }
      });
    },

    /**
     * 清空条件查询，并刷新数据
     */
    reset() {
      this.customer.userName = "";
      this.customer.passwordQuestion = "";

    },
  }

}
</script>

<style scoped>
.searchBox{
  margin-top: 30px;
}


</style>
